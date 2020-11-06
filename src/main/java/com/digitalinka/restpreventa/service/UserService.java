package com.digitalinka.restpreventa.service;


import com.digitalinka.restpreventa.model.response.*;

import java.util.List;

public interface UserService {
    UserResponse loginByUserEmail(String userEmail,String password);
    UserResponse userByDni(List<Object[]> parametrosString);
    StatusResponse updateUserLogin(List<Object[]> parametrosString);
    SueldoResponse getSueldo(List<Object[]> parametrosString);
    PeriodoListResponse getPeriodos(List<Object[]> parametrosString);
    AvanceResponse getAvanceVentas(List<Object[]> parametrosString);
    AvanceProveedorListResponse getAvanceProveedor(List<Object[]> parametrosString);
    AvancePoliticaListResponse getComisiones(List<Object[]> parametrosString);

}
