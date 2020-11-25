package com.digitalinka.restpreventa.controller;

import com.digitalinka.restpreventa.model.response.DisponiblePrecioResponse;
import com.digitalinka.restpreventa.model.response.ProductListResponse;
import com.digitalinka.restpreventa.model.response.UserResponse;
import com.digitalinka.restpreventa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("preventaGps")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("bonifItem")
    public ProductListResponse bonifItem(@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "v_PROCEDURE";
        pProcedure[1] = "LI_BONIF_ITEM";
        Object[] pPage = new Object[2];
        pPage[0] = "v_PAGE";
        pPage[1] = page;

        Object[] pLimit = new Object[2];
        pLimit[0] = "v_LIMIT";
        pLimit[1] = limit;

        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pPage);
        parametros.add(pLimit);
        return productService.bonifItem(parametros);
    }


    @GetMapping("getSugeridos")
    public ProductListResponse getSugeridos(@RequestParam(value = "usuario") String usuario,@RequestParam(value = "codCliente") String codCliente,@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "p_PROCEDURE";
        pProcedure[1] = "SP_GET_SUGERIDOS";

        Object[] pUsuario = new Object[2];
        pUsuario[0] = "p_USUARIO";
        pUsuario[1] = usuario;

        Object[] pCodCliente = new Object[2];
        pCodCliente[0] = "p_COD_CLIENTE";
        pCodCliente[1] = codCliente;

        Object[] pPage = new Object[2];
        pPage[0] = "p_PAGE";
        pPage[1] = page;

        Object[] pLimit = new Object[2];
        pLimit[0] = "p_LIMIT";
        pLimit[1] = limit;

        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pUsuario);
        parametros.add(pCodCliente);
        parametros.add(pPage);
        parametros.add(pLimit);
        return productService.getSugeridos(parametros);
    }

    @GetMapping("getArticulos")
    public ProductListResponse getArticulos(@RequestParam(value = "usuario") String usuario,@RequestParam(value = "codEmpresa") String codEmpresa,@RequestParam(value = "codAlmacen") String codAlmacen,@RequestParam(value = "codLista") String codLista, @RequestParam(value = "filtro") String filtro,@RequestParam(value = "page") String page, @RequestParam(value = "limit") String limit) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "p_PROCEDURE";
        pProcedure[1] = "SP_GET_ARTICULOS";

        Object[] pUsuario = new Object[2];
        pUsuario[0] = "p_USUARIO";
        pUsuario[1] = usuario;

        Object[] pCodEmpresa = new Object[2];
        pCodEmpresa[0] = "p_COD_EMPRESA";
        pCodEmpresa[1] = codEmpresa;

        Object[] pCodAlmacen = new Object[2];
        pCodAlmacen[0] = "p_COD_ALMACEN";
        pCodAlmacen[1] = codAlmacen;

        Object[] pCodLista= new Object[2];
        pCodLista[0] = "p_COD_LISTA";
        pCodLista[1] = codLista;

        Object[] pFiltro= new Object[2];
        pFiltro[0] = "p_FILTRO";
        pFiltro[1] = filtro;


        Object[] pPage = new Object[2];
        pPage[0] = "p_PAGE";
        pPage[1] = page;

        Object[] pLimit = new Object[2];
        pLimit[0] = "p_LIMIT";
        pLimit[1] = limit;

        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pUsuario);
        parametros.add(pCodEmpresa);
        parametros.add(pCodAlmacen);
        parametros.add(pCodLista);
        parametros.add(pFiltro);
        parametros.add(pPage);
        parametros.add(pLimit);
        return productService.getArticulos(parametros);
    }



    @GetMapping("getDisponiblePrecio")
    public DisponiblePrecioResponse getDisponiblePrecio(
            @RequestParam(value = "usuario") String usuario,
            @RequestParam(value = "codEmpresa") String codEmpresa,
            @RequestParam(value = "codSede") String codSede,
            @RequestParam(value = "codCanal") String codCanal,
            @RequestParam(value = "codAlmacen") String codAlmacen,
            @RequestParam(value = "codItem") String codItem,
            @RequestParam(value = "unidadMedida") String unidadMedida,
            @RequestParam(value = "codLista") String codLista,
            @RequestParam(value = "codCondicion") String codCondicion
         ) throws Exception {
        Object[] pProcedure = new Object[2];
        pProcedure[0] = "p_PROCEDURE";
        pProcedure[1] = "SP_GET_DISPONIBLE_PRECIO";

        Object[] pUsuario = new Object[2];
        pUsuario[0] = "p_USUARIO";
        pUsuario[1] = usuario;

        Object[] pCodEmpresa = new Object[2];
        pCodEmpresa[0] = "p_COD_EMPRESA";
        pCodEmpresa[1] = codEmpresa;

        Object[] pCodSede = new Object[2];
        pCodSede[0] = "p_COD_SEDE";
        pCodSede[1] =codSede;

        Object[] pCodCanal = new Object[2];
        pCodCanal[0] = "p_COD_CANAL";
        pCodCanal[1] =codCanal;

        Object[] pCodAlmacen = new Object[2];
        pCodAlmacen[0] = "p_COD_ALMACEN";
        pCodAlmacen[1] = codAlmacen;

        Object[] pCodItem = new Object[2];
        pCodItem[0] = "p_COD_ITEM";
        pCodItem[1] = codItem;


        Object[] pUnidadMedida = new Object[2];
        pUnidadMedida[0] = "p_UNIDAD_MEDIDA";
        pUnidadMedida[1] = unidadMedida;

        Object[] pCodLista= new Object[2];
        pCodLista[0] = "p_COD_LISTA";
        pCodLista[1] = codLista;


        Object[] pCondicion= new Object[2];
        pCondicion[0] = "p_CONDICION";
        pCondicion[1] = codCondicion;



        List<Object[]> parametros = new ArrayList<>();
        parametros.add(pProcedure);
        parametros.add(pUsuario);
        parametros.add(pCodEmpresa);
        parametros.add(pCodSede);
        parametros.add(pCodCanal);
        parametros.add(pCodItem);
        parametros.add(pUnidadMedida);
        parametros.add(pCodAlmacen);
        parametros.add(pCodLista);
        parametros.add(pCondicion);

        return productService.getDisponiblePrecio(parametros);
    }

}
