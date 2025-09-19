package com.example.demo.demo.controller;

import com.example.demo.demo.entity.UserEntity;
import com.example.demo.demo.model.UserDto;
import com.example.demo.demo.repository.UserRepository;
import com.example.demo.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String addUser(@RequestBody UserDto user) {
        return userService.addUser(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN','VIEWER')")
    @GetMapping
    public List<UserDto> listUsers() {
        return userService.getAllUsers();
    }
}