package com.digitalinka.restpreventa.service;

import com.digitalinka.restpreventa.model.response.DisponiblePrecioResponse;
import com.digitalinka.restpreventa.model.response.ProductListResponse;

import java.util.List;

public interface ProductService {
    ProductListResponse bonifItem(List<Object[]> parametrosString);
    ProductListResponse getSugeridos(List<Object[]> parametrosString);
    ProductListResponse getArticulos(List<Object[]> parametrosString);
    DisponiblePrecioResponse getDisponiblePrecio(List<Object[]> parametrosString);


}
