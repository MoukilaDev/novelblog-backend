package com.moukiladev.novelblog.dto;

import com.moukiladev.novelblog.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {
    @NotBlank(message = "name is required")
    @Size(min = 1, max = 30)
    private String username;

    @Email
    @NotBlank(message = "email is required")
    private String email;

    public UpdateUserRequest() {}
    public UpdateUserRequest(String username, String email) {
        this.username = username;
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
}
