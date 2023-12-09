package com.example.summerShop.repository;

import com.example.summerShop.model.Cart;
import com.example.summerShop.model.Product;
import com.example.summerShop.model.Size;
import com.example.summerShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartsRepository extends JpaRepository<Cart, Long>, CustomCartsRepository {
    Optional<Cart> findCartByUserAndProductAndSize(User user, Product product, Size size);
}
