package com.doubleslash.showcase.controller;

import com.doubleslash.showcase.entity.UserEntity;
import com.doubleslash.showcase.service.UserService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
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

    @GetMapping
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the users"),
            @ApiResponse(responseCode = "404", description = "Users not found"),
    })
    // return not found if none exist
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
    @GetMapping("/{lastName}")
    @Operation(summary = "Get users by last name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the users"),
            @ApiResponse(responseCode = "404", description = "Users not found"),
    })
    public ResponseEntity<List<UserEntity>> getUserByLastName(
            @PathVariable(value = "lastName") String lastName) {
        List<UserEntity> users = userService.findByLastName(lastName);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<UserEntity> addUser(@Valid @RequestBody UserEntity user) {
        UserEntity createdUser = userService.addUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}