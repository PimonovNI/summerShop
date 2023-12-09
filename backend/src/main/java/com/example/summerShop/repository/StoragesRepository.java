package com.example.summerShop.repository;

import com.example.summerShop.model.Product;
import com.example.summerShop.model.Size;
import com.example.summerShop.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoragesRepository extends JpaRepository<Storage, Long> {
    Optional<Storage> findStorageByProductAndSize(Product product, Size size);
}
