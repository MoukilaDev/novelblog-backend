package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.dto.comment.CommentResponse;
import com.moukiladev.novelblog.dto.comment.CreateCommentRequest;
import com.moukiladev.novelblog.dto.comment.UpdateCommentRequest;
import com.moukiladev.novelblog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
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
    public CommentResponse createCommentById(@AuthenticationPrincipal UserDetails currentUser,
                                             @Valid @RequestBody CreateCommentRequest dto){
        return commentService.createComment(currentUser, dto);
    }

    @PutMapping("/comment")
    public CommentResponse updateComment(@AuthenticationPrincipal UserDetails currentUser,
                                         @Valid @RequestBody UpdateCommentRequest dto){
        return commentService.updateComment(currentUser, dto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@AuthenticationPrincipal UserDetails currentUser,
                                  @PathVariable Long  commentId){
        commentService.deleteComment(currentUser, commentId);
    }
}
