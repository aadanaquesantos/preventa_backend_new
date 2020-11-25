package com.digitalinka.restpreventa.model;

public class Mesa {
    private String code;
    private String description;
    private String codeLocalidad;

    public String getCodeLocalidad() {
        return codeLocalidad;
    }

    public void setCodeLocalidad(String codeLocalidad) {
        this.codeLocalidad = codeLocalidad;
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
}
