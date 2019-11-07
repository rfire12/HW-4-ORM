package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.Article;

public class ArticlesServices extends DatabaseManagement<Article> {

    private static ArticlesServices instance;

    private ArticlesServices() {
        super(Article.class);
    }

    public static ArticlesServices getInstance() {
        if (instance == null) {
            instance = new ArticlesServices();
        }
        return instance;
    }
}
