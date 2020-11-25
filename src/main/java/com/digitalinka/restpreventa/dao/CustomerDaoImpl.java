package com.digitalinka.restpreventa.dao;


import com.digitalinka.restpreventa.UtilDao;
import com.digitalinka.restpreventa.model.*;
import com.digitalinka.restpreventa.model.response.*;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleConnection;
import org.apache.commons.dbcp2.DelegatingConnection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl  implements CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${nombrePaquete}")
    protected String nombrePaquete;


    @Value("${pkName}")
    protected String pkName;

    @Transactional(readOnly = true)
    @Override
    public CustomerLocalListResponse getClienteList(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            CustomerLocalListResponse customerListResponse=new CustomerLocalListResponse();
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
                customerListResponse.setStatus(statusResponse);
                ResultSet rs = (ResultSet) cs.getObject(4);
                List<CustomerLocal> customerLocals=new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        CustomerLocal customerLocal=new CustomerLocal();
                                Customer customer=new Customer();
                                customer.setCode(rs.getString("CODCLIENTE"));
                                customer.setDescription(rs.getString("DESCCLIENTE"));
                                customer.setStatus(rs.getString("ESTADOCLIENTE"));
                                DispatchAddress dispatchAddress=new DispatchAddress();
                                dispatchAddress.setCode(rs.getString("CODLOCAL"));
                                dispatchAddress.setDescription(rs.getString("DIRECCION"));

                                customerLocal.setCustomer(customer);
                                customerLocal.setDispatchAddress(dispatchAddress);

                                customerLocals.add(customerLocal);
                    }
                    rs.close();
                    cs.close();
                }
                customerListResponse.setCustomerLocals(customerLocals);

                return customerListResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                customerListResponse.setStatus(statusResponse);
                return customerListResponse;

            }
        });

    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse getCustomer(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            CustomerResponse customerResponse=new CustomerResponse();
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
                customerResponse.setStatus(statusResponse);
                ResultSet rs = (ResultSet) cs.getObject(4);

                if (rs != null) {
                    while (rs.next()) {
                       Customer customer=new Customer();
                       customer.setCode(rs.getString("COD_CLIENTE"));
                       customer.setDescription(rs.getString("DESC_CLIENTE"));
                       customer.setAddress(rs.getString("DIR_FACTURACION"));
                       customer.setDni(rs.getString("DNI"));
                       customer.setRuc(rs.getString("RUC"));
                       customer.setPhone(rs.getString("TELEFONO_FIJO"));
                       customer.setCellPhone(rs.getString("CELULAR"));
                       customer.setEmail(rs.getString("EMAIL"));
                       customer.setStatus(rs.getString("STATUS"));
                        customerResponse.setCustomer(customer);
                    }
                    rs.close();
                    cs.close();
                }

                return customerResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                customerResponse.setStatus(statusResponse);
                return customerResponse;

            }
        });
    }

    @Transactional(readOnly = true)
    @Override
    public DispatcherAddressListResponse getAdresses(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            DispatcherAddressListResponse dispatcherAddressListResponse=new DispatcherAddressListResponse();
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
                dispatcherAddressListResponse.setStatus(statusResponse);
                ResultSet rs = (ResultSet) cs.getObject(4);
                List<DispatchAddress> addresses=new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        DispatchAddress address=new DispatchAddress();
                        address.setCode(rs.getString("COD_LOCAL"));
                        address.setDescription(rs.getString("DIRECCION"));
                        address.setStatusLocal(rs.getString("STATUS_LOCAL"));
                        Route route=new Route();
                        route.setCode(rs.getString("COD_RUTA"));
                        route.setDescription(rs.getString("DESC_RUTA"));
                        DivisionEmpresa divisionEmpresa=new DivisionEmpresa();
                        divisionEmpresa.setCode(rs.getString("COD_DIVISION"));
                        divisionEmpresa.setDescription(rs.getString("DESC_DIVISION"));
                        route.setDivision(divisionEmpresa);
                        Company company=new Company();
                        company.setCode(rs.getString("COD_EMPRESA"));
                        company.setDescription(rs.getString("DESC_EMPRESA"));
                        route.setCompany(company);
                        address.setRoute(route);
                        addresses.add(address);
                    }
                    rs.close();
                    cs.close();
                }
            dispatcherAddressListResponse.setAddresses(addresses);

                return dispatcherAddressListResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                dispatcherAddressListResponse.setStatus(statusResponse);
                return dispatcherAddressListResponse;

            }
        });
    }

    @Transactional(readOnly = true)
    @Override
    public RouteListResponse getRoutesByDia(List<Object[]> parametrosString) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public DispatchAddressResponse getAdressPedido(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            DispatchAddressResponse dispatchAddressResponse=new DispatchAddressResponse();
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
                dispatchAddressResponse.setStatus(statusResponse);
                ResultSet rs = (ResultSet) cs.getObject(4);
                DispatchAddress dispatchAddress=new DispatchAddress();
                if (rs != null) {
                    while (rs.next()) {
                        dispatchAddress.setCode(rs.getString("COD_LOCAL"));
                        dispatchAddress.setDescription(rs.getString("DIRECCION"));
                        dispatchAddress.setStatusLocal(rs.getString("STATUS_LOCAL"));
                        Route route=new Route();
                        route.setCode(rs.getString("COD_RUTA"));
                        route.setDescription(rs.getString("DESC_RUTA"));
                        DivisionEmpresa division=new DivisionEmpresa();
                        division.setCode(rs.getString("COD_DIVISION"));
                        division.setDescription(rs.getString("DESC_DIVISION"));
                        route.setDivision(division);
                        Company company=new Company();
                        company.setCode(rs.getString("COD_EMPRESA"));
                        company.setDescription(rs.getString("DESC_EMPRESA"));
                        route.setCompany(company);

                        ListaPrecios lp=new ListaPrecios();
                        lp.setCode(rs.getString("COD_LISTA"));
                        lp.setDescription(rs.getString("DESC_LISTA"));
                        dispatchAddress.setListaPrecios(lp);
                        dispatchAddress.setRoute(route);

                    }
                    rs.close();
                    cs.close();
                }
                dispatchAddressResponse.setDispatchAddress(dispatchAddress);

                return dispatchAddressResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                dispatchAddressResponse.setStatus(statusResponse);
                return dispatchAddressResponse;

            }
        });
    }

    @Transactional(readOnly = true)
    @Override
    public CondicionListResponse getCondiciones(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            CondicionListResponse condicionListResponse=new CondicionListResponse();
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
                condicionListResponse.setStatus(statusResponse);
                ResultSet rs = (ResultSet) cs.getObject(4);
                List<CondicionPago> condiciones=new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        CondicionPago condicion=new CondicionPago();
                        condicion.setCode(rs.getString("COD_CONDICION"));
                        condicion.setDescription(rs.getString("DESC_CONDICION"));
                        condiciones.add(condicion);

                    }
                    rs.close();
                    cs.close();
                }
                condicionListResponse.setCondiciones(condiciones);

                return condicionListResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                condicionListResponse.setStatus(statusResponse);
                return condicionListResponse;

            }
        });
    }

    @Transactional(readOnly = true)
    @Override
    public TipoDocListResponse getTiposDocs(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            TipoDocListResponse tipoDocListResponse=new TipoDocListResponse();
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
                tipoDocListResponse.setStatus(statusResponse);
                ResultSet rs = (ResultSet) cs.getObject(4);
                List<TipoDoc> tipoDocs=new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        TipoDoc tipoDoc=new TipoDoc();
                        tipoDoc.setCode(rs.getString("COD_TIPO_DOC"));
                        tipoDoc.setDescription(rs.getString("DESC_TIPO_DOC"));
                        tipoDocs.add(tipoDoc);

                    }
                    rs.close();
                    cs.close();
                }
                tipoDocListResponse.setTipoDocs(tipoDocs);

                return tipoDocListResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                tipoDocListResponse.setStatus(statusResponse);
                return tipoDocListResponse;

            }
        });
    }
}
