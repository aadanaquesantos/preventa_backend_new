package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.Customer;
import com.digitalinka.restpreventa.model.CustomerLocal;

import java.util.List;
//todo ok
public class CustomerLocalListResponse {

    private List<CustomerLocal> customerLocals;
    private StatusResponse status;

    public List<CustomerLocal> getCustomerLocals() {
        return customerLocals;
    }

    public void setCustomerLocals(List<CustomerLocal> customerLocals) {
        this.customerLocals = customerLocals;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
