package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.model.Post;
import com.moukiladev.novelblog.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;
    // Repository injection (access to db)
    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> getAllPosts(){return postRepository.findAll();}

    @PostMapping // JSON -> Java object
    public Post createPost(@RequestBody Post post){return postRepository.save(post);}

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postRepository.deleteById(id);
    }

}
