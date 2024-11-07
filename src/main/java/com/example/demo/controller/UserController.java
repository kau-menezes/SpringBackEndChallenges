package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.Token;
import com.example.demo.dto.UserData;
import com.example.demo.dto.UserDataLogin;
import com.example.demo.dto.UserUpdatePassword;
import com.example.demo.services.JWTService;
import com.example.demo.services.PasswordEncoderService;
import com.example.demo.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    PasswordEncoderService encoder;

    @Autowired
    JWTService<Token> jwtService;

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

    
    /* ----------------------------------- C8 ----------------------------------- */
    
    @PostMapping("/user")
    public ResponseEntity<String> createEncodedUser(@RequestBody UserData user) {
        return service.createUser(user);
    }

    /* ----------------------------------- C9 ----------------------------------- */
     @PostMapping("/login")
    public ResponseEntity<AuthResponse> create(@RequestBody UserDataLogin loginData) {

        if (loginData.login() == null || loginData.password() == null) {
            return new ResponseEntity<AuthResponse>(new AuthResponse("Both login and password are necessary", null), HttpStatus.BAD_REQUEST);
        }

        UserData userData = new UserData(loginData.login(), loginData.login(), loginData.password());

        var users = service.login(userData);

        if (users.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity<AuthResponse>(new AuthResponse("Incorrect login!", null), HttpStatus.BAD_REQUEST);

        }

        Token token = new Token(userData.username(), userData.email());
        
        var jwt = jwtService.get(token);

        return new ResponseEntity<>(new AuthResponse("Autenticado!", jwt),HttpStatus.OK);
    }
    
    
}
