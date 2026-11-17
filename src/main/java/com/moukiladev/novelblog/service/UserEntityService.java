package com.moukiladev.novelblog.service;

import com.moukiladev.novelblog.dto.user.CreateUserRequest;
import com.moukiladev.novelblog.dto.UpdatePasswordRequest;
import com.moukiladev.novelblog.dto.user.UpdateUserRequest;
import com.moukiladev.novelblog.dto.user.UserResponse;
import com.moukiladev.novelblog.entity.Role;
import com.moukiladev.novelblog.entity.UserEntity;
import com.moukiladev.novelblog.exception.ResourceNotFoundException;
import com.moukiladev.novelblog.mapper.UserMapper;
import com.moukiladev.novelblog.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserEntityService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserEntityService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
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
        requestedUser.setPassword(passwordEncoder.encode(requestedUser.getPassword()));
        requestedUser.setRole(Role.USER);
        UserEntity savedUser = userRepository.save(requestedUser);
        return userMapper.toUserResponse(savedUser);
    }

    public UserResponse updateUser(UpdateUserRequest dto, UserDetails currentUser){
        UserEntity dbUser = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userMapper.updateUserFromDto(dto, dbUser);
        UserEntity savedUser = userRepository.save(dbUser);
        return userMapper.toUserResponse(savedUser);
    }

    public void deleteUser(Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException ("Post not found"));
        userRepository.delete(user);
    }

    public UserResponse updatePassword(UpdatePasswordRequest dto, UserDetails currentUser) {
        UserEntity dbUser = userRepository.findByUsername(currentUser.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(!passwordEncoder.matches(dto.getCurrentPassword(), dbUser.getPassword()))
            throw new BadCredentialsException("Incorrect password");
        dbUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        UserEntity savedUser = userRepository.save(dbUser);

        return userMapper.toUserResponse(savedUser);
    }
}
