package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.Comment;
import edu.pucmm.sparkjdbc.encapsulation.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentsServices extends DatabaseManagement<Comment> {

    private static CommentsServices instance;

    private CommentsServices() {
        super(Comment.class);
    }

    public static CommentsServices getInstance() {
        if (instance == null) {
            instance = new CommentsServices();
        }

        return instance;
    }

    public List<Comment> findAllByArticleUid(String uid) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select c from Comment c where c.article.id = :uid");
        query.setParameter("uid", uid);
        List<Comment> list = query.getResultList();
        return list;
    }
}
