package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.Article;
import edu.pucmm.sparkjdbc.encapsulation.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    public List<Article> lazyFind() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a from Article a");
        int pageNumber = 1;
        int pageSize = 10;
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);
        List<Article> articlesList = query.getResultList();
        return articlesList;
    }

}
