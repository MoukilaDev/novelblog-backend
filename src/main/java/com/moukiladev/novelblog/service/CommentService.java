package com.moukiladev.novelblog.service;

import com.moukiladev.novelblog.dto.CommentResponse;
import com.moukiladev.novelblog.dto.CreateCommentRequest;
import com.moukiladev.novelblog.dto.UpdateCommentRequest;
import com.moukiladev.novelblog.exception.ResourceNotFoundException;
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
    // constructor
    public CommentService(PostRepository postRepository, CommentRepository commentRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<CommentResponse> getCommentsByPostId(Long postId){
        List<CommentResponse> dtoResponses = new ArrayList<>();
        List<Comment> comments = commentRepository.findByPostId(postId);
        for(Comment theComment : comments){
                CommentResponse commentResponse = new CommentResponse();
                commentResponse.setId(theComment.getId());
                commentResponse.setReaderName(theComment.getReaderName());
                commentResponse.setContent(theComment.getContent());

                dtoResponses.add(commentResponse);
        }
        return dtoResponses;
    }

    public CommentResponse createComment(CreateCommentRequest dto){
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        System.out.println("post reached");
        Comment requestedComment = new Comment();

        requestedComment.setReaderName(dto.getReaderName());
        requestedComment.setContent(dto.getContent());
        requestedComment.setPost(post);
        Comment savedComment = commentRepository.save(requestedComment);

        CommentResponse dtoResponse = new CommentResponse(savedComment.getId()
                , savedComment.getReaderName(), savedComment.getContent());
        return dtoResponse;
    }

    public CommentResponse updateComment(Long commentId, UpdateCommentRequest dto){
        Comment requestedComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException ("comment not found"));
        requestedComment.setContent(dto.getContent());
        commentRepository.save(requestedComment);

        CommentResponse dtoResponse = new CommentResponse(requestedComment.getId()
                , requestedComment.getReaderName(), requestedComment.getContent());

        return dtoResponse;
    }

    public void deleteComment(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));
        commentRepository.delete(comment);
    }


}
