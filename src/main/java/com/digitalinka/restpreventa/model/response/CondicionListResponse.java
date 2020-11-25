package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.CondicionPago;

import java.util.ArrayList;
import java.util.List;

public class CondicionListResponse {
    private List<CondicionPago> condiciones=new ArrayList<>();
    private StatusResponse status;

    public List<CondicionPago> getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(List<CondicionPago> condiciones) {
        this.condiciones = condiciones;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
