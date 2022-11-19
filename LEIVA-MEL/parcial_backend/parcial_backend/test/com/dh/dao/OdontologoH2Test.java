package com.dh.dao;

import com.dh.modelo.Odontologo;
import com.dh.util.H2Gestor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoH2Test {

    @Test
    public void testAgregarOdontologo() {
        // Dado
        Connection connection = null;
        OdontologoH2 odontologoH2 = new OdontologoH2();
        boolean resultadoActual = false;

        // Cuando
        try {
            connection = H2Gestor.getConnection();
            resultadoActual = odontologoH2.agregarOdontologo(connection, 2368, "Roman", "Leiva");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean resultadoEsperado = true;

        // Entonces
        Assertions.assertEquals(resultadoEsperado, resultadoActual);
    }

    @Test
    public void testListarOdontologos() {
        // Dado
        List<Odontologo> odontologos = null;
        OdontologoH2 odontologoH2 = new OdontologoH2();


        // Cuando
        try {
            Connection connection = H2Gestor.getConnection();
            odontologos = odontologoH2.listarOdontologos(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int resultado = odontologos.size();
        int resultadoEsperado = 4;

        odontologos.forEach(System.out::println);

        // Entonces
        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testActualizarOdontologo() {
        // Dado
        OdontologoH2 odontologoH2 = new OdontologoH2();
        Odontologo odontologo = new Odontologo();
        boolean result = false;

        odontologo.setId(4);
        odontologo.setNombre("Skay");
        odontologo.setApellido("Bellinson");
        odontologo.setNumeroMatricula(2367);

        // Cuando
        try {
            Connection connection = H2Gestor.getConnection();
            result = odontologoH2.update(odontologo);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Entonces
        Assertions.assertEquals(true, result);
    }

}