package com.example.summerShop.repository;

import com.example.summerShop.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizesRepository extends JpaRepository<Size, Integer> {
}
