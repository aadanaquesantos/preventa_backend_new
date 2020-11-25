package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.Customer;

public class CustomerResponse {
    private Customer customer;
    private StatusResponse status;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
