package com.example.summerShop.util;

import com.example.summerShop.model.Cart;
import com.example.summerShop.model.Product;
import com.example.summerShop.model.Storage;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;

public class GraphUtils {
    public static EntityGraph<Product> fetchProductWithStorage(EntityManager entityManager) {
        EntityGraph<Product> graph = entityManager.createEntityGraph(Product.class);
        graph.addAttributeNodes("storages");
        return graph;
    }

    public static EntityGraph<Product> fetchProductWithAll(EntityManager entityManager) {
        EntityGraph<Product> graph = entityManager.createEntityGraph(Product.class);
        graph.addAttributeNodes("storages", "brand", "category");
        Subgraph<Storage> storages = graph.addSubgraph("storages", Storage.class);
        storages.addAttributeNodes("size");
        return graph;
    }

    public static EntityGraph<Cart> fetchCartWithProduct(EntityManager entityManager) {
        EntityGraph<Cart> graph = entityManager.createEntityGraph(Cart.class);
        graph.addAttributeNodes("product", "size");
        Subgraph<Product> products = graph.addSubgraph("product", Product.class);
        products.addAttributeNodes("brand", "category");
        return graph;
    }
}