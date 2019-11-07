package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UsersServices extends DatabaseManagement<User> {
    private static UsersServices instance;

    private UsersServices() {
        super(User.class);
    }

    public static UsersServices getInstance() {
        if (instance == null) {
            instance = new UsersServices();
        }
        return instance;
    }

    public User validateCredentials(String username, String password) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> list = query.getResultList();
        return list.get(0);
    }
}
