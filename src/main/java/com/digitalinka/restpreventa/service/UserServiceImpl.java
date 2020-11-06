package com.digitalinka.restpreventa.service;

import com.digitalinka.restpreventa.dao.UserDao;
import com.digitalinka.restpreventa.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public UserResponse loginByUserEmail(String userEmail, String password) {
        return userDao.loginByUserEmail(userEmail,password);
    }

    @Override
    public UserResponse userByDni(List<Object[]> parametrosString) {
        return userDao.userByDni(parametrosString);
    }

    @Override
    public StatusResponse updateUserLogin(List<Object[]> parametrosString) {
        return userDao.updateUserLogin(parametrosString);
    }

    @Override
    public SueldoResponse getSueldo(List<Object[]> parametrosString) {
        return userDao.getSueldo(parametrosString);
    }

    @Override
    public PeriodoListResponse getPeriodos(List<Object[]> parametrosString) {
        return userDao.getPeriodos(parametrosString);
    }

    @Override
    public AvanceResponse getAvanceVentas(List<Object[]> parametrosString) {
        return userDao.getAvanceVentas(parametrosString);
    }

    @Override
    public AvanceProveedorListResponse getAvanceProveedor(List<Object[]> parametrosString) {
        return userDao.getAvanceProveedor(parametrosString);
    }

    @Override
    public AvancePoliticaListResponse getComisiones(List<Object[]> parametrosString) {
            return userDao.getComisiones(parametrosString);
    }
}
