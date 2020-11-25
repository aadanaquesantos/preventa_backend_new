package com.digitalinka.restpreventa.dao;

import com.digitalinka.restpreventa.model.response.DisponiblePrecioResponse;
import com.digitalinka.restpreventa.model.response.ProductListResponse;
import com.digitalinka.restpreventa.model.response.UserResponse;

import java.util.List;

public interface ProductDao {
    ProductListResponse bonifItem(List<Object[]> parametrosString);
    ProductListResponse getSugeridos(List<Object[]> parametrosString);
    ProductListResponse getArticulos(List<Object[]> parametrosString);
    DisponiblePrecioResponse getDisponiblePrecio(List<Object[]> parametrosString);
}
