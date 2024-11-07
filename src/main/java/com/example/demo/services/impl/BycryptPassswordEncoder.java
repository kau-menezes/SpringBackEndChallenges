package com.example.demo.services.impl;

import com.example.demo.services.PasswordEncoderService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class BycryptPassswordEncoder implements PasswordEncoderService{

    @Override
    public String encode(String password) {
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    
}
