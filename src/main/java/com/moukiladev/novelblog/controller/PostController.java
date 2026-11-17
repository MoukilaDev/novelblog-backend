package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.dto.post.CreatePostRequest;
import com.moukiladev.novelblog.dto.post.PostResponse;
import com.moukiladev.novelblog.dto.post.UpdatePostRequest;
import com.moukiladev.novelblog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public PostResponse createPost(@AuthenticationPrincipal UserDetails currentUser
            , @Valid @RequestBody CreatePostRequest dto){
        return postService.createPost(currentUser, dto);
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(@AuthenticationPrincipal UserDetails currentUser
            , @Valid @RequestBody UpdatePostRequest dto,  @PathVariable Long id){
        return postService.updatePost(currentUser, dto, id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetails currentUser) {
        postService.deleteByPostId(id, currentUser);
    }

}
