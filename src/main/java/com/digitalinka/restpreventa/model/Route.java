package com.digitalinka.restpreventa.model;

public class Route {
    private String code;
    private String description;
    private DivisionEmpresa division;
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public DivisionEmpresa getDivision() {
        return division;
    }

    public void setDivision(DivisionEmpresa division) {
        this.division = division;
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
