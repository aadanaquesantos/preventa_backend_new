package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.*;

import java.util.ArrayList;
import java.util.List;
//todo ok
public class CustomerPedidoResponse {
    private Customer customer;
    private DispatchAddress dispatchAddress;
    private List<Almacen> almacenes=new ArrayList<>();
    private List<CondicionPago> condiciones=new ArrayList<>();
    private List<TipoDoc> tipoDocs=new ArrayList<>();
    private StatusResponse status;

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public List<TipoDoc> getTipoDocs() {
        return tipoDocs;
    }

    public void setTipoDocs(List<TipoDoc> tipoDocs) {
        this.tipoDocs = tipoDocs;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DispatchAddress getDispatchAddress() {
        return dispatchAddress;
    }

    public void setDispatchAddress(DispatchAddress dispatchAddress) {
        this.dispatchAddress = dispatchAddress;
    }


    public List<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    public List<CondicionPago> getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(List<CondicionPago> condiciones) {
        this.condiciones = condiciones;
    }
}
