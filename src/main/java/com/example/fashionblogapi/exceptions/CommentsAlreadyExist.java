package com.example.fashionblogapi.exceptions;

public class CommentsAlreadyExist extends RuntimeException{
    public CommentsAlreadyExist(String message) {
        super(message);
    }
}
