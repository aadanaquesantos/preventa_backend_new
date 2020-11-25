package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.Almacen;

import java.util.ArrayList;
import java.util.List;

public class AlmacenListResponse {
    private List<Almacen> almacenes=new ArrayList<>();
    private StatusResponse status;

    public List<Almacen> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List<Almacen> almacenes) {
        this.almacenes = almacenes;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
