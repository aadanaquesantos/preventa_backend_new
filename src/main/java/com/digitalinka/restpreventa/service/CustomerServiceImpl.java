package com.digitalinka.restpreventa.service;


import com.digitalinka.restpreventa.dao.CustomerDao;
import com.digitalinka.restpreventa.model.response.CustomerListResponse;
import com.digitalinka.restpreventa.model.response.CustomerResponse;
import com.digitalinka.restpreventa.model.response.DispatcherAddressListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao customerDao;

    @Override
    public CustomerListResponse getClienteList(String usuario) {
        return customerDao.getClienteList(usuario);
    }

    @Override
    public CustomerResponse getCustomerInfo(List<Object[]> parametrosString) {
         return customerDao.getCustomerInfo(parametrosString);
    }

    @Override
    public DispatcherAddressListResponse getAdresses(List<Object[]> parametrosString) {
        return customerDao.getAdresses(parametrosString);
    }
}
