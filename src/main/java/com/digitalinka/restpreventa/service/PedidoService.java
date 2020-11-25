package com.digitalinka.restpreventa.service;

import com.digitalinka.restpreventa.model.request.CarritoRequest;
import com.digitalinka.restpreventa.model.response.PedidoResponse;

public interface PedidoService {
    PedidoResponse saveCarrito(CarritoRequest carritoRequest);

}
