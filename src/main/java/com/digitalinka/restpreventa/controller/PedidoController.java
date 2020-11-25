package com.digitalinka.restpreventa.controller;

import com.digitalinka.restpreventa.model.request.CarritoRequest;
import com.digitalinka.restpreventa.model.response.CustomerLocalListResponse;
import com.digitalinka.restpreventa.model.response.PedidoResponse;
import com.digitalinka.restpreventa.service.CustomerService;
import com.digitalinka.restpreventa.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("preventaGps")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @PostMapping("saveCarrito")
    public PedidoResponse saveCarrito(@RequestBody CarritoRequest carritoRequest) throws Exception {
        return pedidoService.saveCarrito(carritoRequest);
    }

}
