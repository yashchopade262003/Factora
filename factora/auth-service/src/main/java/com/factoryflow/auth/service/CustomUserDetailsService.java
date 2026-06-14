package com.factoryflow.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.factoryflow.auth.dao.UserDAO;
import com.factoryflow.auth.entity.User;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    @Autowired
    private UserDAO userdao;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Optional<User> user =
                userdao.userLogin(username);

        if (user.isPresent()) {

            User userLogin = user.get();

            return org.springframework.security.core.userdetails.User
                    .withUsername(userLogin.getEmail())
                    .password(userLogin.getPassword())
                    .roles(userLogin.getRole())
                    .build();
        }

        throw new UsernameNotFoundException(
                "User Not Found");
    }
}