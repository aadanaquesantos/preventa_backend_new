package com.digitalinka.restpreventa.model;

public class Vendedor {
    private String code;
    private String description;
    private Mesa mesa;

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
}
