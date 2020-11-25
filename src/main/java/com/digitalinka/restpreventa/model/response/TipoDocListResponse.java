package com.digitalinka.restpreventa.model.response;

import com.digitalinka.restpreventa.model.TipoDoc;

import java.util.ArrayList;
import java.util.List;

public class TipoDocListResponse {
    List<TipoDoc>  tipoDocs=new ArrayList<>();
    StatusResponse status;

    public List<TipoDoc> getTipoDocs() {
        return tipoDocs;
    }

    public void setTipoDocs(List<TipoDoc> tipoDocs) {
        this.tipoDocs = tipoDocs;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
