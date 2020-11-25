package com.digitalinka.restpreventa.service;

import com.digitalinka.restpreventa.dao.CustomerDao;
import com.digitalinka.restpreventa.dao.PedidoDao;
import com.digitalinka.restpreventa.model.request.CarritoRequest;
import com.digitalinka.restpreventa.model.response.PedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoDao pedidoDao;

    @Override
    public PedidoResponse saveCarrito(CarritoRequest carritoRequest) {
        return pedidoDao.saveCarrito(carritoRequest);
    }
}
