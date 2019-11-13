package edu.pucmm.sparkjdbc.encapsulation;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Article implements Serializable {
    @Id
    private String uid;
    private String title;
    private String information;
    @ManyToOne
    private User author;
    private Timestamp date;
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    private Set<Comment> comments;
    @ManyToMany(mappedBy = "articles", fetch = FetchType.EAGER)
    private Set<Tag> tags;
    @OneToMany
    private Set<Recommendation> recommentadations;

    public Article() {
    }

    public Article(String uid, String title, String information, User author, Timestamp date, Set<Tag> tags) {
        this.uid = uid;
        this.title = title;
        this.information = information;
        this.author = author;
        this.date = date;
        this.tags = tags;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

}
