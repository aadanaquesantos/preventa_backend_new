package com.digitalinka.restpreventa.service;


import com.digitalinka.restpreventa.model.response.*;

import java.util.List;

public interface CustomerService {
    CustomerLocalListResponse getClienteList(List<Object[]> parametrosString);
    CustomerResponse getCustomer(List<Object[]> parametrosString);
    DispatcherAddressListResponse getAdresses(List<Object[]> parametrosString);
    DispatchAddressResponse getAdressPedido(List<Object[]> parametrosString);
    CondicionListResponse getCondiciones(List<Object[]> parametrosString);
    TipoDocListResponse getTiposDocs(List<Object[]> parametrosString);


}
