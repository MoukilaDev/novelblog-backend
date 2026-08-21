package com.moukiladev.novelblog.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateCommentRequest {

    @NotBlank(message = "The content must be filled")
    private String content;

    //Constructors
    public UpdateCommentRequest(){}
    public UpdateCommentRequest(String content){
        this.content = content;
    }

    //Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
