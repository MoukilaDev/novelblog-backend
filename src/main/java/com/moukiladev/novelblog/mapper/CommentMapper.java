package com.moukiladev.novelblog.mapper;

import com.moukiladev.novelblog.dto.CommentResponse;
import com.moukiladev.novelblog.dto.CreateCommentRequest;
import com.moukiladev.novelblog.model.Comment;

public class CommentMapper {
    public CommentResponse toCommentResponse(Comment comment) {
        CommentResponse dtoResponse = new CommentResponse();
        dtoResponse.setId(comment.getId());
        dtoResponse.setReaderName(comment.getReaderName());
        dtoResponse.setContent(comment.getContent());

        return dtoResponse;
    }

    public Comment toCommentEntity(CreateCommentRequest dto) {
        Comment requestedComment = new Comment();
        requestedComment.setReaderName(dto.getReaderName());
        requestedComment.setContent(dto.getContent());

        return requestedComment;
    }
}