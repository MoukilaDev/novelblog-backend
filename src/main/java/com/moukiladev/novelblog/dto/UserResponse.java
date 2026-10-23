package com.moukiladev.novelblog.dto;
import com.moukiladev.novelblog.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserResponse {
    private Long id;

    private String username;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public UserResponse() {}
    public UserResponse(Long id, String username, String email, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
