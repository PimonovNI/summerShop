package com.example.summerShop.repository;

import com.example.summerShop.model.Cart;
import com.example.summerShop.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomCartsRepository {
    List<Cart> findAllByUserFetchProduct(User user);
    List<Cart> findAllByUserFetchProductAndSize(User user);
}
