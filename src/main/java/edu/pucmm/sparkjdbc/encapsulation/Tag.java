package edu.pucmm.sparkjdbc.encapsulation;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Tag implements Serializable {
    @Id
    private String uid;
    private String tag;
    @ManyToMany()
    private Set<Article> articles;

    public Tag() {

    }

    public Tag(String uid, String tag) {
        this.uid = uid;
        this.tag = tag;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
