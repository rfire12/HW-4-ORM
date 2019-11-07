package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.Tag;

public class TagsServices extends DatabaseManagement<Tag> {

    private static TagsServices instance;

    private TagsServices() {
        super(Tag.class);
    }

    public static TagsServices getInstance() {
        if (instance == null) {
            instance = new TagsServices();
        }
        return instance;
    }
}
