package com.doubleslash.showcase.controller;

import com.doubleslash.showcase.entity.UserEntity;
import com.doubleslash.showcase.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for user registration related APIs.
 */
@Tag(name = "User Registration", description = "APIs related to user registration")
@RestController
@RequestMapping(path = "/users")
public class RegistrationController {
    private final UserService userService;

    /**
     * Constructor for RegistrationController.
     *
     * @param userService the user service to be used
     */
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to get a hello world message.
     *
     * @return a hello world message
     */
    @Operation(summary = "Get a hello world message")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    /**
     * Endpoint to get all users.
     *
     * @return a list of all users
     */
    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "List of all users")
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    /**
     * Endpoint to get users by last name.
     *
     * @param lastName the last name of the users to be searched
     * @return a list of users with the given last name
     */
    @Operation(summary = "Get users by last name")
    @ApiResponse(responseCode = "200", description = "List of users with the given last name")
    @GetMapping("/{lastName}")
    public List<UserEntity> getUserByLastName(
            @Parameter(description = "Last name of the user to be searched") @PathParam(value = "lastName") String lastName) {
        return userService.findByLastName(lastName);
    }

    /**
     * Endpoint to add a new user.
     *
     * @param user the user entity to be added
     * @return the created user entity
     */
    @Operation(summary = "Add a new user")
    @ApiResponse(responseCode = "200", description = "The created user entity")
    @PostMapping
    public UserEntity addUser(@Valid @RequestBody UserEntity user) {
        return userService.addUser(user);
    }
}