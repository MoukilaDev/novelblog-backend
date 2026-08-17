package com.moukiladev.novelblog.repository;

import com.moukiladev.novelblog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {

}
