package com.moukiladev.novelblog.service;

import com.moukiladev.novelblog.dto.comment.CommentResponse;
import com.moukiladev.novelblog.dto.comment.CreateCommentRequest;
import com.moukiladev.novelblog.dto.comment.UpdateCommentRequest;
import com.moukiladev.novelblog.entity.Comment;
import com.moukiladev.novelblog.entity.Post;
import com.moukiladev.novelblog.entity.UserEntity;
import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import com.moukiladev.novelblog.mapper.CommentMapper;
import com.moukiladev.novelblog.repository.CommentRepository;
import com.moukiladev.novelblog.repository.PostRepository;
import com.moukiladev.novelblog.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    // constructor
    public CommentService(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository, CommentMapper commentMapper){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.commentMapper = commentMapper;
    }
    //methods
    public List<CommentResponse> getCommentsByPostId(Long postId){
        List<Comment> dbComments = commentRepository.findAllByPostId(postId);
        return dbComments.stream()
                .map(commentMapper::toCommentResponse)
                .toList();
    }

    public List<CommentResponse> findByAuthor(String authorName){
        List<Comment> dbComments = commentRepository.findAllByAuthor_Username(authorName);
        return dbComments.stream()
                .map(commentMapper::toCommentResponse)
                .toList();
    }
    public CommentResponse createComment(UserDetails currentUser, CreateCommentRequest dto){
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this comment"));
        UserEntity author = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));

        Comment requestedComment = commentMapper.toCommentEntity(dto);
        requestedComment.setPost(post);
        requestedComment.setAuthor(author);
        Comment savedComment = commentRepository.save(requestedComment);

        return commentMapper.toCommentResponse(savedComment);
    }

    public CommentResponse updateComment(UserDetails currentUser, UpdateCommentRequest dto){
        Comment requestedComment = commentRepository.findById(dto.getCommentId())
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        if(!requestedComment.getAuthor().getUsername().equals(currentUser.getUsername())){
            throw new AccessDeniedException("Unauthorized");
        }
        commentMapper.updateCommentFromDto(dto, requestedComment);
        Comment savedComment = commentRepository.save(requestedComment);

        return commentMapper.toCommentResponse(savedComment);
    }

    public void deleteComment(UserDetails currentUser, Long commentId){
        if(currentUser.getAuthorities()
                .stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
            throw new AccessDeniedException("Unauthorized");
        Comment dbComment = commentRepository.findById(commentId)
                        .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        commentRepository.deleteById(commentId);
    }

}
