package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.dto.user.CreateUserRequest;
import com.moukiladev.novelblog.dto.UpdatePasswordRequest;
import com.moukiladev.novelblog.dto.user.UpdateUserRequest;
import com.moukiladev.novelblog.dto.user.UserResponse;
import com.moukiladev.novelblog.service.UserEntityService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserEntityService userEntityService;
    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping
    public List<UserResponse> getUsers(){ return userEntityService.getUsers(); }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable long id){ return userEntityService.getUserById(id); }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest dto){
        return userEntityService.createUser(dto);
    }

    @PutMapping("/me")
    public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest dto,
                                   @AuthenticationPrincipal UserDetails currentUser){
        return userEntityService.updateUser(dto, currentUser);
    }
    @PutMapping("/me/password")
    public UserResponse updatePassword(@Valid @RequestBody UpdatePasswordRequest dto,
                                       @AuthenticationPrincipal UserDetails currentUser){
        return userEntityService.updatePassword(dto, currentUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id){
        userEntityService.deleteUser(id);
    }
}
