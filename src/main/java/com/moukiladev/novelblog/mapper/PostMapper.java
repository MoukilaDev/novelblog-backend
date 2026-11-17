package com.moukiladev.novelblog.mapper;

import com.moukiladev.novelblog.dto.post.CreatePostRequest;
import com.moukiladev.novelblog.dto.post.PostResponse;
import com.moukiladev.novelblog.dto.post.UpdatePostRequest;
import com.moukiladev.novelblog.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target ="author", source = "author.username")
    public PostResponse toPostResponse(Post post);
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "author", ignore = true)
    public Post toPostEntity(CreatePostRequest dto);
    //Take this existing Post and modify its properties using the data from the DTO.
    public void updatePostFromDto(UpdatePostRequest dto, @MappingTarget Post post);
}
