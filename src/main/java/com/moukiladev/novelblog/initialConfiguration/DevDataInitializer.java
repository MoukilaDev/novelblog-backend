package com.moukiladev.novelblog.initialConfiguration;

import com.moukiladev.novelblog.entity.Category;
import com.moukiladev.novelblog.entity.Comment;
import com.moukiladev.novelblog.entity.Post;
import com.moukiladev.novelblog.entity.UserEntity;
import com.moukiladev.novelblog.repository.CategoryRepository;
import com.moukiladev.novelblog.repository.PostRepository;
import com.moukiladev.novelblog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
@Order(2)
public class DevDataInitializer implements CommandLineRunner {
    @Value("${application.security.admin.name}")
    private String adminName;
    private final UserRepository userRepository;
    private  final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private static final Logger log =
            LoggerFactory.getLogger(DevDataInitializer.class);

    public DevDataInitializer (UserRepository userRepository, CategoryRepository categoryRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(postRepository.count() == 0) {
            Category category = new Category("fantasy");
            UserEntity adminTest = userRepository.findByUsername(adminName)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            String content = "Vesta, Novaria and Kaho, encountered a S rank mercenary who's is trying to force the last one to join him around his beer. A confrontation engaged between the young waitress and the swordman.";
            List<Comment> comments = new ArrayList<>();
            Post newPost = new Post("Vesta Rising", content, adminTest, category, comments);

            categoryRepository.save(category);
            postRepository.save(newPost);
        }else{
            log.info("Dev Post already initialized");
        }
    }
}