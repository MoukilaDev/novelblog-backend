package com.moukiladev.novelblog.dto;

public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
    //Constructors
    public CategoryResponse(){};
    public CategoryResponse(Long categoryId, String categoryName){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    //Getters and Setters
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
