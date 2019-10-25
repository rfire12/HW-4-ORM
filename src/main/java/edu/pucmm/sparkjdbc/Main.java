package edu.pucmm.sparkjdbc;

import edu.pucmm.sparkjdbc.controllers.ArticlesController;
import edu.pucmm.sparkjdbc.controllers.CommentsController;
import edu.pucmm.sparkjdbc.controllers.LoginController;
import edu.pucmm.sparkjdbc.encapsulation.User;
import edu.pucmm.sparkjdbc.services.*;
import spark.Session;

import java.sql.SQLException;

import static spark.Spark.before;
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

        before((request, response) -> {
            User user = request.session().attribute("user");
            if(request.cookie("USER") != null && user == null){ //If the user is not logged, try to get the cookie to set a session
                String userUID = request.cookie("USER");
                user = UsersServices.getInstance().getUser(userUID);
                Session session = request.session(true);
                session.attribute("user", user);
            }
        });

        // Articles Routes
        ArticlesController.getRoutes();

        // Comments Routes
        CommentsController.getRoutes();

        // Login Routes
        LoginController.getRoutes();
    }
}
