package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.DispatchAddress;

import java.util.ArrayList;
import java.util.List;

public class DispatcherAddressListResponse {
    private List<DispatchAddress> addresses=new ArrayList<>();
    StatusResponse status;

    public List<DispatchAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<DispatchAddress> addresses) {
        this.addresses = addresses;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
