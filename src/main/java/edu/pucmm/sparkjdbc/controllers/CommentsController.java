package edu.pucmm.sparkjdbc.controllers;

import edu.pucmm.sparkjdbc.encapsulation.Article;
import edu.pucmm.sparkjdbc.encapsulation.Comment;
import edu.pucmm.sparkjdbc.encapsulation.User;
import edu.pucmm.sparkjdbc.services.ArticlesServices;
import edu.pucmm.sparkjdbc.services.CommentsServices;
import edu.pucmm.sparkjdbc.services.UsersServices;
import edu.pucmm.sparkjdbc.utils.Utils;

import static spark.Spark.*;

public class CommentsController {
    public static void getRoutes() {
        before("/comments/*", (request, response) -> {
            User user = request.session().attribute("user");
            if (user == null) {
                response.redirect("/");
            }
        });

        post("/comments/new/:article_id", (request, response) -> {
            User user = UsersServices.getInstance().findByObject(request.session().attribute("user"));
            Article article = ArticlesServices.getInstance().find(request.params("article-id"));
            System.out.println("Article:" + article.getUid());
            Comment comment = new Comment(article, request.queryParams("comment"), user);
            CommentsServices.getInstance().create(comment);
            response.redirect("/articles/" + request.params("article_id"));
            return "";
        });

        post("/articles/:article_id/comments/:comment_id", (request, response) -> {
            CommentsServices.getInstance().delete(request.params("comment_id"));
            response.redirect("/articles/" + request.params("article_id"));
            return "";
        });
    }
}
