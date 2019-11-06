package edu.pucmm.sparkjdbc.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import java.lang.reflect.Field;

public class DatabaseManagement<T> {
    private static EntityManagerFactory emf;
    private Class<T> entityClass;

    public DatabaseManagement(Class<T> entityClass) {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("MyDatabase");
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private Object getFieldValue(T entity) {
        if (entity == null)
            return null;

        for (Field f : entity.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Id.class)) {
                try {
                    f.setAccessible(true);
                    Object fieldValue = f.get(entity);
                    return fieldValue;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public void create(T entity) {
        EntityManager em = getEntityManager();

        try {
            if (em.find(entityClass, getFieldValue(entity)) != null) {
                System.out.println("Entity already exists");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        em.getTransaction().begin();

        try {
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
