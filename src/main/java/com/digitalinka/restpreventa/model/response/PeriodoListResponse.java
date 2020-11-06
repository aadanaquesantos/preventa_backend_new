package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.Periodo;

import java.util.List;

public class PeriodoListResponse {
    private List<Periodo> periodos;
    private StatusResponse status;

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
