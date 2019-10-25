package edu.pucmm.sparkjdbc.controllers;

import edu.pucmm.sparkjdbc.encapsulation.User;
import edu.pucmm.sparkjdbc.services.UsersServices;
import spark.Session;

import static spark.Spark.*;

public class LoginController {
    public static void getRoutes(){

        before("/login", (request, response) -> {
            User user = request.session().attribute("user");
            if(user != null){
                response.redirect("/");
            }
        });

        get("/login", (request, response) -> {
            return TemplatesController.renderFreemarker(null, "login.ftl");
        });

        post("/login", (request, response) -> {
            request.queryParams("username");
            User user = UsersServices.getInstance().validateCredentials(request.queryParams("username"), request.queryParams("password"));
            Boolean rememberMe = false;
            if(request.queryParams("remember-me") != null) {
                rememberMe = true;
            }
            
            if(user != null){
                Session session = request.session(true);
                session.attribute("user", user);
                if(rememberMe){
                    response.cookie("USER", user.getUid(), 604800);
                }

                response.redirect("/");

            }else{
                response.redirect("/login", 401);
            }
            return "";
        });

        get("/logout", (request, response) -> {
            request.session().removeAttribute("user");
            response.removeCookie("USER");
            response.redirect("/");
            return "";
        });
    }
}
