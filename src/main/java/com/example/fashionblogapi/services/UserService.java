package com.example.fashionblogapi.services;

import com.example.fashionblogapi.dto.CommentsDto;
import com.example.fashionblogapi.dto.LikesDto;
import com.example.fashionblogapi.dto.SignUpDto;
import com.example.fashionblogapi.models.Post;
import com.example.fashionblogapi.models.postPage.PostPagination;
import org.springframework.data.domain.Page;

public interface UserService {

    String signUp(SignUpDto signUpDto);

    String login(SignUpDto signUpDto);

    String logout();

    String comments(CommentsDto commentsDto);

    Integer like(LikesDto likesDto);



    Page<Post> getAllPost(PostPagination postPagination);


}
