package com.digitalinka.restpreventa.dao;

import com.digitalinka.restpreventa.model.request.CarritoRequest;
import com.digitalinka.restpreventa.model.response.PedidoResponse;

public interface PedidoDao {

    PedidoResponse saveCarrito(CarritoRequest carritoRequest);

}
