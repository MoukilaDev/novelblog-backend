package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.dto.CommentResponse;
import com.moukiladev.novelblog.dto.CreateCommentRequest;
import com.moukiladev.novelblog.dto.UpdateCommentRequest;
import com.moukiladev.novelblog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }


    @GetMapping("/{postId}/comments")
    public List<CommentResponse> getCommentsByPostId(@PathVariable Long postId){
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping("/comment")
    public CommentResponse createCommentById(@Valid @RequestBody CreateCommentRequest dto){

        return commentService.createComment(dto);
    }

    @PutMapping("/{commentId}")
    public CommentResponse updateComment(@PathVariable Long commentId , @Valid @RequestBody UpdateCommentRequest dto){
        return commentService.updateComment(commentId,dto);
    }

    @DeleteMapping("/{commentId}/comments")
    public void deleteCommentById(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
}
