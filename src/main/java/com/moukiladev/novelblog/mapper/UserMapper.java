package com.moukiladev.novelblog.mapper;
import com.moukiladev.novelblog.dto.CreateUserRequest;
import com.moukiladev.novelblog.dto.UpdateUserRequest;
import com.moukiladev.novelblog.dto.UserResponse;
import com.moukiladev.novelblog.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserResponse toUserResponse(UserEntity userEntity);
    public UserEntity toUserEntity(CreateUserRequest dto);
    public void updateUserFromDto(UpdateUserRequest dto, @MappingTarget UserEntity userEntity);
}
