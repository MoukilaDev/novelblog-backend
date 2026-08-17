package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import com.moukiladev.novelblog.model.Category;
import com.moukiladev.novelblog.model.Post;
import com.moukiladev.novelblog.repository.CategoryRepository;
import com.moukiladev.novelblog.repository.PostRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    // Repository injection (access to db)
    public PostController(PostRepository postRepository, CategoryRepository categoryRepository){
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    }

    @PostMapping("/{categoryId}/post") // JSON -> Java object
    public Post createPost(@Valid @RequestBody Post newpost, @PathVariable Long categoryId){
        Category category= categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));

        newpost.setCategory(category);
        return postRepository.save(newpost);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post post){
        Post updatedPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));

        updatedPost.setTitle(post.getTitle());
        updatedPost.setContent(post.getContent());
        return postRepository.save(updatedPost);
    }

    @DeleteMapping("/{postId}/Post")
    public void deletePost(@PathVariable Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));

        postRepository.delete(post);
    }

}
