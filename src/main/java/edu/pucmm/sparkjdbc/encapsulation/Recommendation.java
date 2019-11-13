package edu.pucmm.sparkjdbc.encapsulation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Recommendation {
    @Id
    private String uid;
    @ManyToOne
    private Article article;
    @ManyToOne
    private User user;
    private Boolean isLike;

    public Recommendation(String uid, Article article, User user, Boolean isLike) {
        this.uid = uid;
        this.article = article;
        this.user = user;
        this.isLike = isLike;
    }

    public Recommendation() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }
}
