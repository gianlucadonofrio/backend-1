package com.company.test;


import com.company.dao.IDao;
import com.company.dao.impl.OdontologoDAOH2;
import com.company.model.Odontologo;
import com.company.service.OdontologoService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OdontologoServiceTest {
    private IDao<Odontologo> odontologoIDao = new OdontologoDAOH2();
    private OdontologoService odontologoService = new OdontologoService();

    @Before
    public void cargarDataOdontologos(){
        Odontologo odontologo = new Odontologo("Roberto","Palacios", 234423 );
        Odontologo odontologo1 = new Odontologo("Florencia","Rodriguez", 458585);


        odontologoService.setOdontologoIDao(odontologoIDao);
        odontologoService.guardarOdontologo(odontologo);
        odontologoService.guardarOdontologo(odontologo1);
    }

    @Test
    public void buscarOdontologosTest(){
        odontologoService.setOdontologoIDao(odontologoIDao);
        Odontologo odontologo = odontologoService.buscarOdontologo(4L);
        Assert.assertEquals(odontologo.getApellido(),"Rodriguez");
       Assert.assertEquals(odontologo.getNombre(),"Florencia");

    }
@Test
    public void buscarTodos(){
        List<Odontologo> odontologos = odontologoService.buscarTodosOdontologos();
        Assert.assertTrue(!odontologos.isEmpty());
}
}
