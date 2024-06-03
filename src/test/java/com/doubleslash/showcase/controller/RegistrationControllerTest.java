package com.doubleslash.showcase.controller;

import com.doubleslash.showcase.entity.UserEntity;
import com.doubleslash.showcase.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    @DisplayName("Should return all users when users exist")
    public void getAllUsersReturnsUsers() throws Exception {
        when(userService.findAll()).thenReturn(Arrays.asList(new UserEntity()));
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return not found when no users exist")
    public void getAllUsersReturnsNotFound() throws Exception {
        when(userService.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/users")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return user when user with last name exists")
    public void getUserByLastNameReturnsUser() throws Exception {
        when(userService.findByLastName("Doe")).thenReturn(Arrays.asList(new UserEntity()));
        mockMvc.perform(get("/users/Doe")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return not found when no user with last name exists")
    public void getUserByLastNameReturnsNotFound() throws Exception {
        when(userService.findByLastName("Doe")).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/users/Doe")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should create user when valid request")
    public void addUserCreatesUser() throws Exception {
        when(userService.addUser(new UserEntity())).thenReturn(new UserEntity());
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"test@test.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should return bad request when invalid request")
    public void addUserReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }
}