package com.example.demo.demo.controller;

import com.example.demo.demo.model.UserDto;
import com.example.demo.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "viewer", roles = {"VIEWER"})
    void getUsers_asViewer_shouldReturnOk() throws Exception {
        UserDto u = new UserDto( "viewerUser", "pwd", "VIEWER");
        Mockito.when(userService.getAllUsers()).thenReturn(List.of(u));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getUsers_asAdmin_shouldReturnOk() throws Exception {
        UserDto u = new UserDto( "viewerUser", "pwd", "VIEWER");
        Mockito.when(userService.getAllUsers()).thenReturn(List.of(u));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void postUser_asViewer_shouldBeForbidden() throws Exception {
        UserDto u = new UserDto( "viewerUser", "pwd", "VIEWER");
        String json = objectMapper.writeValueAsString(u);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isForbidden());
    }
}