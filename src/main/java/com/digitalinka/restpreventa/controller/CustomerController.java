package com.digitalinka.restpreventa.controller;


import com.digitalinka.restpreventa.model.response.AvancePoliticaListResponse;
import com.digitalinka.restpreventa.model.response.CustomerListResponse;
import com.digitalinka.restpreventa.model.response.CustomerResponse;
import com.digitalinka.restpreventa.model.response.DispatcherAddressListResponse;
import com.digitalinka.restpreventa.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("preventaGps")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("clientesByDiaV3")
    public CustomerListResponse getClientesByDiaV3(@RequestParam(value="usuario") String usuario) throws Exception {
        return customerService.getClienteList(usuario);
    }

    @GetMapping("getCustomerInfo")
    public CustomerResponse getCustomerInfo(@RequestParam(value = "usuario") String usuario, @RequestParam(value = "codCliente") String codCliente) throws Exception {
        Object[] pProcedureCliente = new Object[2];
        pProcedureCliente[0] = "v_PROCEDURE";
        pProcedureCliente[1] = "SP_GET_CLIENTE_INFO";

        Object[] pUsuario = new Object[2];
        pUsuario[0] = "v_USUARIO";
        pUsuario[1] = usuario;

        Object[] pCodCliente = new Object[2];
        pCodCliente[0] = "v_COD_CLIENTE";
        pCodCliente[1] = codCliente;

        List<Object[]> parametrosCliente = new ArrayList<>();
        parametrosCliente.add(pProcedureCliente);
        parametrosCliente.add(pUsuario);
        parametrosCliente.add(pCodCliente);
       CustomerResponse customerResponse= customerService.getCustomerInfo(parametrosCliente);
        if(customerResponse.getStatus().getStatusCode()==1){
            Object[] pProcedureLocales = new Object[2];
            pProcedureLocales[0] = "v_PROCEDURE";
            pProcedureLocales[1] = "SP_GET_LOCALES_BY_CLIENTE";

            List<Object[]> parametrosLocales = new ArrayList<>();
            parametrosLocales.add(pProcedureLocales);
            parametrosLocales.add(pUsuario);
            parametrosLocales.add(pCodCliente);
            DispatcherAddressListResponse dispatcherAddressListResponse=customerService.getAdresses(parametrosLocales);
               if(dispatcherAddressListResponse.getStatus().getStatusCode()==1){
                   customerResponse.setAddresses(dispatcherAddressListResponse.getAddresses());
               }

        }


        return customerResponse;
    }


}
