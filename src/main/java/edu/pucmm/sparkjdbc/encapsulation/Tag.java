package edu.pucmm.sparkjdbc.encapsulation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Tag implements Serializable {
    @Id
    private String uid;
    private String tag;
    @ManyToMany
    private Set<Article> article;

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
}
