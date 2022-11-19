package com.dh.dao;

import com.dh.modelo.Odontologo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDao {

    List<Odontologo> listarOdontologos(Connection connection) throws SQLException;
    boolean agregarOdontologo (Connection connection,int matricula, String nombre,String apellido);
    boolean update(Odontologo odontologo);
}
