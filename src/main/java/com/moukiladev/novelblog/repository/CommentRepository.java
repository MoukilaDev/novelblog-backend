package com.moukiladev.novelblog.repository;

import com.moukiladev.novelblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long> {
    public List<Comment> findAllByPostId(Long postId);
    public List<Comment> findAllByAuthor_Username(String authorName);
}