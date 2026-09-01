package com.moukiladev.novelblog.mapper;

import com.moukiladev.novelblog.dto.CategoryResponse;
import com.moukiladev.novelblog.dto.CreateCategoryRequest;
import com.moukiladev.novelblog.dto.UpdateCategoryRequest;
import com.moukiladev.novelblog.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public CategoryResponse toCategoryResponse(Category category);
    public Category toCategoryEntity(CreateCategoryRequest dto);
    public void updateFromDto(UpdateCategoryRequest dto, @MappingTarget Category category);
}
