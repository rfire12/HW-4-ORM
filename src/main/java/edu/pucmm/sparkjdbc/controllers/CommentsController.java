package edu.pucmm.sparkjdbc.controllers;

import edu.pucmm.sparkjdbc.encapsulation.Comment;
import edu.pucmm.sparkjdbc.services.CommentsServices;

import static spark.Spark.*;

public class CommentsController {
    public static void getRoutes() {
        get("/comments/new/:article_id", (request, response) -> {
            Comment comment = new Comment();
            comment.setComment(request.queryParams("comment"));
//            CommentsServices.getInstance().createComment()
            response.redirect("/articles/" + request.params("article_id"));
            return "";
        });
    }
}
