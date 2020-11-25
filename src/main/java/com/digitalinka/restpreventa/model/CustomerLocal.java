package com.digitalinka.restpreventa.model;

//todo ok
public class CustomerLocal {
    private Customer customer;
    private DispatchAddress dispatchAddress;

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
}
