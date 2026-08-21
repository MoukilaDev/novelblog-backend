package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import com.moukiladev.novelblog.model.Category;
import com.moukiladev.novelblog.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @PostMapping
    public Category createCategory(@Valid @RequestBody Category category){
        return categoryRepository.save(category);
    }

}
