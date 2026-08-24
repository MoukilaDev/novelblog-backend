package com.moukiladev.novelblog.service;

import com.moukiladev.novelblog.dto.CreatePostRequest;
import com.moukiladev.novelblog.dto.PostResponse;
import com.moukiladev.novelblog.dto.UpdatePostRequest;
import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import com.moukiladev.novelblog.mapper.PostMapper;
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
    private final PostMapper postMapper;
    public PostService(PostRepository postRepository, CategoryRepository categoryRepository
            , PostMapper postMapper){
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.postMapper = postMapper;
    }

    public List<PostResponse> getAllPosts(){
        List<Post> savedPosts = postRepository.findAll();

        return savedPosts.stream()
                .map(postMapper::toPostResponse)
                .toList();
    }

    public PostResponse findById(Long id){
            Post savedPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

            return postMapper.toPostResponse(savedPost);
    }

    public PostResponse createPost(CreatePostRequest dto){
        Category category= categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));

        Post requestedPost = postMapper.toPostEntity(dto);
        requestedPost.setCategory(category);
        Post savedPost = postRepository.save(requestedPost);

        return postMapper.toPostResponse(savedPost);
    }

    public PostResponse updatePost(Long postId, UpdatePostRequest dto){
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException ("Category of this post not found"));

        Post dbPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));

        postMapper.updatePostFromDto(dto, dbPost);
        dbPost.setCategory(category);
        Post savedPost = postRepository.save(dbPost);
         return postMapper.toPostResponse(savedPost);
    }

    public void deleteByPostId(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));

        postRepository.delete(post);
    }

}
