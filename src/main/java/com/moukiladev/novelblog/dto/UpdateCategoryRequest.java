package com.moukiladev.novelblog.dto;


import jakarta.validation.constraints.NotBlank;

public class UpdateCategoryRequest {
    @NotBlank(message = "category name is required")
    private String categoryName;
    // Constructor
    public UpdateCategoryRequest(){}
    public UpdateCategoryRequest(String categoryName){
        this.categoryName = categoryName;
    }
    // Getters and Setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
