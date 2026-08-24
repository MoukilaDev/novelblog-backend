package com.moukiladev.novelblog.mapper;

import com.moukiladev.novelblog.dto.CreatePostRequest;
import com.moukiladev.novelblog.dto.PostResponse;
import com.moukiladev.novelblog.dto.UpdatePostRequest;
import com.moukiladev.novelblog.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    public PostResponse toPostResponse(Post post);
    public Post toPostEntity(CreatePostRequest dto);
    //Take this existing Post and modify its properties using the data from the DTO.
    public void updatePostFromDto(UpdatePostRequest dto, @MappingTarget Post post);
}
