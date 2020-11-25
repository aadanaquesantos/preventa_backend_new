package com.digitalinka.restpreventa.service;


import com.digitalinka.restpreventa.dao.CustomerDao;
import com.digitalinka.restpreventa.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao customerDao;


    @Override
    public CustomerLocalListResponse getClienteList(List<Object[]> parametrosString) {
        return customerDao.getClienteList(parametrosString);
    }

    @Override
    public CustomerResponse getCustomer(List<Object[]> parametrosString) {
         return customerDao.getCustomer(parametrosString);
    }

    @Override
    public DispatcherAddressListResponse getAdresses(List<Object[]> parametrosString) {
        return customerDao.getAdresses(parametrosString);
    }

    @Override
    public DispatchAddressResponse getAdressPedido(List<Object[]> parametrosString) {
        return customerDao.getAdressPedido(parametrosString);
    }

    @Override
    public CondicionListResponse getCondiciones(List<Object[]> parametrosString) {
        return customerDao.getCondiciones(parametrosString);
    }

    @Override
    public TipoDocListResponse getTiposDocs(List<Object[]> parametrosString) {
        return customerDao.getTiposDocs(parametrosString);
    }
}
