package com.moukiladev.novelblog.repository;

import com.moukiladev.novelblog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public boolean existsByCategoryNameAndIdNot(String categoryName, Long id);
    public boolean existsByCategoryName(String categoryName);
}
