package edu.pucmm.sparkjdbc.encapsulation;

public class Tag {
    private long uid;
    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
