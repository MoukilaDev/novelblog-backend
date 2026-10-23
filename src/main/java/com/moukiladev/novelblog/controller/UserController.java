package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.dto.CreateUserRequest;
import com.moukiladev.novelblog.dto.UpdateUserRequest;
import com.moukiladev.novelblog.dto.UserResponse;
import com.moukiladev.novelblog.service.UserEntityService;
import jakarta.validation.Valid;
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

    @GetMapping("/id")
    public UserResponse getUserById(@PathVariable long id){ return userEntityService.getUserById(id); }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest dto){
        return userEntityService.createUser(dto);
    }

    @PutMapping("/id")
    public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest dto, @PathVariable long id){
        return userEntityService.updateUser(dto, id);
    }

    @DeleteMapping("/id")
    public void deleteUserById(@PathVariable long id){
        userEntityService.deleteUser(id);
    }
}
