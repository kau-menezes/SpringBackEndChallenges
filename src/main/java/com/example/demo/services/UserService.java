package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.UserData;
import com.example.demo.dto.UserUpdatePassword;

public interface UserService {
    ResponseEntity<String> createUser(UserData user);
    ResponseEntity<String> changePassword(UserUpdatePassword user);
    ResponseEntity<String> login(UserData user);
}
