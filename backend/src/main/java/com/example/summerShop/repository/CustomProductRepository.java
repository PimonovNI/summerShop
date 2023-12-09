package com.example.summerShop.repository;

import com.example.summerShop.model.Brand;
import com.example.summerShop.model.Category;
import com.example.summerShop.model.Product;
import com.example.summerShop.model.enums.Gender;
import com.example.summerShop.util.SortType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomProductRepository {
    Optional<Product> findProductByIdFetchStorage(Long id);
    Optional<Product> findProductByIdAll(Long id);
    List<Product> findAllFetchBrandAndCategory();

    List<Product> findUsingFilters(List<Integer> brands, List<Integer> categories, List<Integer> sizes,
                                   Double priceMin, Double priceMax, Gender gen, SortType st);

    List<Product> findRecommendation(List<Brand> brands, List<Category> categories);
}
