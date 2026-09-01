package com.moukiladev.novelblog.service;

import com.moukiladev.novelblog.dto.CategoryResponse;
import com.moukiladev.novelblog.dto.CreateCategoryRequest;
import com.moukiladev.novelblog.dto.UpdateCategoryRequest;
import com.moukiladev.novelblog.exception.ResourceAlreadyExist;
import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import com.moukiladev.novelblog.mapper.CategoryMapper;
import com.moukiladev.novelblog.model.Category;
import com.moukiladev.novelblog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

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
        String normalizedCategoryName =  dto.getCategoryName().trim().toLowerCase();
        if(categoryRepository.existsByCategoryName(normalizedCategoryName)){
            throw new ResourceAlreadyExist("This category name already exist");
        }

        dto.setCategoryName(normalizedCategoryName);
        Category requestedCategory= categoryMapper.toCategoryEntity(dto);
        Category savedCategory = categoryRepository.save(requestedCategory);
        return categoryMapper.toCategoryResponse(savedCategory);
    }

    public CategoryResponse updateCategory (Long categoryId, UpdateCategoryRequest dto){
        String normalizedCategoryName =  dto.getCategoryName().trim().toLowerCase();
        if( categoryRepository.existsByCategoryNameAndCategoryIdNot(normalizedCategoryName, categoryId)){
            throw new ResourceAlreadyExist("This category name already exist");
        }
        dto.setCategoryName(normalizedCategoryName);
        Category dbCategory = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryMapper.updateFromDto(dto, dbCategory);

        return categoryMapper.toCategoryResponse(categoryRepository.save(dbCategory));

    }
}
