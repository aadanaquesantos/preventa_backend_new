package com.digitalinka.restpreventa.controller;

import com.digitalinka.restpreventa.model.response.*;
import com.digitalinka.restpreventa.model.User;
import com.digitalinka.restpreventa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("preventaGps")
public class UserController {

    @Autowired
    private UserService userService;





    @PostMapping("login")
    public UserResponse getClientesByDiaV3(@RequestBody User user) throws Exception {
        return userService.loginByUserEmail(user.getUserEmail(), user.getPassword());
    }


    @GetMapping("userByEmail")
    public UserResponse userByEmail(@RequestParam(value = "dni") String dni) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "LI_USER_BY_DNI";
        Object[] pDni = new Object[2];
        pDni[0] = "v_DNI";
        pDni[1] = dni;
        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pDni);
        return userService.userByDni(parametros);
    }

    @PutMapping("updateUserLogin")
    public StatusResponse updateUserLogin(@RequestBody User user) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "UP_USER_LOGIN";

        Object[] pUsername = new Object[2];
        pUsername[0] = "v_USERNAME";
        pUsername[1] = user.getUsername();

        Object[] pPassword = new Object[2];
        pPassword[0] = "v_PASSWORD";
        pPassword[1] = user.getPassword();

        Object[] pCelular = new Object[2];
        pCelular[0] = "v_CELULAR";
        pCelular[1] = user.getCelular();

        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pUsername);
        parametros.add(pPassword);
        parametros.add(pCelular);
        return userService.updateUserLogin(parametros);
    }



    @GetMapping("getSueldo")
    public SueldoResponse getSueldo(@RequestParam(value = "usuario") String usuario) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "SP_GET_SUELDO";
        Object[] pDni = new Object[2];
        pDni[0] = "v_USUARIO";
        pDni[1] = usuario;
        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pDni);
        return userService.getSueldo(parametros);
    }


    @GetMapping("getPeriodos")
    public PeriodoListResponse getPeriodos() throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "SP_GET_PERIODOS";
        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);

        return userService.getPeriodos(parametros);
    }

    @GetMapping("getAvanceByPeriodo")
    public AvanceResponse getAvanceBtPeriodo(@RequestParam(value = "usuario") String usuario,@RequestParam(value = "annio") String annio,@RequestParam(value = "periodo") String periodo) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "SP_GET_AVANCE";


        Object[] pUsuario = new Object[2];
        pUsuario[0] = "v_USUARIO";
        pUsuario[1] = usuario;

        Object[] pAnnio = new Object[2];
        pAnnio[0] = "v_ANNIO";
        pAnnio[1] = annio;


        Object[] pPeriodo= new Object[2];
        pPeriodo[0] = "v_PERIODO";
        pPeriodo[1] = periodo;


        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pUsuario);
        parametros.add(pAnnio);
        parametros.add(pPeriodo);

        return userService.getAvanceVentas(parametros);
    }


    @GetMapping("getAvanceProveedorByPeriodo")
    public AvanceProveedorListResponse getAvanceProveedorByPeriodo(@RequestParam(value = "usuario") String usuario,@RequestParam(value = "annio") String annio,@RequestParam(value = "periodo") String periodo) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "SP_GET_AVANCE_PROVEEDOR";

        Object[] pUsuario = new Object[2];
        pUsuario[0] = "v_USUARIO";
        pUsuario[1] = usuario;

        Object[] pAnnio = new Object[2];
        pAnnio[0] = "v_ANNIO";
        pAnnio[1] = annio;


        Object[] pPeriodo= new Object[2];
        pPeriodo[0] = "v_PERIODO";
        pPeriodo[1] = periodo;


        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pUsuario);
        parametros.add(pAnnio);
        parametros.add(pPeriodo);

        return userService.getAvanceProveedor(parametros);
    }

    @GetMapping("getComisiones")
    public AvancePoliticaListResponse getComisiones(@RequestParam(value = "usuario") String usuario,@RequestParam(value = "annio") String annio,@RequestParam(value = "periodo") String periodo) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "SP_GET_COMISIONES";

        Object[] pUsuario = new Object[2];
        pUsuario[0] = "v_USUARIO";
        pUsuario[1] = usuario;

        Object[] pAnnio = new Object[2];
        pAnnio[0] = "v_ANNIO";
        pAnnio[1] = annio;


        Object[] pPeriodo= new Object[2];
        pPeriodo[0] = "v_PERIODO";
        pPeriodo[1] = periodo;


        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pUsuario);
        parametros.add(pAnnio);
        parametros.add(pPeriodo);

        return userService.getComisiones(parametros);
    }

}
