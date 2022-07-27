package com.example.fashionblogapi.services.serviceImpl;

import com.example.fashionblogapi.dto.CommentsDto;
import com.example.fashionblogapi.dto.LikesDto;
import com.example.fashionblogapi.dto.SignUpDto;
import com.example.fashionblogapi.exceptions.AlreadyLikesException;
import com.example.fashionblogapi.exceptions.CommentsAlreadyExist;
import com.example.fashionblogapi.exceptions.UserAlreadyExist;
import com.example.fashionblogapi.exceptions.UserNotFoundException;
import com.example.fashionblogapi.models.Comments;
import com.example.fashionblogapi.models.Likes;
import com.example.fashionblogapi.models.Post;
import com.example.fashionblogapi.models.User;
import com.example.fashionblogapi.models.postPage.PostPagination;
import com.example.fashionblogapi.repositories.CommentsRepository;
import com.example.fashionblogapi.repositories.LikesRepository;
import com.example.fashionblogapi.repositories.PostRepository;
import com.example.fashionblogapi.repositories.UserRepository;
import com.example.fashionblogapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final CommentsRepository commentsRepository;


    private final UserRepository userRepository;


    private final HttpSession httpSession;


    private final LikesRepository likesRepository;


    private final PostRepository postRepository;



    @Override
    public String signUp(SignUpDto signUpDto) {
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new UserAlreadyExist("User With " + signUpDto.getEmail() + " Already Exist");
        }
        else{
            User user = new User();
            BeanUtils.copyProperties(signUpDto, user);
            userRepository.save(user);
            return "SignUp Successfully";
        }

    }

    @Override
    public String login(SignUpDto signUpDto) {
        String email = signUpDto.getEmail();

        User user = userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("User Not Found"));
        if(!user.getPassword().equals(signUpDto.getPassword())){
            throw new UserNotFoundException("Email or Password Not Correct");
        }else {
            httpSession.setAttribute("found", user.getId());
            httpSession.setAttribute("permission","user");
            return "Login Successfully";
        }

    }


    public Long loginUserId(){
        Long id = (Long) httpSession.getAttribute("found");
        if(id==null){
            throw new UserNotFoundException("User Not Found! Login First");
        }
        return id;
    }

    @Override
    public String logout() {
        httpSession.invalidate();
        return "logout Successfully";
    }

    @Override
    public String comments(CommentsDto commentsDto) {
        Long ids = loginUserId();
        Optional<User> user = userRepository.findUserById(ids);
        if (user.isEmpty()) {
            throw new UserNotFoundException("You Must Login First");
        }
            Comments comments = new Comments();
            if (commentsRepository.existsCommentsById(loginUserId())) {
                throw new CommentsAlreadyExist("You Have Already Comments this Product!");
            }
            else {
                BeanUtils.copyProperties(commentsDto, comments);
                commentsRepository.save(comments);
                return "Thank You for Your Comments";
            }


    }

    @Override
    public Integer like(LikesDto likesDto) {
    int count = 0;
    Likes likes = new Likes();
     Long likesId = loginUserId();
     Optional<User> userOptional = userRepository.findUserById(likesId);
     if(userOptional.isEmpty()){
         throw new UserNotFoundException("You Must Login First");
     }
     if(likesRepository.existsLikesById(likesId)){
         throw new AlreadyLikesException("You Already Likes This Product");
     }
     else {
         BeanUtils.copyProperties(likesDto, likes);
         likesRepository.save(likes);

         for(int  i = 0; i< likesId; i++){
             count++;
         }

     }
         return count;

    }


    @Override
    public Page<Post> getAllPost(PostPagination postPagination) {
     Sort sort = Sort.by(postPagination.getSortDirection(), postPagination.getSortBy());
     Pageable pageable = PageRequest.of(postPagination.getPageNumber(), postPagination.getPageSize(), sort);
     Page<Post> post = postRepository.findAll(pageable);
        return post;
    }



}
