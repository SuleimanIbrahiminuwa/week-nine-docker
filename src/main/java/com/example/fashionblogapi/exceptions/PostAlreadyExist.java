package com.example.fashionblogapi.exceptions;

public class PostAlreadyExist extends RuntimeException{

    public PostAlreadyExist(String message) {
        super(message);
    }
}
