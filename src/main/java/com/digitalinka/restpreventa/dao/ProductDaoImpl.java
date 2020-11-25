package com.digitalinka.restpreventa.dao;

import com.digitalinka.restpreventa.UtilDao;
import com.digitalinka.restpreventa.model.Product;
import com.digitalinka.restpreventa.model.User;
import com.digitalinka.restpreventa.model.response.DisponiblePrecioResponse;
import com.digitalinka.restpreventa.model.response.ProductListResponse;
import com.digitalinka.restpreventa.model.response.StatusResponse;
import com.digitalinka.restpreventa.model.response.UserResponse;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleConnection;
import org.apache.commons.dbcp2.DelegatingConnection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${nombrePaquete}")
    protected String nombrePaquete;


    @Value("${pkName}")
    protected String pkName;


    @Transactional(readOnly = true)
    @Override
    public ProductListResponse bonifItem(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            ProductListResponse productListResponse = new ProductListResponse();
            StatusResponse statusResponse = new StatusResponse();
            try {
                DelegatingConnection del = new DelegatingConnection(conn);
                OracleConnection connection = (OracleConnection) del.getInnermostDelegate();
                Array arrayStrinb = connection.createARRAY("LISTPARAMETR0STRING", UtilDao.getStructsString("PARAMETR0STRING", connection, parametrosString));
                CallableStatement cs = connection.prepareCall("call " + nombrePaquete + ".SP_HOME_DATA(?,?,?,?)");
                cs.setArray(1, arrayStrinb);
                cs.registerOutParameter(2, OracleTypes.NUMBER);
                cs.registerOutParameter(3, OracleTypes.VARCHAR);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.execute();
                statusResponse.setStatusCode(cs.getInt(2));
                statusResponse.setStatusText(cs.getString(3));
                ResultSet rs = (ResultSet) cs.getObject(4);
                    List<Product> productos=new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Product product=new Product();
                        product.setCode(rs.getString("COD_ITEM"));
                        product.setDescription(rs.getString("DESC_ITEM"));
                        product.setUri(rs.getString("FOTO"));
                        productos.add(product);
                    }
                    rs.close();
                    cs.close();
                }
                productListResponse.setProducts(productos);
                productListResponse.setStatus(statusResponse);
                return productListResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                productListResponse.setStatus(statusResponse);
                return productListResponse;

            }
        });

    }

    @Transactional(readOnly = true)
    @Override
    public ProductListResponse getSugeridos(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            ProductListResponse productListResponse = new ProductListResponse();
            StatusResponse statusResponse = new StatusResponse();
            try {
                DelegatingConnection del = new DelegatingConnection(conn);
                OracleConnection connection = (OracleConnection) del.getInnermostDelegate();
                Array arrayStrinb = connection.createARRAY("LISTPARAMETR0STRING", UtilDao.getStructsString("PARAMETR0STRING", connection, parametrosString));
                CallableStatement cs = connection.prepareCall("call " + pkName + ".SP_GLOBAL(?,?,?,?)");
                cs.setArray(1, arrayStrinb);
                cs.registerOutParameter(2, OracleTypes.NUMBER);
                cs.registerOutParameter(3, OracleTypes.VARCHAR);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.execute();
                statusResponse.setStatusCode(cs.getInt(2));
                statusResponse.setStatusText(cs.getString(3));
                productListResponse.setStatus(statusResponse);
                ResultSet rs = (ResultSet) cs.getObject(4);
                List<Product> productos=new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Product product=new Product();
                        product.setCode(rs.getString("COD_ITEM"));
                        product.setDescription(rs.getString("DESC_ITEM"));
                        product.setUri(rs.getString("FOTO"));
                        product.setSugerido(true);
                        productos.add(product);
                    }
                    rs.close();
                    cs.close();
                }
                productListResponse.setProducts(productos);
                 return productListResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                productListResponse.setStatus(statusResponse);
                return productListResponse;

            }
        });
    }

    @Transactional(readOnly = true)
    @Override
    public ProductListResponse getArticulos(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            ProductListResponse productListResponse = new ProductListResponse();
            StatusResponse statusResponse = new StatusResponse();
            try {
                DelegatingConnection del = new DelegatingConnection(conn);
                OracleConnection connection = (OracleConnection) del.getInnermostDelegate();
                Array arrayStrinb = connection.createARRAY("LISTPARAMETR0STRING", UtilDao.getStructsString("PARAMETR0STRING", connection, parametrosString));
                CallableStatement cs = connection.prepareCall("call " + pkName + ".SP_GLOBAL(?,?,?,?)");
                cs.setArray(1, arrayStrinb);
                cs.registerOutParameter(2, OracleTypes.NUMBER);
                cs.registerOutParameter(3, OracleTypes.VARCHAR);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.execute();
                statusResponse.setStatusCode(cs.getInt(2));
                statusResponse.setStatusText(cs.getString(3));
                productListResponse.setStatus(statusResponse);
                ResultSet rs = (ResultSet) cs.getObject(4);
                List<Product> productos=new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Product product=new Product();
                        product.setCode(rs.getString("COD_ITEM"));
                        product.setDescription(rs.getString("DESC_ITEM"));
                        product.setUri(rs.getString("FOTO"));
                        product.setUm(rs.getString("UM_VENTA"));
                        productos.add(product);
                    }
                    rs.close();
                    cs.close();
                }
                productListResponse.setProducts(productos);
                return productListResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                productListResponse.setStatus(statusResponse);
                return productListResponse;

            }
        });
    }

    @Transactional(readOnly = true)
    @Override
    public DisponiblePrecioResponse getDisponiblePrecio(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            DisponiblePrecioResponse disponiblePrecioResponse = new DisponiblePrecioResponse();
            StatusResponse statusResponse = new StatusResponse();
            try {
                DelegatingConnection del = new DelegatingConnection(conn);
                OracleConnection connection = (OracleConnection) del.getInnermostDelegate();
                Array arrayStrinb = connection.createARRAY("LISTPARAMETR0STRING", UtilDao.getStructsString("PARAMETR0STRING", connection, parametrosString));
                CallableStatement cs = connection.prepareCall("call " + pkName + ".SP_GLOBAL(?,?,?,?)");
                cs.setArray(1, arrayStrinb);
                cs.registerOutParameter(2, OracleTypes.NUMBER);
                cs.registerOutParameter(3, OracleTypes.VARCHAR);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.execute();
                statusResponse.setStatusCode(cs.getInt(2));
                statusResponse.setStatusText(cs.getString(3));
                disponiblePrecioResponse.setStatus(statusResponse);
                ResultSet rs = (ResultSet) cs.getObject(4);

                if (rs != null) {
                    while (rs.next()) {
                        disponiblePrecioResponse.setDisponible(rs.getDouble("DISPONIBLE"));
                        disponiblePrecioResponse.setPrecioBase(rs.getDouble("PRECIO_BASE"));
                        disponiblePrecioResponse.setPrecioSugerido(rs.getDouble("PRECIO_SUGERIDO"));
                    }
                    rs.close();
                    cs.close();
                }

                return disponiblePrecioResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                disponiblePrecioResponse.setStatus(statusResponse);
                return disponiblePrecioResponse;

            }
        });
    }
}
