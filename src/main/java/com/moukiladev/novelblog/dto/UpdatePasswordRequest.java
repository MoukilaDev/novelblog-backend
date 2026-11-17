package com.moukiladev.novelblog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdatePasswordRequest {
    @NotBlank(message = "old password is required")
    @Size(min = 8, max = 80,message = "The pastword must have at least 8 characters long")
    private String currentPassword;

    @NotBlank(message = "new password is required")
    @Size(min = 8, max = 72)
    private String newPassword;
    // Constructors
    public UpdatePasswordRequest(){}
    public UpdatePasswordRequest(String oldPassword, String newPassword){
        this.currentPassword = oldPassword;
        this.newPassword = newPassword;
    }
    // Getters and Setters
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
