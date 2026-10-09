package com.moukiladev.novelblog.repository;

import com.moukiladev.novelblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
