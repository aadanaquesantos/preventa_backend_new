package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.Customer;
import com.digitalinka.restpreventa.model.DispatchAddress;
import jdk.net.SocketFlow;

public class DispatchAddressResponse {
    private Customer customer;
    private DispatchAddress dispatchAddress;
    private StatusResponse status;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DispatchAddress getDispatchAddress() {
        return dispatchAddress;
    }

    public void setDispatchAddress(DispatchAddress dispatchAddress) {
        this.dispatchAddress = dispatchAddress;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
