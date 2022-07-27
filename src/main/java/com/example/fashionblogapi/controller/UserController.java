package com.example.fashionblogapi.controller;

import com.example.fashionblogapi.dto.CommentsDto;
import com.example.fashionblogapi.dto.LikesDto;
import com.example.fashionblogapi.dto.SignUpDto;
import com.example.fashionblogapi.models.Post;
import com.example.fashionblogapi.models.postPage.PostPagination;
import com.example.fashionblogapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @PostMapping("/signUp")
    public ResponseEntity<?> userSignUp(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(userService.signUp(signUpDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(userService.login(signUpDto), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        return new ResponseEntity<>(userService.logout(), HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<?> comment(@RequestBody CommentsDto commentsDto){
        return new ResponseEntity<>(userService.comments(commentsDto), HttpStatus.OK);
    }

    @PostMapping("/likes")
    public ResponseEntity<?> comment(@RequestBody LikesDto likesDto){
        return new ResponseEntity<>(userService.like(likesDto),HttpStatus.OK);
    }
    @GetMapping("/viewPostPages")
    public ResponseEntity<Page<Post>> getAllPost(PostPagination postPagination) {
        return ResponseEntity.ok(userService.getAllPost(postPagination));
    }


}
