package com.example.fashionblogapi.services;

import com.example.fashionblogapi.dto.AdminDto;
import com.example.fashionblogapi.dto.PostDto;
import com.example.fashionblogapi.dto.UpdatePost;
import com.example.fashionblogapi.models.Comments;
import com.example.fashionblogapi.models.Likes;

import java.util.List;

public interface AdminService {

    String login(AdminDto adminDto);

    String postProduct(PostDto postDto);

    String deleteProduct(Long id);

    String updateProduct(UpdatePost updatePost, Long id);

    List<Comments> getAllComments();

    List<Likes> getAllLikes();
}
