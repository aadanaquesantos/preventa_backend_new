package com.digitalinka.restpreventa.model;

public class DispatchAddress {
    private String code;
    private String description;
    private Route route;
    private String statusLocal;
    private ListaPrecios listaPrecios;

    public ListaPrecios getListaPrecios() {
        return listaPrecios;
    }

    public void setListaPrecios(ListaPrecios listaPrecios) {
        this.listaPrecios = listaPrecios;
    }

    public String getStatusLocal() {
        return statusLocal;
    }

    public void setStatusLocal(String statusLocal) {
        this.statusLocal = statusLocal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
