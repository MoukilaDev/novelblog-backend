package com.moukiladev.novelblog.service;

import com.moukiladev.novelblog.dto.post.CreatePostRequest;
import com.moukiladev.novelblog.dto.post.PostResponse;
import com.moukiladev.novelblog.dto.post.UpdatePostRequest;
import com.moukiladev.novelblog.entity.UserEntity;
import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import com.moukiladev.novelblog.mapper.PostMapper;
import com.moukiladev.novelblog.entity.Category;
import com.moukiladev.novelblog.entity.Post;
import com.moukiladev.novelblog.repository.CategoryRepository;
import com.moukiladev.novelblog.repository.PostRepository;
import com.moukiladev.novelblog.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;
    public PostService(PostRepository postRepository, CategoryRepository categoryRepository, UserRepository userRepository
            , PostMapper postMapper){
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;

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

    public PostResponse createPost(UserDetails currentUser, CreatePostRequest dto){
        Category category= categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category not found"));

        UserEntity author = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(()-> new AccessDeniedException("User not found"));

        if(currentUser.getAuthorities()
                .stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
            throw new AccessDeniedException("Unauthorized");

        Post requestedPost = postMapper.toPostEntity(dto);
        requestedPost.setAuthor(author);
        requestedPost.setCategory(category);
        Post savedPost = postRepository.save(requestedPost);

        return postMapper.toPostResponse(savedPost);
    }

    public PostResponse updatePost(UserDetails currentUser, UpdatePostRequest dto, Long id){
        // Control feature access
        if(currentUser.getAuthorities()
                .stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
            throw new AccessDeniedException("Unauthorized");

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException ("Category of this post not found"));

        Post dbPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));

        postMapper.updatePostFromDto(dto, dbPost);
        dbPost.setCategory(category);
        Post savedPost = postRepository.save(dbPost);
         return postMapper.toPostResponse(savedPost);
    }

    public void deleteByPostId(Long postId, UserDetails currentUser){
        if(currentUser.getAuthorities()
                .stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
            throw new AccessDeniedException("Unauthorized");

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));
        postRepository.delete(post);
    }
}
