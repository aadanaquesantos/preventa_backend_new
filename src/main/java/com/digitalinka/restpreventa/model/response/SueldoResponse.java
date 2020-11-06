package com.digitalinka.restpreventa.model.response;

public class SueldoResponse {
    private Double sueldo;
    private Double cuota;
    private Double avanceCuota;
    StatusResponse status;

    public Double getCuota() {
        return cuota;
    }

    public void setCuota(Double cuota) {
        this.cuota = cuota;
    }

    public Double getAvanceCuota() {
        return avanceCuota;
    }

    public void setAvanceCuota(Double avanceCuota) {
        this.avanceCuota = avanceCuota;
    }

    public Double getSueldo() {
        return sueldo;
    }

    public void setSueldo(Double sueldo) {
        this.sueldo = sueldo;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
