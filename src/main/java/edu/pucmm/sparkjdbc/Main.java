package edu.pucmm.sparkjdbc;

import edu.pucmm.sparkjdbc.services.BootStrapServices;
import edu.pucmm.sparkjdbc.services.DataBaseServices;

import java.sql.SQLException;

import static spark.Spark.staticFiles;

public class Main {
    public static void main(String[] args) throws SQLException {
        staticFiles.location("/publico");

        // Iniciando el servicio
        BootStrapServices.startDb();

        // Crear las tablas
        BootStrapServices.createTables();

        // Probando conexión
        DataBaseServices.getInstance().testConnection();
    }
}
