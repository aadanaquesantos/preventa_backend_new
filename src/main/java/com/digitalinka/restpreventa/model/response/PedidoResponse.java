package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.Pedido;

public class PedidoResponse {
    private Pedido pedido;
    private StatusResponse status;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
