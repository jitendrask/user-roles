package com.example.demo.demo.service;

import com.example.demo.demo.entity.UserEntity;
import com.example.demo.demo.model.UserDto;
import com.example.demo.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    UserEntity userEntity;

    @InjectMocks
    UserService userService;

    @Test
    void addUser() {
        Mockito.when(userEntity.getId()).thenReturn(10L);
        Mockito.when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        Assertions.assertEquals("10",userService.addUser(new UserDto("u","p","r")));
    }

    @Test
    void getAllUsers() {
        UserEntity u = new UserEntity();
        Mockito.when(userRepository.findAll()).thenReturn(List.of(u));
        Assertions.assertEquals(1,userService.getAllUsers().size());
    }
}