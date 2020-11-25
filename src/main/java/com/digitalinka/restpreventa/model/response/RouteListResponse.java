package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.Route;

import java.util.List;

public class RouteListResponse {
    private List<Route> routes;
    private StatusResponse status;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
