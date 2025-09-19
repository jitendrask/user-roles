package com.example.demo.demo.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    void passwordEncoderBeanExistsAndWorks() {
        assertThat(passwordEncoder).isNotNull();
        String raw = "mysecret";
        String encoded = passwordEncoder.encode(raw);
        assertTrue(passwordEncoder.matches(raw, encoded));
    }

    @Test
    void inMemoryUsersExist() {
        // assuming SecurityConfig registers "admin" and "viewer"
        UserDetails admin = userDetailsService.loadUserByUsername("admin");
        assertThat(admin).isNotNull();
        assertTrue(admin.getAuthorities().stream().anyMatch(a -> a.getAuthority().endsWith("ADMIN")));

        UserDetails viewer = userDetailsService.loadUserByUsername("viewer");
        assertThat(viewer).isNotNull();
        assertTrue(viewer.getAuthorities().stream().anyMatch(a -> a.getAuthority().endsWith("VIEWER")));
    }
}