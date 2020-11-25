package com.digitalinka.restpreventa.dao;

import com.digitalinka.restpreventa.UtilDao;
import com.digitalinka.restpreventa.model.*;
import com.digitalinka.restpreventa.model.response.*;
import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.OracleTypes;
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
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${nombrePaquete}")
    protected String nombrePaquete;


    @Value("${pkName}")
    protected String pkName;

    @Transactional(readOnly = false)
    @Override
    public UserResponse loginByUserEmail(String userEmail, String password) {
        return sessionFactory.getCurrentSession().doReturningWork(connection -> {
            UserResponse userResponse = new UserResponse();
            StatusResponse statusResponse = new StatusResponse();
            try {
                CallableStatement cs = connection.prepareCall(" { call " + nombrePaquete + ".LOGIN_USER_EMAIL(?,?,?,?,?)}");
                cs.setString(1, userEmail);
                cs.setString(2, password);
                cs.registerOutParameter(3, OracleTypes.NUMBER);
                cs.registerOutParameter(4, OracleTypes.VARCHAR);
                cs.registerOutParameter(5, OracleTypes.CURSOR);
                cs.execute();
                statusResponse.setStatusCode(cs.getInt(3));
                statusResponse.setStatusText(cs.getString(4));
                ResultSet rs = (ResultSet) cs.getObject(5);
                User user = new User();
                if (rs != null) {
                    while (rs.next()) {
                        user.setUserEmail(rs.getString(1));
                        user.setFullName(rs.getString(2));
                    }
                    rs.close();
                    cs.close();
                }
                userResponse.setUser(user);
                userResponse.setStatus(statusResponse);
            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                userResponse.setStatus(statusResponse);
            }
            return userResponse;
        });

    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse userByDni(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            UserResponse userResponse = new UserResponse();
            StatusResponse statusResponse = new StatusResponse();
            try {
                DelegatingConnection del = new DelegatingConnection(conn);
                OracleConnection connection = (OracleConnection) del.getInnermostDelegate();
                Array arrayStrinb = connection.createARRAY("LISTPARAMETR0STRING", UtilDao.getStructsString("PARAMETR0STRING", connection, parametrosString));
                CallableStatement cs = connection.prepareCall("call " + nombrePaquete + ".SP_LIU_USER(?,?,?,?)");
                cs.setArray(1, arrayStrinb);
                cs.registerOutParameter(2, OracleTypes.NUMBER);
                cs.registerOutParameter(3, OracleTypes.VARCHAR);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.execute();
                statusResponse.setStatusCode(cs.getInt(2));
                statusResponse.setStatusText(cs.getString(3));
                ResultSet rs = (ResultSet) cs.getObject(4);
                User user = new User();
                if (rs != null) {
                    while (rs.next()) {
                        user.setUsername(rs.getString("USUARIO"));
                        user.setFullName(rs.getString("NOMBRE"));
                        user.setEmail(rs.getString("EMAIL"));
                    }
                    rs.close();
                    cs.close();
                }
                userResponse.setUser(user);
                userResponse.setStatus(statusResponse);
                return userResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                userResponse.setStatus(statusResponse);
                return userResponse;

            }
        });

    }

    @Transactional(readOnly = false)
    @Override
    public StatusResponse updateUserLogin(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
             StatusResponse statusResponse = new StatusResponse();
            try {
                DelegatingConnection del = new DelegatingConnection(conn);
                OracleConnection connection = (OracleConnection) del.getInnermostDelegate();
                Array arrayStrinb = connection.createARRAY("LISTPARAMETR0STRING", UtilDao.getStructsString("PARAMETR0STRING", connection, parametrosString));
                CallableStatement cs = connection.prepareCall("call " + nombrePaquete + ".SP_LIU_USER(?,?,?,?)");
                cs.setArray(1, arrayStrinb);
                cs.registerOutParameter(2, OracleTypes.NUMBER);
                cs.registerOutParameter(3, OracleTypes.VARCHAR);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.execute();
                statusResponse.setStatusCode(cs.getInt(2));
                statusResponse.setStatusText(cs.getString(3));

                return statusResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                return statusResponse;

            }
        });
    }

    @Transactional(readOnly = false)
    @Override
    public SueldoResponse getSueldo(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            SueldoResponse sueldoResponse = new SueldoResponse();
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
                ResultSet rs = (ResultSet) cs.getObject(4);

                if (rs != null) {
                    while (rs.next()) {
                      sueldoResponse.setSueldo(rs.getDouble("SUELDO"));
                      sueldoResponse.setCuota(rs.getDouble("CUOTA"));
                      sueldoResponse.setAvanceCuota(rs.getDouble("AVANCECUOTA"));
                    }
                    rs.close();
                    cs.close();
                }

                sueldoResponse.setStatus(statusResponse);
                return sueldoResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                sueldoResponse.setStatus(statusResponse);
                return sueldoResponse;

            }
        });
    }

    @Transactional(readOnly = false)
    @Override
    public PeriodoListResponse getPeriodos(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            PeriodoListResponse periodoListResponse = new PeriodoListResponse();
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
                ResultSet rs = (ResultSet) cs.getObject(4);
                List<Periodo> periodos=new ArrayList<>();
                if (rs != null) {
                    while (rs.next()) {
                        Periodo periodo=new Periodo();
                        periodo.setAnnio(rs.getString("ANNIO"));
                        periodo.setDescription(rs.getString("DESCRIPTION"));
                        periodo.setStartDate(rs.getString("START_DATE"));
                        periodo.setEndDate(rs.getString("END_DATE"));
                        periodos.add(periodo);
                    }
                    rs.close();
                    cs.close();
                }

                periodoListResponse.setStatus(statusResponse);
                periodoListResponse.setPeriodos(periodos);
                return periodoListResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                periodoListResponse.setStatus(statusResponse);
                return periodoListResponse;

            }
        });
    }

    @Transactional(readOnly = true)
    @Override
    public AvanceResponse getAvanceVentas(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            AvanceResponse avanceResponse=new AvanceResponse();
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
                ResultSet rs = (ResultSet) cs.getObject(4);

                if (rs != null) {
                    while (rs.next()) {

                        avanceResponse.setTotalDias(rs.getInt("TOTAL_DIAS"));
                        avanceResponse.setAvanceDias(rs.getInt("AVANCE_DIAS"));
                        avanceResponse.setRestoDias(rs.getInt("RESTO_DIAS"));
                        avanceResponse.setLinealidad(rs.getDouble("LINEALIDAD_DIA"));
                        avanceResponse.setNecesidadDiaria(rs.getDouble("NECESIDAD_DIA"));
                        avanceResponse.setProyección(rs.getDouble("PROYECTADO"));
                        avanceResponse.setClientesAtendidos(rs.getInt("CLIENTES_ATENDIDOS"));

                    }
                    rs.close();
                    cs.close();
                }

                avanceResponse.setStatus(statusResponse);
                 return avanceResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                avanceResponse.setStatus(statusResponse);
                return avanceResponse;

            }
        });
    }

    @Transactional(readOnly =true )
    @Override
    public AvanceProveedorListResponse getAvanceProveedor(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            AvanceProveedorListResponse avanceProveedorResponse=new AvanceProveedorListResponse();
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
                ResultSet rs = (ResultSet) cs.getObject(4);

                List<AvanceProveedor> avances=new ArrayList<>();

                if (rs != null) {
                    while (rs.next()) {
                        AvanceProveedor avanceProveedor=new AvanceProveedor();
                        avanceProveedor.setCodProveedor(rs.getString("COD_PROVEEDOR"));
                        avanceProveedor.setDescProveedor(rs.getString("DESC_PROVEEDOR"));
                        avanceProveedor.setAvance(rs.getDouble("AVANCE"));
                        avances.add(avanceProveedor);
                    }
                    rs.close();
                    cs.close();
                }
                avanceProveedorResponse.setAvances(avances);
                avanceProveedorResponse.setStatus(statusResponse);
                return avanceProveedorResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                avanceProveedorResponse.setStatus(statusResponse);
                return avanceProveedorResponse;

            }
        });
    }

    @Transactional(readOnly = true)
    @Override
    public AvancePoliticaListResponse getComisiones(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            AvancePoliticaListResponse avanceProveedorResponse=new AvancePoliticaListResponse();
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
                ResultSet rs = (ResultSet) cs.getObject(4);

                List<AvancePolitica> avances=new ArrayList<>();

                if (rs != null) {
                    while (rs.next()) {
                        AvancePolitica avancePolitica=new AvancePolitica();
                        avancePolitica.setCodPolitica(rs.getString("COD_POLITICA"));
                        avancePolitica.setDescPolitica(rs.getString("DESC_POLITICA"));
                        avancePolitica.setMontoPolitica(rs.getDouble("MONTO_POLITICA"));
                        avances.add(avancePolitica);
                    }
                    rs.close();
                    cs.close();
                }
                avanceProveedorResponse.setAvances(avances);
                avanceProveedorResponse.setStatus(statusResponse);
                return avanceProveedorResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                avanceProveedorResponse.setStatus(statusResponse);
                return avanceProveedorResponse;

            }
        });
    }

    @Transactional(readOnly = true)
    @Override
    public AlmacenListResponse getAlmacenes(List<Object[]> parametrosString) {
        return sessionFactory.getCurrentSession().doReturningWork(conn -> {
            AlmacenListResponse almacenListResponse=new AlmacenListResponse();
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
                almacenListResponse.setStatus(statusResponse);
                ResultSet rs = (ResultSet) cs.getObject(4);
                List<Almacen> almacenes=new ArrayList<>();

                if (rs != null) {
                    while (rs.next()) {
                        Almacen almacen=new Almacen();
                        almacen.setCode(rs.getString("COD_ALMACEN"));
                        almacen.setDescription(rs.getString("DESC_ALMACEN"));
                        almacen.setCodLocalidad(rs.getString("COD_LOCALIDAD"));
                         almacen.setCodMesa(rs.getString("COD_MESA"));
                         almacen.setCodEmpresa(rs.getString("COD_EMPRESA"));
                         almacen.setCodSede(rs.getString("COD_SEDE"));
                         almacen.setCodCanal(rs.getString("COD_CANAL"));
                         almacen.setCodVendedor(rs.getString("COD_VENDEDOR"));
                         almacenes.add(almacen);
                    }
                    rs.close();
                    cs.close();
                }
                almacenListResponse.setAlmacenes(almacenes);
                almacenListResponse.setStatus(statusResponse);
                return almacenListResponse;

            } catch (Exception e) {
                statusResponse.setStatusCode(-1);
                statusResponse.setStatusText(e.getMessage());
                almacenListResponse.setStatus(statusResponse);
                return almacenListResponse;

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
}
