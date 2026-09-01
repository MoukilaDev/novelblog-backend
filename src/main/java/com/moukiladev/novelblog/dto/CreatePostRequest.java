package com.moukiladev.novelblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreatePostRequest {
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "content is required")
    private String content;
    @NotNull(message = "The id of the category is required")
    private Long categoryId;

    //contructors
    public CreatePostRequest(){}
    public CreatePostRequest(String title, String content, Long categoryId){
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
    }

    //Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}