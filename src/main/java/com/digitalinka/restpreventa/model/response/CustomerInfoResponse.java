package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.Customer;
import com.digitalinka.restpreventa.model.DispatchAddress;

import java.util.List;
//todo ok
public class CustomerInfoResponse {
    private Customer customer;
    private List<DispatchAddress> addresses;
    private StatusResponse status;

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<DispatchAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<DispatchAddress> addresses) {
        this.addresses = addresses;
    }
}
