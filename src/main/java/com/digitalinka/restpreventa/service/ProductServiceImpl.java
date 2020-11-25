package com.digitalinka.restpreventa.service;

import com.digitalinka.restpreventa.dao.ProductDao;
import com.digitalinka.restpreventa.model.response.DisponiblePrecioResponse;
import com.digitalinka.restpreventa.model.response.ProductListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;
    @Override
    public ProductListResponse bonifItem(List<Object[]> parametrosString) {
        return productDao.bonifItem(parametrosString);
    }

    @Override
    public ProductListResponse getSugeridos(List<Object[]> parametrosString) {
        return productDao.getSugeridos(parametrosString);
    }

    @Override
    public ProductListResponse getArticulos(List<Object[]> parametrosString) {
        return productDao.getArticulos(parametrosString);
    }

    @Override
    public DisponiblePrecioResponse getDisponiblePrecio(List<Object[]> parametrosString) {
        return productDao.getDisponiblePrecio(parametrosString);
    }
}
