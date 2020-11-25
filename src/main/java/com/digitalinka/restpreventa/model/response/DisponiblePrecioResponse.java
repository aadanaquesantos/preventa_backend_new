package com.digitalinka.restpreventa.model.response;

public class DisponiblePrecioResponse {
    private Double disponible;
    private Double  precioBase;
    private Double precioSugerido;
    private StatusResponse status;

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public Double getDisponible() {
        return disponible;
    }

    public void setDisponible(Double disponible) {
        this.disponible = disponible;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public Double getPrecioSugerido() {
        return precioSugerido;
    }

    public void setPrecioSugerido(Double precioSugerido) {
        this.precioSugerido = precioSugerido;
    }
}
