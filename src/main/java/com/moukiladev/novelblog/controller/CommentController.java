package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.model.Comment;
import com.moukiladev.novelblog.model.Post;
import com.moukiladev.novelblog.repository.CommentRepository;
import com.moukiladev.novelblog.repository.PostRepository;
import org.aspectj.bridge.ICommand;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    public CommentController(PostRepository postRepository ,CommentRepository commentRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId){
        return  commentRepository.findByPostId(postId);
    }

    @PostMapping("/{postId}/comments")
    public Comment createCommentById(@PathVariable Long postId, @RequestBody Comment newComment){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        System.out.println("post reached");
        newComment.setPost(post);
        System.out.println("comment associated with the post");
        return commentRepository.save(newComment);
    }

    @PutMapping("/comments/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody Comment comment){
        Comment updatedComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("comment not found"));
        updatedComment.setContent(comment.getContent());
        updatedComment.setReaderName(comment.getReaderName());
        return commentRepository.save(updatedComment);
    }

    @DeleteMapping("/{commentId}/comments")
    public void deleteCommentById(@PathVariable Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Post not found"));
        commentRepository.delete(comment);
    }
}
