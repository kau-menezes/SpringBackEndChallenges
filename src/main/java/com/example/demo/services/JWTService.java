package com.example.demo.services;

public interface JWTService<T> {
    String get(T Token);
    T validate(String jwt);
}
