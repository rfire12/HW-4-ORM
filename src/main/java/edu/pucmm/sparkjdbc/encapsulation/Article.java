package edu.pucmm.sparkjdbc.encapsulation;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Article {
    @Id
    private String uid;
    private String title;
    private String information;
    private User author;
    private Timestamp date;
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    private ArrayList<Comment> comments;
    @ManyToMany()
    private ArrayList<Tag> tags;

    public Article() {
    }

    public Article(String title, String information, User author, Timestamp date, ArrayList<Tag> tags) {
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

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
}
