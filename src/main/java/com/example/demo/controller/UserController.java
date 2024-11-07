package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserData;
import com.example.demo.dto.UserUpdatePassword;
import com.example.demo.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService service;

    /* ----------------------------------- C6 ----------------------------------- */

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserData user) {
        return service.createUser(user);
    }

    /* ----------------------------------- C7 ----------------------------------- */

    @PatchMapping("/changepassword")

    public ResponseEntity<String> updatePasswordUser(@RequestBody UserUpdatePassword user) {
        return service.changePassword(user);
    }
    
}
