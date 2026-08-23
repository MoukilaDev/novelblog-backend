package com.moukiladev.novelblog.mapper;

import com.moukiladev.novelblog.dto.CommentResponse;
import com.moukiladev.novelblog.dto.CreateCommentRequest;
import com.moukiladev.novelblog.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
        CommentResponse toCommentResponse(Comment comment);

        Comment toCommentEntity(CreateCommentRequest dto);
}