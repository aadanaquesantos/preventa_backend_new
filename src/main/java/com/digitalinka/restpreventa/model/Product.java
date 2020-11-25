package com.digitalinka.restpreventa.model;

public class Product {
    private String code;
    private String description;
    private String uri;
    private Double priceBase;
    private Double priceSugerido;
    private String um;
    private boolean isBonif;
    private boolean isSugerido;

    public String getUm() {
        return um;
    }

    public void setUm(String um) {
        this.um = um;
    }

    public boolean isSugerido() {
        return isSugerido;
    }

    public void setSugerido(boolean sugerido) {
        isSugerido = sugerido;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Double getPriceBase() {
        return priceBase;
    }

    public void setPriceBase(Double priceBase) {
        this.priceBase = priceBase;
    }

    public Double getPriceSugerido() {
        return priceSugerido;
    }

    public void setPriceSugerido(Double priceSugerido) {
        this.priceSugerido = priceSugerido;
    }

    public boolean isBonif() {
        return isBonif;
    }

    public void setBonif(boolean bonif) {
        isBonif = bonif;
    }
}
