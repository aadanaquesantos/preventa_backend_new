package com.digitalinka.restpreventa.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String  usuario;
    private String codEmpresa;
    private String codSede;
    private String codAlmacen;
    private String codLocalidad;
    private String codMesa;
    private String codVendedor;
    private String codCondicion;
    private String codListaPrecios;
    private String codLocal;
    private String codTipoDoc;
    private String codCanal;
    private String codRuta;
    private String codCliente;
    private Double totalImporte;
    private Double  totalDescuentos;
    private Double totalIgv;
    private Double  totalPercepcion;
    private Double totalFlete;
    private List<DetallePedido> detallePedidos=new ArrayList<>();

    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCodSede() {
        return codSede;
    }

    public void setCodSede(String codSede) {
        this.codSede = codSede;
    }

    public String getCodAlmacen() {
        return codAlmacen;
    }

    public void setCodAlmacen(String codAlmacen) {
        this.codAlmacen = codAlmacen;
    }

    public String getCodLocalidad() {
        return codLocalidad;
    }

    public void setCodLocalidad(String codLocalidad) {
        this.codLocalidad = codLocalidad;
    }

    public String getCodMesa() {
        return codMesa;
    }

    public void setCodMesa(String codMesa) {
        this.codMesa = codMesa;
    }

    public String getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(String codVendedor) {
        this.codVendedor = codVendedor;
    }

    public String getCodCondicion() {
        return codCondicion;
    }

    public void setCodCondicion(String codCondicion) {
        this.codCondicion = codCondicion;
    }

    public String getCodListaPrecios() {
        return codListaPrecios;
    }

    public void setCodListaPrecios(String codListaPrecios) {
        this.codListaPrecios = codListaPrecios;
    }

    public String getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(String codLocal) {
        this.codLocal = codLocal;
    }

    public String getCodTipoDoc() {
        return codTipoDoc;
    }

    public void setCodTipoDoc(String codTipoDoc) {
        this.codTipoDoc = codTipoDoc;
    }

    public String getCodCanal() {
        return codCanal;
    }

    public void setCodCanal(String codCanal) {
        this.codCanal = codCanal;
    }

    public String getCodRuta() {
        return codRuta;
    }

    public void setCodRuta(String codRuta) {
        this.codRuta = codRuta;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public Double getTotalImporte() {
        return totalImporte;
    }

    public void setTotalImporte(Double totalImporte) {
        this.totalImporte = totalImporte;
    }

    public Double getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(Double totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public Double getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(Double totalIgv) {
        this.totalIgv = totalIgv;
    }

    public Double getTotalPercepcion() {
        return totalPercepcion;
    }

    public void setTotalPercepcion(Double totalPercepcion) {
        this.totalPercepcion = totalPercepcion;
    }

    public Double getTotalFlete() {
        return totalFlete;
    }

    public void setTotalFlete(Double totalFlete) {
        this.totalFlete = totalFlete;
    }
}
