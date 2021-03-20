package com.challenge.techbase.services;

import com.challenge.techbase.models.entity.User;

import java.util.Optional;

public interface UserService {

    User login(String email, String password);

    User saveUser(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(int id);
}