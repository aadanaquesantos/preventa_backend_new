package com.digitalinka.restpreventa.dao;

import com.digitalinka.restpreventa.UtilDao;
import com.digitalinka.restpreventa.constantes.STATUS;
import com.digitalinka.restpreventa.model.*;
import com.digitalinka.restpreventa.model.request.CarritoRequest;
import com.digitalinka.restpreventa.model.response.PedidoResponse;
import com.digitalinka.restpreventa.model.response.StatusResponse;
import oracle.jdbc.OracleConnection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import oracle.sql.StructDescriptor;
import org.apache.commons.dbcp2.DelegatingConnection;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PedidoDaoImpl implements PedidoDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Value("${pkName}")
    protected String pkName;

    @Transactional(readOnly = false)
    @Override
    public PedidoResponse saveCarrito(CarritoRequest carritoRequest) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            PedidoResponse pedidoResponse=new PedidoResponse();
            StatusResponse status=new StatusResponse();
            try {
                DelegatingConnection del = new DelegatingConnection(conn);
                OracleConnection connection = (OracleConnection) del.getInnermostDelegate();
                Struct structCarritoHeader = connection.createStruct("CARRITO_HEADER",
                        new Object[] {
                               carritoRequest.getId(),
                               carritoRequest.getCodEmpresa(),
                                carritoRequest.getCodSede(),
                                carritoRequest.getCodMesa(),
                                carritoRequest.getCodLocalidad(),
                                carritoRequest.getCodAlmacen(),
                                carritoRequest.getCodVendedor(),
                                carritoRequest.getCodLocal(),
                                carritoRequest.getCodRuta(),
                                carritoRequest.getCodCliente(),
                                carritoRequest.getCodCondicion(),
                                carritoRequest.getUsuario(),
                                carritoRequest.getCodLista(),
                                carritoRequest.getCodCanal(),
                                carritoRequest.getCodTipoDoc()

                });



                Struct[] structs = new Struct[carritoRequest.getCarritoList().size()];
                int index = 0;
                for (Carrito carrito : carritoRequest.getCarritoList()) {

                    structs[index] = connection.createStruct("CARRITO_DETAIL_VENDEDOR",
                            new Object[] {
                                    carrito.getProduct().getCode(),
                                    carrito.getProduct().getUm(),
                                    carrito.getCantidad(),
                                    carrito.getProduct().getPriceBase(),
                                    carrito.getProduct().getPriceSugerido()
                            });

                    index++;
                }
                Array arrayCarrito = connection.createARRAY("LIST_DETAIL_CARRITO",structs);
                CallableStatement cs = connection.prepareCall(" { call "+pkName+".SP_REGISTRAR_CARRITO(?,?,?,?,?,?)}");
                cs.setObject(1, structCarritoHeader);
                cs.setArray(2,arrayCarrito);
                cs.registerOutParameter(3, OracleTypes.NUMBER);
                cs.registerOutParameter(4, OracleTypes.VARCHAR);
                cs.registerOutParameter(5, OracleTypes.CURSOR);
                cs.registerOutParameter(6, OracleTypes.CURSOR);
                cs.execute();
                status.setStatusCode(cs.getInt(3));
                status.setStatusText(cs.getString(4));
                pedidoResponse.setStatus(status);
                Pedido pedido=new Pedido();
               if(status.getStatusCode().equals(STATUS.SUCCESS)){
                   ResultSet rsPedido = (ResultSet) cs.getObject(5);
                   ResultSet rsDetallePedido = (ResultSet) cs.getObject(6);

                   if (rsPedido!= null) {
                       while (rsPedido.next()) {
                            pedido.setCodEmpresa(rsPedido.getString("COD_EMPRESA"));
                            pedido.setCodSede(rsPedido.getString("COD_SEDE"));
                            pedido.setCodMesa(rsPedido.getString("COD_MESA"));
                            pedido.setCodLocalidad(rsPedido.getString("COD_LOCALIDAD"));
                            pedido.setCodAlmacen(rsPedido.getString("COD_ALMACEN"));
                            pedido.setCodVendedor(rsPedido.getString("COD_VENDEDOR"));
                            pedido.setCodLocal(rsPedido.getString("COD_LOCAL"));
                            pedido.setCodRuta(rsPedido.getString("COD_RUTA"));
                            pedido.setCodCliente(rsPedido.getString("COD_CLIENTE"));
                            pedido.setCodCondicion(rsPedido.getString("COD_CONDICION"));
                            pedido.setUsuario(rsPedido.getString("USUARIO"));
                            pedido.setCodListaPrecios(rsPedido.getString("COD_LISTA"));
                            pedido.setCodCanal(rsPedido.getString("COD_CANAL"));
                            pedido.setCodTipoDoc(rsPedido.getString("COD_TIPO_DOC"));
                            pedido.setTotalFlete(rsPedido.getDouble("MONTO_FLETE"));
                            pedido.setTotalDescuentos(rsPedido.getDouble("DCTO_TOTAL"));
                            pedido.setTotalImporte(rsPedido.getDouble("IMPORTE_TOTAL"));
                       }
                       rsPedido.close();
                      // cs.close();
                   }
                List<DetallePedido> detalles=new ArrayList<>();
                   if (rsDetallePedido!= null) {
                       while (rsDetallePedido.next()) {
                           DetallePedido detallePedido=new DetallePedido();
                           Product product=new Product();
                           product.setCode(rsDetallePedido.getString("COD_ITEM"));
                           product.setUm(rsDetallePedido.getString("UNIDAD_MEDIDA"));
                           product.setDescription(rsDetallePedido.getString("DESC_ITEM"));
                           product.setPriceBase(rsDetallePedido.getDouble("PRECIO_BASE"));
                           product.setPriceSugerido(rsDetallePedido.getDouble("PRECIO_SUGERIDO"));

                           detallePedido.setProduct(product);
                           detallePedido.setCantidad(rsDetallePedido.getDouble("CANTIDAD"));
                           detallePedido.setImporteTotal(rsDetallePedido.getDouble("IMPORTE"));
                           detallePedido.setImporteDescuentos(rsDetallePedido.getDouble("DCTOS"));
                           detalles.add(detallePedido);

                       }
                       rsDetallePedido.close();
                   }
                   pedido.setDetallePedidos(detalles);
               }

                cs.close();

                pedidoResponse.setPedido(pedido);

                return  pedidoResponse;
            }catch(SQLException ex){
                status.setStatusCode(STATUS.ERROR);
                status.setStatusText(ex.getMessage());
                pedidoResponse.setStatus(status);
                return pedidoResponse;
            }
        });

    }
}
