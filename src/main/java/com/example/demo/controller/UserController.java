package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserData;
import com.example.demo.services.UserService;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    UserService service;

    /* ----------------------------------- C6 ----------------------------------- */

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserData user) {
        return service.createUser(user);
    }
    
}
