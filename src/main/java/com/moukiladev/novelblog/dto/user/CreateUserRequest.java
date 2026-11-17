package com.moukiladev.novelblog.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {
    @NotBlank(message = "name is required")
    @Size(min = 1, max = 30)
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 8, max = 80,message = "The pastword must have at least 8 characters long")
    private String password;

    @Email
    @NotBlank(message = "email is required")
    private String email;

    public CreateUserRequest() {}
    public CreateUserRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
