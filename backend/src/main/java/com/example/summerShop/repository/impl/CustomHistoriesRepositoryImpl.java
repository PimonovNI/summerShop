package com.example.summerShop.repository.impl;

import com.example.summerShop.model.History;
import com.example.summerShop.repository.CustomHistoriesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomHistoriesRepositoryImpl implements CustomHistoriesRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Value("${spring.history.max-count}")
    private int maxCountInList;

    @Autowired
    public CustomHistoriesRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<History> findHistoryByUserId(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<History> criteria = cb.createQuery(History.class);

            Root<History> history = criteria.from(History.class);

            criteria.select(history)
                    .orderBy(cb.desc(history.get("time")));

            return entityManager.createQuery(criteria)
                    .setMaxResults(maxCountInList)
                    .getResultList();
        }
    }

    @Override
    public List<History> findHistoryByUserIdFetchingProduct(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<History> criteria = cb.createQuery(History.class);

            Root<History> history = criteria.from(History.class);
            var product = history.fetch("product");
            product.fetch("brand");
            product.fetch("category");

            criteria.select(history)
                    .where(cb.equal(history.get("user").get("id"), id))
                    .orderBy(cb.desc(history.get("time")));

            return entityManager.createQuery(criteria)
                    .setMaxResults(maxCountInList)
                    .getResultList();
        }
    }
}
