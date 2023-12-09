package com.example.summerShop.repository.impl;

import com.example.summerShop.model.Brand;
import com.example.summerShop.model.Category;
import com.example.summerShop.model.Product;
import com.example.summerShop.model.enums.Gender;
import com.example.summerShop.repository.CustomProductRepository;
import com.example.summerShop.util.GraphUtils;
import com.example.summerShop.util.SortType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.*;
import org.hibernate.graph.GraphSemantic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CustomProductRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Optional<Product> findProductByIdFetchStorage(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Map<String, Object> properties = Map.of(
                    GraphSemantic.LOAD.getJakartaHintName(), GraphUtils.fetchProductWithStorage(entityManager)
            );
            return Optional.ofNullable(entityManager.find(Product.class, id, properties));
        }
    }

    @Override
    public Optional<Product> findProductByIdAll(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Map<String, Object> properties = Map.of(
                    GraphSemantic.LOAD.getJakartaHintName(), GraphUtils.fetchProductWithAll(entityManager)
            );
            return Optional.ofNullable(entityManager.find(Product.class, id, properties));
        }
    }

    @Override
    public List<Product> findAllFetchBrandAndCategory() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> criteria = cb.createQuery(Product.class);

            Root<Product> product = criteria.from(Product.class);
            product.fetch("brand", JoinType.LEFT);
            product.fetch("category", JoinType.LEFT);

            criteria.select(product)
                    .orderBy(cb.asc(product.get("id")));

            return entityManager.createQuery(criteria)
                    .getResultList();
        }
    }

    @Override
    public List<Product> findUsingFilters(List<Integer> brands, List<Integer> categories, List<Integer> sizes,
                                          Double priceMin, Double priceMax, Gender gen, SortType st) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> criteria = cb.createQuery(Product.class);

            Root<Product> product = criteria.from(Product.class);
            product.fetch("category", JoinType.INNER);
            product.fetch("brand", JoinType.INNER);
            var storage = product.join("storages", JoinType.LEFT);

            List<Predicate> predicates = new ArrayList<>();
            if (brands != null && !brands.isEmpty()) {
                predicates.add(product.get("brand").get("id").in(brands));
            }
            if (categories != null && !categories.isEmpty()) {
                predicates.add(product.get("category").get("id").in(categories));
            }
            if (sizes != null && !sizes.isEmpty()) {
                predicates.add(storage.get("size").get("id").in(sizes));
                predicates.add(cb.gt(storage.get("count"), 0));
            }
            if (priceMin != null && priceMin > 0) {
                predicates.add(cb.ge(product.get("price"), priceMin));
            }
            if (priceMax != null) {
                predicates.add(cb.le(product.get("price"), priceMax));
            }
            if (gen != null) {
                predicates.add(cb.equal(product.get("gender"), gen));
            }

            criteria.select(product)
                    .distinct(true)
                    .where(cb.and(predicates.toArray(Predicate[]::new)));

            if (st == SortType.BY_PRICE_ASC) {
                criteria.orderBy(cb.asc(product.get("price")));
            }
            if (st == SortType.BY_PRICE_DESC) {
                criteria.orderBy(cb.desc(product.get("price")));
            }

            return entityManager.createQuery(criteria)
                    .getResultList();
        }
    }

    @Override
    public List<Product> findRecommendation(List<Brand> brands, List<Category> categories) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> criteria = cb.createQuery(Product.class);

            Root<Product> product = criteria.from(Product.class);
            var brand = product.join("brand", JoinType.LEFT);
            var category = product.join("category", JoinType.LEFT);
            product.fetch("brand", JoinType.LEFT);
            product.fetch("category", JoinType.LEFT);

            criteria.select(product)
                    .where(cb.or(
                            brand.in(brands),
                            category.in(categories)
                    ));

            return entityManager.createQuery(criteria)
                    .getResultList();
        }
    }
}
