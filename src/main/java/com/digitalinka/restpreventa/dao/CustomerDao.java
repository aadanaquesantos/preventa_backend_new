package com.digitalinka.restpreventa.dao;


import com.digitalinka.restpreventa.model.response.*;

import java.util.List;

public interface CustomerDao {
    CustomerLocalListResponse getClienteList(List<Object[]> parametrosString);
    CustomerResponse getCustomer(List<Object[]> parametrosString);//todo ok
    DispatcherAddressListResponse getAdresses(List<Object[]> parametrosString);
    RouteListResponse getRoutesByDia(List<Object[]> parametrosString);
    DispatchAddressResponse getAdressPedido(List<Object[]> parametrosString);
    CondicionListResponse getCondiciones(List<Object[]> parametrosString);
    TipoDocListResponse getTiposDocs(List<Object[]> parametrosString);
}
