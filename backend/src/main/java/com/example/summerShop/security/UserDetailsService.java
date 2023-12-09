package com.example.summerShop.security;

import com.example.summerShop.model.User;
import com.example.summerShop.repository.UsersRepository;
import com.example.summerShop.util.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Користувача з таким ім'я не знайдено");
        }

        return new com.example.summerShop.security.UserDetails(user.get());
    }

    public UserDetails loadUserById(Long id) throws IdNotFoundException {
        Optional<User> user = usersRepository.findById(id);

        if (user.isEmpty()) {
            throw new IdNotFoundException("Користувача з таким ID не існує");
        }

        return new com.example.summerShop.security.UserDetails(user.get());
    }
}
