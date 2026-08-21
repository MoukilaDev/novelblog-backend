package com.moukiladev.novelblog.dto;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdatePostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Long categoryId;
    // Constructors
    public UpdatePostRequest(){};
    public UpdatePostRequest(String title, String content, Long categoryId){
        this.title =   title;
        this.content = content;
        this.categoryId = categoryId;
    }
    // Getters and Setteres
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

    public void setCategoryId( Long categoryId) {
        this.categoryId = categoryId;
    }
}
