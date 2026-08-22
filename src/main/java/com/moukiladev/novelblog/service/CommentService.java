package com.moukiladev.novelblog.service;

import com.moukiladev.novelblog.dto.CommentResponse;
import com.moukiladev.novelblog.dto.CreateCommentRequest;
import com.moukiladev.novelblog.dto.UpdateCommentRequest;
import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import com.moukiladev.novelblog.mapper.CommentMapper;
import com.moukiladev.novelblog.model.Comment;
import com.moukiladev.novelblog.model.Post;
import com.moukiladev.novelblog.repository.CommentRepository;
import com.moukiladev.novelblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    // constructor
    public CommentService(PostRepository postRepository, CommentRepository commentRepository, CommentMapper commentMapper){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }
    //Methods

    public List<CommentResponse> getCommentsByPostId(Long postId){
        List<CommentResponse> dtoResponses = new ArrayList<>();
        List<Comment> comments = commentRepository.findByPostId(postId);
        for(Comment theComment : comments){
                dtoResponses.add(commentMapper.toCommentResponse(theComment));
        }
        return dtoResponses;
    }

    public CommentResponse createComment(CreateCommentRequest dto){
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        System.out.println("post reached");

        Comment requestedComment = commentMapper.toCommentEntity(dto);
        requestedComment.setPost(post);
        Comment savedComment = commentRepository.save(requestedComment);

        return commentMapper.toCommentResponse(savedComment);
    }

    public CommentResponse updateComment(Long commentId, UpdateCommentRequest dto){
        Comment requestedComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException ("comment not found"));

        requestedComment.setContent(dto.getContent());
        Comment savedComment = commentRepository.save(requestedComment);

        return commentMapper.toCommentResponse(savedComment);
    }

    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));
        commentRepository.delete(comment);
    }

}
