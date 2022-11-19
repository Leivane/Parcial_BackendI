package com.dh.service;

import com.dh.dao.IDao;
import com.dh.modelo.Odontologo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OdontologoService {

    private IDao odontologoDAO;

    public OdontologoService() {
    }

    public OdontologoService(IDao odontologoDAO) {
        this.odontologoDAO = odontologoDAO;
    }

    public void setOdontologoDAO(IDao odontologoDAO) {
        this.odontologoDAO = odontologoDAO;
    }

    public List<Odontologo> listarOdontologos(Connection connection) throws SQLException {
        return odontologoDAO.listarOdontologos(connection);
    }

    public boolean agregarOdontologo (Connection connection,int matricula, String nombre,String apellido) {
        return odontologoDAO.agregarOdontologo(connection, matricula, nombre, apellido);
    }

    public boolean update(Odontologo odontologo) {
        return odontologoDAO.update(odontologo);
    }
}
