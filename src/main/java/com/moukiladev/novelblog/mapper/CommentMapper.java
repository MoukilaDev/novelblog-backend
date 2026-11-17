package com.moukiladev.novelblog.mapper;

import com.moukiladev.novelblog.dto.comment.CommentResponse;
import com.moukiladev.novelblog.dto.comment.CreateCommentRequest;
import com.moukiladev.novelblog.dto.comment.UpdateCommentRequest;
import com.moukiladev.novelblog.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CommentMapper {
        @Mapping(target = "author", source = "author.username")
        public CommentResponse toCommentResponse(Comment comment);
        @Mapping(target = "author", ignore = true)
        @Mapping(target = "post", ignore = true)
        public Comment toCommentEntity(CreateCommentRequest dto);
        public void updateCommentFromDto(UpdateCommentRequest dto, @MappingTarget Comment comment);
}