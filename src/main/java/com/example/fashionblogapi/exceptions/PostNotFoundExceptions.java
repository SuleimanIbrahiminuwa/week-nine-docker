package com.example.fashionblogapi.exceptions;

public class PostNotFoundExceptions extends RuntimeException{
    public PostNotFoundExceptions(String message) {
        super(message);
    }
}
