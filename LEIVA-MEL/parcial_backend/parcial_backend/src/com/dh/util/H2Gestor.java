package com.dh.util;

import java.sql.*;

public class H2Gestor {

    public static final String H2_URL = "jdbc:h2:~/parcial_backend";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(H2_URL);
    }


    public static void executeSQL(Connection conn, String sql){
        try {
            Statement statement= conn.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(Connection conn, String sql){
        try {
            Statement statement= conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
