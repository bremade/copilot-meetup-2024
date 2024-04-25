package com.doubleslash.showcase.controller;

import com.doubleslash.showcase.entity.UserEntity;
import com.doubleslash.showcase.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/registration")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {
        // use the user service to get all users
        return userService.findAll();
    }

    @GetMapping("/user")
    public List<UserEntity> getAllUsers(
            @RequestParam(value = "lastName", required = false) String lastName) {
        // use the user service to get all users by last name
        return userService.findByLastName(lastName);
    }

    @PostMapping("/user")
    public UserEntity addUser(@Valid @RequestBody UserEntity user) {
        // use the user service to add a user
        return userService.addUser(user);
    }
}
