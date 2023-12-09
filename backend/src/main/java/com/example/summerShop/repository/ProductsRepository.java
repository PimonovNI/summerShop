package com.example.summerShop.repository;

import com.example.summerShop.model.Brand;
import com.example.summerShop.model.Category;
import com.example.summerShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>, CustomProductRepository {
    List<Product> findAllByBrandOrCategory(Brand brand, Category category);
}
