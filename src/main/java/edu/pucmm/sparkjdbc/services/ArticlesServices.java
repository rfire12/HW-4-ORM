package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.Article;
import edu.pucmm.sparkjdbc.encapsulation.Comment;
import edu.pucmm.sparkjdbc.encapsulation.Tag;
import edu.pucmm.sparkjdbc.encapsulation.User;
import edu.pucmm.sparkjdbc.utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

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
