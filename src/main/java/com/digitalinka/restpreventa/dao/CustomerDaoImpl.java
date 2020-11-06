package com.digitalinka.restpreventa.dao;


import com.digitalinka.restpreventa.UtilDao;
import com.digitalinka.restpreventa.model.*;
import com.digitalinka.restpreventa.model.response.*;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleConnection;
import org.apache.commons.dbcp2.DelegatingConnection;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
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
    public CustomerListResponse getClienteList(String usuario) {
            return sessionFactory.getCurrentSession().doReturningWork(new ReturningWork<CustomerListResponse>() {
                @Override
                public CustomerListResponse execute(Connection connection) throws SQLException {
                    CustomerListResponse customerListResponse = new CustomerListResponse();
                    StatusResponse statusResponse=new StatusResponse();
                    try {
                        CallableStatement cs = connection.prepareCall(" { call "+nombrePaquete+".CLIENTES_BY_DIAV2(?,?,?)}");
                        cs.setString(
                                1,usuario);
                        cs.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
                        cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                        cs.execute();
                     //   String ignoresec=cs.getString(2);
                        ResultSet rs = (ResultSet) cs.getObject(3);
                        List<Customer> customers=new ArrayList<>();
                        //int i=0;
                        if (rs!= null) {
                            while (rs.next()) {

                                Customer customer=new Customer();
                                customer.setCode(rs.getString(1));
                                customer.setDescription(rs.getString(2));
                                customer.setAddress(rs.getString(3));
                                customer.setStatus(rs.getString(12));


                                customers.add(customer);

                            }
                            rs.close();
                        }


                        statusResponse.setStatusCode(1);
                        statusResponse.setStatusText("OK");
                        customerListResponse.setCustomers(customers);
                        customerListResponse.setStatus(statusResponse);
                    } catch (Exception e) {
                        statusResponse.setStatusCode(-1);
                        statusResponse.setStatusText(e.getMessage());
                        customerListResponse.setStatus(statusResponse);

                    }
                    return customerListResponse;
                }

            });





    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse getCustomerInfo(List<Object[]> parametrosString) {
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
                       customer.setStatus(rs.getString("DIR_FACTURACION"));
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
                        Route route=new Route();
                        route.setCode(rs.getString("COD_RUTA"));
                        route.setDescription(rs.getString("DESC_RUTA"));
                        route.setDivision(rs.getString("DIVISION"));
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
}
