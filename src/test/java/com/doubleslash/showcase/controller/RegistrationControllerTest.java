package com.doubleslash.showcase.controller;

import com.doubleslash.showcase.entity.UserEntity;
import com.doubleslash.showcase.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        RegistrationController registrationController = new RegistrationController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    public void getAllUsersReturnsOk() throws Exception {
        when(userService.findAll()).thenReturn(Arrays.asList(new UserEntity(), new UserEntity()));
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getUserByLastNameReturnsOk() throws Exception {
        when(userService.findByLastName(any(String.class))).thenReturn(Arrays.asList(new UserEntity()));
        mockMvc.perform(get("/users/Doe").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void addUserReturnsOk() throws Exception {
        when(userService.addUser(any(UserEntity.class))).thenReturn(new UserEntity());
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password1\"}")).andExpect(status().isOk());
    }
}