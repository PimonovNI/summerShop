package com.example.summerShop.repository;

import com.example.summerShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    List<User> findByEmail(String email);
    Optional<User> findByActivationCode(String activationCode);

}
