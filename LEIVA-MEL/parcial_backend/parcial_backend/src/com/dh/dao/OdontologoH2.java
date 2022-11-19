package com.dh.dao;


import com.dh.modelo.Odontologo;
import com.dh.util.H2Gestor;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.dh.util.ConsultasSql.*;

public class OdontologoH2 implements IDao{

    public static final Logger logger = Logger.getLogger(OdontologoH2.class);
    H2Gestor h2Gestor = new H2Gestor();

    public List<Odontologo> listarOdontologos(Connection connection) throws SQLException {

        ResultSet rs = h2Gestor.executeQuery(connection, SQL_LISTAR);
        List<Odontologo> odontologoList = new ArrayList<>();


        while (rs.next()){
            Odontologo odontologo = new Odontologo();
            odontologo.setId(rs.getLong("ID"));
            odontologo.setNumeroMatricula(rs.getInt("NUM_MATRICULA"));
            odontologo.setNombre(rs.getString("NOMBRE"));
            odontologo.setApellido(rs.getString("APELLIDO"));
            logger.info(odontologo);
            odontologoList.add(odontologo);
        }

        rs.close();

        return odontologoList;

    }

    public boolean agregarOdontologo (Connection connection,int matricula, String nombre,String apellido) {
        PreparedStatement preparedStatement= null;
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1,matricula);
            preparedStatement.setString(2,nombre);
            preparedStatement.setString(3,apellido);
            preparedStatement.execute();

            logger.info(" Agregamos al odontologo con matricula " + matricula);
            result = true;
        } catch (SQLException e) {
            logger.error(" Error en agregar un adontologo " + e.getMessage());
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public void crearTabla (Connection connection){
        H2Gestor.executeSQL(connection,SQL_CREATE_TABLE);
        logger.info("Estamos creando una tabla");
    }

    public boolean update(Odontologo odontologo) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;

        try {
            connection = H2Gestor.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE ODONTOLOGO SET NOMBRE  = ?, APELLIDO = ?, NUM_MATRICULA = ? WHERE id = ?;");
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getNumeroMatricula());
            preparedStatement.setLong(4, odontologo.getId());

            preparedStatement.executeUpdate();
            logger.info("Se actulizó correctamente");
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error al actualizar");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return result;

    }



}
