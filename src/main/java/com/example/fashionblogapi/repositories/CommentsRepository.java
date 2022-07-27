package com.example.fashionblogapi.repositories;

import com.example.fashionblogapi.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    Optional<Comments> findCommentsByIdAndMessage(Long id, String message);
    boolean existsCommentsById(Long id);
}
