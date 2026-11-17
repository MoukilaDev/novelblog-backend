package com.moukiladev.novelblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateCommentRequest {
    @NotNull(message = "The id of the comment is required")
    private Long commentId;
    @NotBlank(message = "The content is required")
    private String content;

    //Constructors
    public UpdateCommentRequest(){}
    public UpdateCommentRequest(Long commentId, String content){
        this.commentId = commentId;
        this.content = content;
    }
    //Getters and Setters
    public Long getCommentId() { return commentId;}
    public String getContent() {return content;}
    public void setContent(String content) { this.content = content;}
}
