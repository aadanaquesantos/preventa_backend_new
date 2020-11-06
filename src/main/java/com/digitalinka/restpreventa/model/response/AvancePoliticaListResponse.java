package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.AvancePolitica;

import java.util.List;

public class AvancePoliticaListResponse {
    private List<AvancePolitica> avances;
    private StatusResponse status;

    public List<AvancePolitica> getAvances() {
        return avances;
    }

    public void setAvances(List<AvancePolitica> avances) {
        this.avances = avances;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
