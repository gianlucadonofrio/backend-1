package com.company.service;


import com.company.dao.IDao;
import com.company.dao.impl.OdontologoDAOH2;
import com.company.model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;
    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public IDao<Odontologo> getOdontologoIDao() {
        return odontologoIDao;
    }


    public Odontologo guardarOdontologo(Odontologo o) {
        return odontologoIDao.guardar(o);
    }

    public void eliminarOdontologo(Long id) {
        odontologoIDao.eliminar(id);
    }

    public Odontologo buscarOdontologo(Long id) {
        return odontologoIDao.buscar(id);
    }

    public List<Odontologo> buscarTodosOdontologos(){
        return odontologoIDao.buscarTodos();
    }
}
