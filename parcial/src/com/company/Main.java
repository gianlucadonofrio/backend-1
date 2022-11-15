package com.company;

import com.company.dao.impl.OdontologoDAOH2;
import com.company.model.Odontologo;
import com.company.service.OdontologoService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Odontologo odontologo0 = new Odontologo("GIAN","DONOFRIO",48484);
        Odontologo odontologo1 = new Odontologo("MARCE","GALLARDO",232312);



        OdontologoService odontologoService = new OdontologoService();
        odontologoService.setOdontologoIDao(new OdontologoDAOH2());
        odontologoService.guardarOdontologo(odontologo0);
        odontologoService.guardarOdontologo(odontologo1);

        List<Odontologo> odontologoList = odontologoService.buscarTodosOdontologos();
        for (Odontologo odontologo : odontologoList){
            System.out.println("Nombre: "+ odontologo.getNombre()+ ", apellido: "+ odontologo.getApellido()+", matricula: "+ odontologo.getNroMatricula());
        }



    }
}
