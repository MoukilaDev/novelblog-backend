package com.moukiladev.novelblog.service;

import com.moukiladev.novelblog.SecurityConfig;
import com.moukiladev.novelblog.dto.CreateUserRequest;
import com.moukiladev.novelblog.dto.UpdateUserRequest;
import com.moukiladev.novelblog.dto.UserResponse;
import com.moukiladev.novelblog.entity.UserEntity;
import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import com.moukiladev.novelblog.mapper.UserMapper;
import com.moukiladev.novelblog.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserEntityService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SecurityConfig security;

    public UserEntityService(UserRepository userRepository, UserMapper userMapper, SecurityConfig security) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.security = security;
    }

    public List<UserResponse> getUsers(){
        List<UserEntity> savedUsers = userRepository.findAll();

        return savedUsers.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUserById(Long id){
        UserEntity savedUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toUserResponse(savedUser);
    }

    public UserResponse createUser(CreateUserRequest dto){
        UserEntity requestedUser = userMapper.toUserEntity(dto);
        requestedUser.setPassword(security.passwordEncoder().encode(requestedUser.getPassword()));
        UserEntity savedUser = userRepository.save(requestedUser);
        return userMapper.toUserResponse(savedUser);
    }

    public UserResponse updateUser(UpdateUserRequest dto, Long id){
        UserEntity dbUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userMapper.updateUserFromDto(dto, dbUser);
        dbUser.setPassword(security.passwordEncoder().encode(dbUser.getPassword()));
        UserEntity savedUser = userRepository.save(dbUser);
        return userMapper.toUserResponse(savedUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
