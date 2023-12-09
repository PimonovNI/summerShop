package com.example.summerShop.repository;

import com.example.summerShop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findCategoriesByName(String name);
}
