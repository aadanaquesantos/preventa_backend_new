package com.digitalinka.restpreventa.controller;


import com.digitalinka.restpreventa.model.response.*;
import com.digitalinka.restpreventa.service.CustomerService;
import com.digitalinka.restpreventa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("preventaGps")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @GetMapping("getClientesByDia")
    public CustomerLocalListResponse getClientesByDia(@RequestParam(value = "usuario") String usuario) throws Exception {
        Object[] pProcedureCliente = new Object[2];
        pProcedureCliente[0] = "p_PROCEDURE";
        pProcedureCliente[1] = "SP_GET_CLIENTES_DIA";
        Object[] pUsuario = new Object[2];
        pUsuario[0] = "p_USUARIO";
        pUsuario[1] = usuario;
        List<Object[]> parametrosCliente = new ArrayList<>();
        parametrosCliente.add(pProcedureCliente);
        parametrosCliente.add(pUsuario);
        return customerService.getClienteList(parametrosCliente);
    }

    @GetMapping("getCustomerInfo")
    public CustomerInfoResponse getCustomerInfo(@RequestParam(value = "usuario") String usuario, @RequestParam(value = "codCliente") String codCliente) throws Exception {
        CustomerInfoResponse customerInfoResponse = new CustomerInfoResponse();
        Object[] pProcedureCliente = new Object[2];
        pProcedureCliente[0] = "p_PROCEDURE";
        pProcedureCliente[1] = "SP_GET_CLIENTE";
        Object[] pUsuario = new Object[2];
        pUsuario[0] = "p_USUARIO";
        pUsuario[1] = usuario;
        Object[] pCodCliente = new Object[2];
        pCodCliente[0] = "p_COD_CLIENTE";
        pCodCliente[1] = codCliente;
        List<Object[]> parametrosCliente = new ArrayList<>();
        parametrosCliente.add(pProcedureCliente);
        parametrosCliente.add(pUsuario);
        parametrosCliente.add(pCodCliente);
        CustomerResponse customerResponse = customerService.getCustomer(parametrosCliente);
        customerResponse.setStatus(customerResponse.getStatus());
        if (customerResponse.getStatus().getStatusCode() == 1) {
            customerInfoResponse.setCustomer(customerResponse.getCustomer());
            Object[] pProcedureLocales = new Object[2];
            pProcedureLocales[0] = "p_PROCEDURE";
            pProcedureLocales[1] = "SP_GET_LOCALES_BY_CLIENTE";
            List<Object[]> parametrosLocales = new ArrayList<>();
            parametrosLocales.add(pProcedureLocales);
            parametrosLocales.add(pUsuario);
            parametrosLocales.add(pCodCliente);
            DispatcherAddressListResponse dispatcherAddressListResponse = customerService.getAdresses(parametrosLocales);
            customerInfoResponse.setStatus(dispatcherAddressListResponse.getStatus());
            if (dispatcherAddressListResponse.getStatus().getStatusCode() == 1) {
                customerInfoResponse.setAddresses(dispatcherAddressListResponse.getAddresses());
            }

        }
        return customerInfoResponse;
    }


    @GetMapping("getCustomerPedido")
    public CustomerPedidoResponse getCustomerPedido(@RequestParam(value = "usuario") String usuario, @RequestParam(value = "codCliente") String codCliente, @RequestParam(value = "codLocal") String codLocal) throws Exception {
        CustomerPedidoResponse customerPedidoResponse = new CustomerPedidoResponse();
        Object[] pProcedureCliente = new Object[2];
        pProcedureCliente[0] = "p_PROCEDURE";
        pProcedureCliente[1] = "SP_GET_CLIENTE";
        Object[] pUsuario = new Object[2];
        pUsuario[0] = "p_USUARIO";
        pUsuario[1] = usuario;
        Object[] pCodCliente = new Object[2];
        pCodCliente[0] = "p_COD_CLIENTE";
        pCodCliente[1] = codCliente;
        List<Object[]> parametrosCliente = new ArrayList<>();
        parametrosCliente.add(pProcedureCliente);
        parametrosCliente.add(pUsuario);
        parametrosCliente.add(pCodCliente);
        CustomerResponse customerResponse = customerService.getCustomer(parametrosCliente);
        customerPedidoResponse.setStatus(customerResponse.getStatus());
        if (customerResponse.getStatus().getStatusCode() == 1) {
            customerPedidoResponse.setCustomer(customerResponse.getCustomer());
            Object[] pProcedureDireccion = new Object[2];
            pProcedureDireccion[0] = "p_PROCEDURE";
            pProcedureDireccion[1] = "SP_GET_DIRECCION";
            Object[] pLocal = new Object[2];
            pLocal[0] = "p_COD_LOCAL";
            pLocal[1] = codLocal;
            List<Object[]> parametrosLocal = new ArrayList<>();
            parametrosLocal.add(pProcedureDireccion);
            parametrosLocal.add(pCodCliente);
            parametrosLocal.add(pLocal);
            DispatchAddressResponse dispatchAddressResponse = customerService.getAdressPedido(parametrosLocal);
            customerPedidoResponse.setStatus(dispatchAddressResponse.getStatus());
            if (dispatchAddressResponse.getStatus().getStatusCode() == 1) {
                customerPedidoResponse.setDispatchAddress(dispatchAddressResponse.getDispatchAddress());
                Object[] pProcedureAlmacenes = new Object[2];
                pProcedureAlmacenes[0] = "p_PROCEDURE";
                pProcedureAlmacenes[1] = "SP_GET_ALMACENES";
                List<Object[]> parametrosAlmacenes = new ArrayList<>();
                parametrosAlmacenes.add(pProcedureAlmacenes);
                parametrosAlmacenes.add(pUsuario);
                AlmacenListResponse almacenListResponse = userService.getAlmacenes(parametrosAlmacenes);
                customerPedidoResponse.setStatus(almacenListResponse.getStatus());
                if (almacenListResponse.getStatus().getStatusCode() == 1) {
                    customerPedidoResponse.setAlmacenes(almacenListResponse.getAlmacenes());
                    Object[] pProcedureCondiciones = new Object[2];
                    pProcedureCondiciones[0] = "p_PROCEDURE";
                    pProcedureCondiciones[1] = "SP_GET_CONDICIONES";
                    Object[] pCodEmpresa = new Object[2];
                    pCodEmpresa[0] = "p_COD_EMPRESA";
                    pCodEmpresa[1] = customerPedidoResponse.getAlmacenes().get(0).getCodEmpresa();

                    Object[] pCodLocalidad = new Object[2];
                    pCodLocalidad[0] = "p_COD_LOCALIDAD";
                    pCodLocalidad[1] = customerPedidoResponse.getAlmacenes().get(0).getCodLocalidad();
                    Object[] pCodLista = new Object[2];
                    pCodLista[0] = "p_COD_LISTA";
                    pCodLista[1] = customerPedidoResponse.getDispatchAddress().getListaPrecios().getCode();
                    List<Object[]> parametrosCondiciones = new ArrayList<>();
                    parametrosCondiciones.add(pProcedureCondiciones);
                    parametrosCondiciones.add(pCodEmpresa);
                    parametrosCondiciones.add(pCodLocalidad);
                    parametrosCondiciones.add(pCodCliente);
                    parametrosCondiciones.add(pCodLista);
                    CondicionListResponse condicionListResponse = userService.getCondiciones(parametrosCondiciones);
                    customerPedidoResponse.setStatus(condicionListResponse.getStatus());
                    if (condicionListResponse.getStatus().getStatusCode() == 1)
                        customerPedidoResponse.setCondiciones(condicionListResponse.getCondiciones());
                    Object[] pProcedureTipoDocs = new Object[2];
                    pProcedureTipoDocs[0] = "p_PROCEDURE";
                    pProcedureTipoDocs[1] = "SP_GET_TIPO_DOCS";
                    List<Object[]> parametrosTipoDocs = new ArrayList<>();
                    parametrosTipoDocs.add(pProcedureTipoDocs);
                    parametrosTipoDocs.add(pCodCliente);
                    TipoDocListResponse tipoDocListResponse = customerService.getTiposDocs(parametrosTipoDocs);
                    customerPedidoResponse.setStatus(tipoDocListResponse.getStatus());
                    if (tipoDocListResponse.getStatus().getStatusCode() == 1) {
                        customerPedidoResponse.setTipoDocs(tipoDocListResponse.getTipoDocs());

                    }

                }
            }

        }
        return customerPedidoResponse;
    }

}

