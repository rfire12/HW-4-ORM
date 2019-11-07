package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.Tag;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

    public Tag findByName(String tag) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select t from Tag t where t.tag = :tag");
        query.setParameter("tag", tag);
        List<Tag> list = query.getResultList();
        if(list.size() > 0)
            return list.get(0);
        else
            return null;
    }
}
