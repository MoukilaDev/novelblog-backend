package com.moukiladev.novelblog.mapper;

import com.moukiladev.novelblog.dto.category.CategoryResponse;
import com.moukiladev.novelblog.dto.category.CreateCategoryRequest;
import com.moukiladev.novelblog.dto.category.UpdateCategoryRequest;
import com.moukiladev.novelblog.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public CategoryResponse toCategoryResponse(Category category);
    public Category toCategoryEntity(CreateCategoryRequest dto);
    public void updateFromDto(UpdateCategoryRequest dto, @MappingTarget Category category);
}
