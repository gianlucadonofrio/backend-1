package com.company.dao.impl;

import com.company.dao.IDao;
import com.company.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo> {
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test_odontologos;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";
    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);



    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO odontologos(nombre,apellido,matricula) VALUES (?,?,?)");
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getNroMatricula());

            preparedStatement.executeUpdate();
            logger.info("Guardamos cada odontologo");
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error("Error al guardar odontologo");
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos ");

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String nombreOdontologo = result.getString("nombre");
                String apellidoOdontologo = result.getString("apellido");
                int nroMatricula = result.getInt("matricula");
                Odontologo odontologo = new Odontologo(nombreOdontologo, apellidoOdontologo, nroMatricula);

                logger.info("Listamos todos los odontologos");

                odontologos.add(odontologo);
            }
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error("Error al listar los odontologos");

        }
        return odontologos;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM odontologos WHERE ID=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            logger.info("Eliminamos a un odontologo");

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error("Error al eliminar un odontologo");

        }
    }

    @Override
    public Odontologo buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;
        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM odontologos WHERE ID=?");
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String nombreOdontologo = result.getString("nombre");
                String apellidoOdontologo = result.getString("apellido");
                int nroMatricula = result.getInt("matricula");
                odontologo = new Odontologo(nombreOdontologo,apellidoOdontologo,nroMatricula);

            }
            logger.info("Buscando al odontologo");

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error("Error al buscar al odontologo");

        }
        return odontologo;
    }
}
