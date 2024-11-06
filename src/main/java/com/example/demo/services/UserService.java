package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.UserData;

public interface UserService {
    ResponseEntity<String> createUser(UserData user);
}
