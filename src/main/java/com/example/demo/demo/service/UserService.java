package com.example.demo.demo.service;

import com.example.demo.demo.entity.UserEntity;
import com.example.demo.demo.model.UserDto;
import com.example.demo.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String addUser(UserDto userDto){
        UserEntity user = new UserEntity();
        user.setUsername(userDto.userName());
        user.setPassword(userDto.password());
        user.setRole(userDto.role());
        user = userRepository.save(user);
        return String.valueOf(user.getId());
    }

    public List<UserDto> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(u->new UserDto(u.getUsername(),u.getPassword(),u.getRole()))
                .collect(Collectors.toList());
    }
}
