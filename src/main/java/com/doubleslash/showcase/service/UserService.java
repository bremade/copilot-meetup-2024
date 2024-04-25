package com.doubleslash.showcase.service;

// Generate a user service class to search for users by last name

import com.doubleslash.showcase.entity.UserEntity;
import com.doubleslash.showcase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }
}