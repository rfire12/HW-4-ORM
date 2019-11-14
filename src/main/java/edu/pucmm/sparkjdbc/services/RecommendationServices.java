package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.Article;
import edu.pucmm.sparkjdbc.encapsulation.Recommendation;
import edu.pucmm.sparkjdbc.encapsulation.Tag;
import jdk.management.jfr.RecordingInfo;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class RecommendationServices extends DatabaseManagement<Recommendation> {

    private static RecommendationServices instance;

    private RecommendationServices() {
        super(Recommendation.class);
    }

    public static RecommendationServices getInstance() {
        if (instance == null) {
            instance = new RecommendationServices();
        }
        return instance;
    }

    public int numberOfRecommendations(Article article, Boolean criteria) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select r from Recommendation r where r.recommendationId.article = :article and r.isLike = :criteria");
        query.setParameter("article", article);
        query.setParameter("criteria", criteria);
        List<Recommendation> recommendations = query.getResultList();
        return recommendations.size();
    }

}
