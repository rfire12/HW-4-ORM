package edu.pucmm.sparkjdbc.encapsulation;

import javax.persistence.OneToOne;
import java.io.Serializable;

public class ArticleTag implements Serializable {
    @OneToOne
    private Article article;
    @OneToOne
    private Tag tag;

    public ArticleTag() {

    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
