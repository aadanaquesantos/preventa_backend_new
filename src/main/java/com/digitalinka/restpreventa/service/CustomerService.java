package com.digitalinka.restpreventa.service;


import com.digitalinka.restpreventa.model.response.CustomerListResponse;
import com.digitalinka.restpreventa.model.response.CustomerResponse;
import com.digitalinka.restpreventa.model.response.DispatcherAddressListResponse;

import java.util.List;

public interface CustomerService {
    CustomerListResponse getClienteList(String usuario);
    CustomerResponse getCustomerInfo(List<Object[]> parametrosString);
    DispatcherAddressListResponse getAdresses(List<Object[]> parametrosString);

}
