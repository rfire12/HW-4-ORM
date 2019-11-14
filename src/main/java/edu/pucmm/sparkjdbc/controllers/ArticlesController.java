package edu.pucmm.sparkjdbc.controllers;

import edu.pucmm.sparkjdbc.encapsulation.*;
import edu.pucmm.sparkjdbc.services.*;
import edu.pucmm.sparkjdbc.utils.Utils;

import java.util.*;

import static spark.Spark.*;

public class ArticlesController {
    public static void getRoutes() {

        before("/new-article", (request, response) -> {
            User user = request.session().attribute("user");
            if (user == null) {
                response.redirect("/");
            }
        });

        before("/articles/:id/edit", (request, response) -> {
            User user = request.session().attribute("user");
            if (user == null) {
                response.redirect("/");
            }
        });


        get("/", (request, response) -> {

            int pageNumber = (request.queryParams("page") != null) ? Integer.parseInt(request.queryParams("page")) : 1;
            int articles = ArticlesServices.getInstance().findAll().size(); //Total number of articles on the DB (this implementantation should be changed)
            List<Article> articlesList = ArticlesServices.getInstance().lazyFind(pageNumber);
            int totalPages = (int) (Math.ceil((double) articles / 5)) + 1;
            Map<String, Object> obj = new HashMap<>();
            obj.put("articles", articlesList);
            obj.put("tags", TagsServices.getInstance().findAll());
            obj.put("user", request.session().attribute("user"));
            obj.put("pages", totalPages);

            return TemplatesController.renderFreemarker(obj, "index.ftl");
        });

        get("/new-article", (request, response) -> {
            Map<String, Object> obj = new HashMap<>();
            obj.put("user", request.session().attribute("user"));
            return TemplatesController.renderFreemarker(obj, "new-article.ftl");
        });

        post("/new-article", (request, response) -> {
            Date todaysDate = new Date();
            java.sql.Timestamp date = new java.sql.Timestamp(todaysDate.getTime());

            User user = request.session().attribute("user");
            String[] tags = request.queryParams("tags").split(",");
            Set<Tag> tagList = Utils.arrayToTagsSet(tags);
            Article article = new Article(UUID.randomUUID().toString(), request.queryParams("title"), request.queryParams("article-body"), user, date, tagList);
            ArticlesServices.getInstance().create(article);
            response.redirect("/");
            return "";
        });

        get("/articles/:id", (request, response) -> {
            Map<String, Object> obj = new HashMap<>();
            Article article = ArticlesServices.getInstance().find(request.params("id"));
            List<Comment> comments = CommentsServices.getInstance().findAllByArticleUid(request.params("id"));
            User user = request.session().attribute("user");
            Recommendation recommendation = RecommendationServices.getInstance().find(new RecommendationId(article, user));
            Boolean userRecomendation = recommendation != null ? recommendation.getLike() : null;
            int likesTotal = RecommendationServices.getInstance().numberOfRecommendations(article, true);
            int dislikesTotal = RecommendationServices.getInstance().numberOfRecommendations(article, false);
            obj.put("article", article);
            obj.put("comments", comments);
            obj.put("tags", article.getTags());
            obj.put("user", request.session().attribute("user"));
            obj.put("like", String.valueOf(userRecomendation));
            obj.put("likesTotal", likesTotal);
            obj.put("dislikesTotal", dislikesTotal);
            return TemplatesController.renderFreemarker(obj, "show-article.ftl");
        });

        post("/articles/:id", (request, response) -> {
            Article article = ArticlesServices.getInstance().find(request.params("id"));
            article.setTitle(request.queryParams("title"));
            article.setInformation(request.queryParams("article-body"));

            for (Tag tag : article.getTags()) {
                tag.remove(article);
            }

            for (Tag tag : TagsServices.getInstance().findAll()) {
                if (!tag.hasArticles())
                    TagsServices.getInstance().delete(tag.getUid());
            }

            String[] tags = request.queryParams("tags").split(",");
            Set<Tag> tagList = Utils.arrayToTagsSet(tags);
            article.setTags(tagList);

            ArticlesServices.getInstance().update(article);
            response.redirect("/articles/" + request.params("id"));
            return "";
        });

        get("/articles/:id/edit", (request, response) -> {
            Map<String, Object> obj = new HashMap<>();
            Article article = ArticlesServices.getInstance().find(request.params("id"));
            Set<Tag> tags = article.getTags();
            String tagsTxt = "";
            for (Tag tag : tags)
                tagsTxt += tag.getTag() + ",";
            if (tagsTxt.endsWith(",")) {
                tagsTxt = tagsTxt.substring(0, tagsTxt.length() - 1);
            }
            obj.put("article", article);
            obj.put("tags", tagsTxt);
            obj.put("user", request.session().attribute("user"));
            return TemplatesController.renderFreemarker(obj, "edit-article.ftl");
        });

        before("/articles/:id/delete", (request, response) ->
        {
            User user = request.session().attribute("user");
            System.out.println(user);
            if (user == null) {
                response.redirect("/");
            }
        });

        post("/articles/:id/delete", (request, response) ->
        {
            ArticlesServices.getInstance().delete(request.params("id"));
            response.redirect("/");
            return "";
        });

        post("/articles/:id/like", (request, response) -> {
            Article article = ArticlesServices.getInstance().find(request.params("id"));
            User user = request.session().attribute("user");
            Utils.likeDislike(true, article, user);
            response.redirect("/articles/" + request.params("id"));
            return "";
        });

        post("/articles/:id/dislike", (request, response) -> {
            Article article = ArticlesServices.getInstance().find(request.params("id"));
            User user = request.session().attribute("user");
            Recommendation recommendation = RecommendationServices.getInstance().find(new RecommendationId(article, user));
            RecommendationId recommendationId = new RecommendationId(article, user);

            Utils.likeDislike(false, article, user);

            response.redirect("/articles/" + request.params("id"));
            return "";
        });

        get("/articles/tag/:id", (request, response) -> {
            Tag tag = TagsServices.getInstance().find(request.params("id"));
            Map<String, Object> obj = new HashMap<>();
            obj.put("articles", tag.getArticles());
            obj.put("tag", tag);
            return TemplatesController.renderFreemarker(obj, "show-tag-articles.ftl");
        });
    }
}
