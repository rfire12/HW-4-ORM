package edu.pucmm.sparkjdbc.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DataBaseServices {
    private static DataBaseServices instance;
    private String URL = "jdbc:h2:tcp://localhost/~/spark-jdbc";

    private DataBaseServices() {
        registerDriver();
    }

    public static DataBaseServices getInstance() {
        if (instance == null) {
            instance = new DataBaseServices();
        }
        return instance;
    }

    private void registerDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {

        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, "sa", "");
        } catch (SQLException ex) {

        }
        return con;
    }

    public void testConnection() {
        try {
            getConnection().close();
            System.out.println("Conexión realizada con éxito!!");
        } catch (SQLException ex) {

        }
    }
}
