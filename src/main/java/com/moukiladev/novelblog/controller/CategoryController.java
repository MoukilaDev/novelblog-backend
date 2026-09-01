package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.dto.CategoryResponse;
import com.moukiladev.novelblog.dto.CreateCategoryRequest;
import com.moukiladev.novelblog.dto.UpdateCategoryRequest;
import com.moukiladev.novelblog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CreateCategoryRequest category){
        return categoryService.createCategory(category);
    }

    @PutMapping("/{categoryId}")
    public CategoryResponse updateCategory(@PathVariable Long categoryId
            ,@Valid @RequestBody UpdateCategoryRequest dto) {
        return categoryService.updateCategory(categoryId, dto);
    }
}
