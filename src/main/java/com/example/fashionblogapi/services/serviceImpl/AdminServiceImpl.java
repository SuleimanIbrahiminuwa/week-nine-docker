package com.example.fashionblogapi.services.serviceImpl;

import com.example.fashionblogapi.dto.AdminDto;
import com.example.fashionblogapi.dto.PostDto;
import com.example.fashionblogapi.dto.UpdatePost;
import com.example.fashionblogapi.exceptions.AdminNotFoundException;
import com.example.fashionblogapi.exceptions.InvalidEmailException;
import com.example.fashionblogapi.exceptions.PostAlreadyExist;
import com.example.fashionblogapi.exceptions.PostNotFoundExceptions;
import com.example.fashionblogapi.models.Admin;
import com.example.fashionblogapi.models.Comments;
import com.example.fashionblogapi.models.Likes;
import com.example.fashionblogapi.models.Post;
import com.example.fashionblogapi.repositories.AdminRepository;
import com.example.fashionblogapi.repositories.CommentsRepository;
import com.example.fashionblogapi.repositories.LikesRepository;
import com.example.fashionblogapi.repositories.PostRepository;
import com.example.fashionblogapi.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final PostRepository postRepository;


    private final AdminRepository adminRepository;

    private final HttpSession httpSession;


    private final CommentsRepository commentsRepository;

    private final LikesRepository likesRepository;





    @Override
    public String login(AdminDto adminDto) {
        String email = adminDto.getEmail();
        Admin admin = adminRepository.findAdminByEmail(email).orElseThrow(()-> new AdminNotFoundException("Not Found"));
        if(!admin.getPassword().equals(adminDto.getPassword())) {
            throw new InvalidEmailException("Invalid Email or Password");
        }
           httpSession.setAttribute("found", adminDto.getEmail());
           httpSession.setAttribute("granted", "admin");
           return "Login Successfully";

    }

    @Override
    public String postProduct(PostDto postDto) {
        Post post = new Post();
     if(postRepository.existsPostByName(postDto.getName())){
         throw new PostAlreadyExist("The post  " + postDto.getName() + " Already Exist");
     }
     else {

         BeanUtils.copyProperties(postDto, post);
         postRepository.save(post);
         return "Product Posted Successfully";
     }

    }

    @Override
    public String deleteProduct(Long id) {
    Post post = postRepository.findPostById(id).orElseThrow(()-> new PostNotFoundExceptions("Not Found"));
    postRepository.delete(post);
    return "Post Deleted Successfully";
    }

    @Override
    public String updateProduct(UpdatePost updatePost, Long id) {
        Post post = postRepository.findPostById(id).orElseThrow(()-> new PostNotFoundExceptions("This post not Found"));
            BeanUtils.copyProperties(updatePost, post);
            postRepository.save(post);
            return "Product Updated Successfully";
    }
    @Override
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    @Override
    public List<Likes> getAllLikes() {
        return likesRepository.findAll();
    }

}
