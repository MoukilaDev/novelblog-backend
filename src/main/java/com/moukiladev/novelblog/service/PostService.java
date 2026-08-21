package com.moukiladev.novelblog.service;

import com.moukiladev.novelblog.dto.CreatePostRequest;
import com.moukiladev.novelblog.dto.UpdatePostRequest;
import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import com.moukiladev.novelblog.model.Category;
import com.moukiladev.novelblog.model.Post;
import com.moukiladev.novelblog.repository.CategoryRepository;
import com.moukiladev.novelblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    public PostService(PostRepository postRepository, CategoryRepository categoryRepository){
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post findById(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
    }

    public Post createPost(CreatePostRequest dto){
        Post requestedPost = new Post();
        Category category= categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));

        requestedPost.setTitle(dto.getTitle());
        requestedPost.setContent(dto.getContent());
        requestedPost.setCategory(category);

        return postRepository.save(requestedPost);
    }

    public Post updatePost(Long postId, UpdatePostRequest dto){
        Post requestedPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException ("Category of this post not found"));

        requestedPost.setTitle(dto.getTitle());
        requestedPost.setContent(dto.getContent());
        requestedPost.setCategory(category);

        return postRepository.save(requestedPost);
    }

    public void deleteByPostId(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));

        postRepository.delete(post);
    }

}
