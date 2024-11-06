package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.UserData;
import com.example.demo.dto.UserUpdatePassword;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

public class DefaultUserImplementation implements UserService {

    @Autowired
    UserRepository userRepo;

    public Boolean verifyPasswordLenght(String password) {
        return password.length() >= 8;
    }

    public Boolean verifyPasswordUpperCaseLetters(String password) {
        boolean upper = false;

        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 65 && password.charAt(i) <= 90) {
                upper = true;
                break;
            }
        }

        return upper;
    }

    public Boolean verifyPasswordLowerCaseLetters(String password) {
        boolean lower = false;

        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 97 && password.charAt(i) <= 122) {
                lower = true;
                break;
            }

        }

        return lower;
    }

    public Boolean verifyPasswordNumbers(String password) {
        boolean nums = false;

        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 48 && password.charAt(i) <= 57) {
                nums = true;
                break;
            }
        }

        return nums;
        
    }

    public Boolean verifyOverallPassword(String password) {
        
        Boolean psLength = verifyPasswordLenght(password);

        Boolean psUpper = verifyPasswordUpperCaseLetters(password);

        Boolean psLower = verifyPasswordLowerCaseLetters(password);

        Boolean psNums = verifyPasswordNumbers(password);

        return psLength && psUpper && psLower && psNums;
    }

    @Override
    public ResponseEntity<String> createUser(UserData user) {
        var cities = userRepo.findByEmail(user.email());

        if (cities.size() > 0) {
            return new ResponseEntity<String>("E-mail already in use by other user.",HttpStatus.CONFLICT); // 409
        }

        Boolean psLength = verifyPasswordLenght(user.password());

        if (!psLength)
            return new ResponseEntity<String>("Password must be at least 8 characters long",HttpStatus.UNPROCESSABLE_ENTITY); // 422

        Boolean psUpper = verifyPasswordUpperCaseLetters(user.password());

        if (!psUpper)
            return new ResponseEntity<String>("Password must have at least one (1) upper case letter.",HttpStatus.UNPROCESSABLE_ENTITY);

        Boolean psLower = verifyPasswordLowerCaseLetters(user.password());

        if (!psLower)
            return new ResponseEntity<String>("Password must have at least one (1) lower case letter.",HttpStatus.UNPROCESSABLE_ENTITY);

        Boolean psNums = verifyPasswordNumbers(user.password());

        if (!psNums)
            return new ResponseEntity<String>("Password must have at elast one (1) number.",HttpStatus.UNPROCESSABLE_ENTITY);
        
        User newUser = new User();
        newUser.setEmail(user.email());
        newUser.setPassword(user.password());
        newUser.setUsername(user.username());

        userRepo.saveAndFlush(newUser);

        return new ResponseEntity<String>("User registered with success!",HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> changePassword(UserUpdatePassword data) {
        User user = userRepo.findByUsername(data.username()).get(0);

        if (user == null) {
            return new ResponseEntity<String>("User not found.",HttpStatus.NOT_FOUND);

        }

        Boolean ps = verifyOverallPassword(data.newPassword());

        if (!ps) {
            return new ResponseEntity<String>("New password must submit to all requisites of validation.",HttpStatus.UNPROCESSABLE_ENTITY);

        }

        if (!data.newPassword().equals(data.repeatPassword())) {
            return new ResponseEntity<String>("The passwords do not match.",HttpStatus.UNPROCESSABLE_ENTITY);

        }

        user.setPassword(data.newPassword());

        userRepo.save(user);

        return new ResponseEntity<String>("Password updated with success!",HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> login(UserData user) {
        var found = userRepo.findByUsername(user.username()).get(0);

        if (found == null)
            return ResponseEntity.status(404).build();

        if (!found.getPassword().equals(user.password()))
            return ResponseEntity.status(403).build();

        return ResponseEntity.ok("welcome, user");
    }

}
