package com.doubleslash.showcase.controller;

import com.doubleslash.showcase.entity.UserEntity;
import com.doubleslash.showcase.service.UserService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    @Operation(summary = "Returns a greeting message")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping()
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the users"),
            @ApiResponse(responseCode = "404", description = "Users not found"),
    })
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{lastName}")
    @Operation(summary = "Get users by last name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the users"),
            @ApiResponse(responseCode = "404", description = "Users not found"),
    })
    public List<UserEntity> getAllUsers(
            @PathParam(value = "lastName") String lastName) {
        return userService.findByLastName(lastName);
    }

    @PostMapping
    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public UserEntity addUser(@Valid @RequestBody UserEntity user) {
        return userService.addUser(user);
    }
}