package com.doubleslash.showcase.repository;

// Generate a user repository interface to search for users by last name

import com.doubleslash.showcase.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByLastName(String lastName);

    //Get all users
    List<UserEntity> findAll();
}
