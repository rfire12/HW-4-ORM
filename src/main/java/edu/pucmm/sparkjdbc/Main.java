package edu.pucmm.sparkjdbc;

import edu.pucmm.sparkjdbc.controllers.ArticlesController;
import edu.pucmm.sparkjdbc.controllers.CommentsController;
import edu.pucmm.sparkjdbc.services.*;

import java.sql.SQLException;

import static spark.Spark.staticFiles;

public class Main {

    public static void main(String[] args) throws SQLException {

        staticFiles.location("/publico");

        // Starting the server
        BootStrapServices.startDb();

        // Creating tables
        BootStrapServices.createTables();

        // Testing connection
        DataBaseServices.getInstance().testConnection();

        // Articles Routes
        ArticlesController.getRoutes();

        // Comments Routes
        CommentsController.getRoutes();
    }
}
