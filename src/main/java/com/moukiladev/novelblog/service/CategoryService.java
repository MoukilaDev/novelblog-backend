package com.moukiladev.novelblog.service;

import com.moukiladev.novelblog.dto.CategoryResponse;
import com.moukiladev.novelblog.dto.CreateCategoryRequest;
import com.moukiladev.novelblog.mapper.CategoryMapper;
import com.moukiladev.novelblog.model.Category;
import com.moukiladev.novelblog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryResponse> getAllCategories(){
        List<Category> savedCategories =  categoryRepository.findAll();

        return savedCategories.stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }
    public CategoryResponse createCategory(CreateCategoryRequest dto){
        Category requestedCategory= categoryMapper.toCategoryEntity(dto);
        Category savedCategory = categoryRepository.save(requestedCategory);
        return categoryMapper.toCategoryResponse(savedCategory);
    }
}
