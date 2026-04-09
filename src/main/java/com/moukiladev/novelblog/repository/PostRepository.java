package com.moukiladev.novelblog.repository;

import com.moukiladev.novelblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
