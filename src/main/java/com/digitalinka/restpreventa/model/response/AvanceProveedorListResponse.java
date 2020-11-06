package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.AvanceProveedor;

import java.util.ArrayList;
import java.util.List;

public class AvanceProveedorListResponse {

    private List<AvanceProveedor> avances=new ArrayList<>();
    private StatusResponse status;

    public List<AvanceProveedor> getAvances() {
        return avances;
    }

    public void setAvances(List<AvanceProveedor> avances) {
        this.avances = avances;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
