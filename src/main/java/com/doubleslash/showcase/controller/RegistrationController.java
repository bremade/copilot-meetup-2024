package com.doubleslash.showcase.controller;

import com.doubleslash.showcase.entity.UserEntity;
import com.doubleslash.showcase.service.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
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
    public String hello() {
        return "Hello, World!";
    }

    // TODO generate getAll endpoint

    // TODO generate getByLAstname endpoint

    // TODO generate addUser endpoint
}
