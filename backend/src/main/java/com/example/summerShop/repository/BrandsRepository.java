package com.example.summerShop.repository;

import com.example.summerShop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandsRepository extends JpaRepository<Brand, Integer> {
    Optional<Brand> findBrandByName(String name);
}
