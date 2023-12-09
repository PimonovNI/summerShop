package com.example.summerShop.repository.impl;

import com.example.summerShop.model.Cart;
import com.example.summerShop.model.User;
import com.example.summerShop.repository.CustomCartsRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomCartsRepositoryImpl implements CustomCartsRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CustomCartsRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Cart> findAllByUserFetchProduct(User user) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Cart> criteria = cb.createQuery(Cart.class);

            Root<Cart> cart = criteria.from(Cart.class);
            cart.fetch("product", JoinType.INNER);

            criteria.select(cart);

            return entityManager.createQuery(criteria)
                    .getResultList();
        }
    }

    @Override
    public List<Cart> findAllByUserFetchProductAndSize(User user) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Cart> criteria = cb.createQuery(Cart.class);

            Root<Cart> cart = criteria.from(Cart.class);
            cart.fetch("size", JoinType.LEFT);
            var product = cart.fetch("product", JoinType.INNER);
            product.fetch("brand", JoinType.LEFT);
            product.fetch("category", JoinType.LEFT);

            criteria.select(cart);

            return entityManager.createQuery(criteria)
                    .getResultList();
        }
    }
}
