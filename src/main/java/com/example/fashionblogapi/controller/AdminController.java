package com.example.fashionblogapi.controller;

import com.example.fashionblogapi.dto.AdminDto;
import com.example.fashionblogapi.dto.PostDto;
import com.example.fashionblogapi.dto.UpdatePost;
import com.example.fashionblogapi.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {


    private final AdminService adminService;


    @PostMapping("/post")
    public ResponseEntity<?> postProduct(@RequestBody PostDto postDto){
        return new ResponseEntity<>(adminService.postProduct(postDto), HttpStatus.OK);
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<?> adminLogin(@RequestBody AdminDto adminDto) {
        return new ResponseEntity<>(adminService.login(adminDto), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id){
        return new ResponseEntity<>(adminService.deleteProduct(id),HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") Long id, @RequestBody UpdatePost updatePost){
      return new ResponseEntity<>(adminService.updateProduct(updatePost, id),HttpStatus.OK);
    }

    @GetMapping("/getComments")
    public ResponseEntity viewComments(){

        return new ResponseEntity<>(adminService.getAllComments(),HttpStatus.OK);
    }

    @GetMapping("/getLikes")
    public ResponseEntity viewLikes(){
        return new ResponseEntity<>(adminService.getAllLikes(),HttpStatus.OK);
    }
}
