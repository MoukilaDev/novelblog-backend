package com.moukiladev.novelblog.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateCategoryRequest {
    @NotBlank(message = "The name is required")
    private String categoryName;
    //Constructor
    public CreateCategoryRequest(){};
    public CreateCategoryRequest(String categoryName){
        this.categoryName = categoryName;
    }
    //Getters and Setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
