package com.example.fashionblogapi.exceptions;

public class UserAlreadyExist extends RuntimeException{

    public UserAlreadyExist(String message) {
        super(message);
    }
}
