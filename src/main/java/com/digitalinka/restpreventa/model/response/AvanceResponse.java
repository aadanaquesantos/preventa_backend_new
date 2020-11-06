package com.digitalinka.restpreventa.model.response;

public class AvanceResponse {
    private Integer totalDias;
    private Integer restoDias;
    private Integer avanceDias;
    private Double linealidad;
    private Double necesidadDiaria;
    private Integer clientesAtendidos;
    private Double proyección;
    StatusResponse status;

    public Integer getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(Integer totalDias) {
        this.totalDias = totalDias;
    }

    public Integer getRestoDias() {
        return restoDias;
    }

    public void setRestoDias(Integer restoDias) {
        this.restoDias = restoDias;
    }

    public Integer getAvanceDias() {
        return avanceDias;
    }

    public void setAvanceDias(Integer avanceDias) {
        this.avanceDias = avanceDias;
    }

    public Double getLinealidad() {
        return linealidad;
    }

    public void setLinealidad(Double linealidad) {
        this.linealidad = linealidad;
    }

    public Double getNecesidadDiaria() {
        return necesidadDiaria;
    }

    public void setNecesidadDiaria(Double necesidadDiaria) {
        this.necesidadDiaria = necesidadDiaria;
    }

    public Integer getClientesAtendidos() {
        return clientesAtendidos;
    }

    public void setClientesAtendidos(Integer clientesAtendidos) {
        this.clientesAtendidos = clientesAtendidos;
    }

    public Double getProyección() {
        return proyección;
    }

    public void setProyección(Double proyección) {
        this.proyección = proyección;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
