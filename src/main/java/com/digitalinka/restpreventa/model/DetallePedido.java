package com.digitalinka.restpreventa.model;

public class DetallePedido {
    private Product product;
    private Double importeTotal;
    private Double importeIgv;
    private Double importePercepcion;
    private Double importeDescuentos;
    private Double cantidad;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Double getImporteIgv() {
        return importeIgv;
    }

    public void setImporteIgv(Double importeIgv) {
        this.importeIgv = importeIgv;
    }

    public Double getImportePercepcion() {
        return importePercepcion;
    }

    public void setImportePercepcion(Double importePercepcion) {
        this.importePercepcion = importePercepcion;
    }

    public Double getImporteDescuentos() {
        return importeDescuentos;
    }

    public void setImporteDescuentos(Double importeDescuentos) {
        this.importeDescuentos = importeDescuentos;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
}
