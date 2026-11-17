package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.dto.category.CategoryResponse;
import com.moukiladev.novelblog.dto.category.CreateCategoryRequest;
import com.moukiladev.novelblog.dto.category.UpdateCategoryRequest;
import com.moukiladev.novelblog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/category")
    public CategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest category){
        return categoryService.createCategory(category);
    }

    @PutMapping("/category/{categoryId}")
    public CategoryResponse updateCategory(@PathVariable Long categoryId
            ,@Valid @RequestBody UpdateCategoryRequest dto) {
        return categoryService.updateCategory(categoryId, dto);
    }

}
