package edu.pucmm.sparkjdbc.services;

import edu.pucmm.sparkjdbc.encapsulation.Article;
import edu.pucmm.sparkjdbc.encapsulation.Recommendation;
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

}
