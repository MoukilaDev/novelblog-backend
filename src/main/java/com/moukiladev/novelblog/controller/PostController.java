package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.dto.CreatePostRequest;
import com.moukiladev.novelblog.dto.PostResponse;
import com.moukiladev.novelblog.dto.UpdatePostRequest;
import com.moukiladev.novelblog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService){
        this.postService= postService;
    }

    @GetMapping
    public List<PostResponse> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable Long id){
        return postService.findById(id);
    }

    @PostMapping // JSON -> Java object
    public PostResponse createPost(@Valid @RequestBody CreatePostRequest dto){
        return postService.createPost(dto);
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable Long id, @Valid @RequestBody UpdatePostRequest dto){
        return postService.updatePost(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deleteByPostId(id);
    }

}
