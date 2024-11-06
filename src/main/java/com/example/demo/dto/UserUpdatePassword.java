package com.example.demo.dto;

public record UserUpdatePassword(String username, String password, String newPassword, String repeatPassword) {
    
}
