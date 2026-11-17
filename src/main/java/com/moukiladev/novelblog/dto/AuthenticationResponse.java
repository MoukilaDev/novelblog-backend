package com.moukiladev.novelblog.dto;

public class AuthenticationResponse {
    private String token;
    //Constructors
    public AuthenticationResponse() {}
    public AuthenticationResponse(String token) {
        this.token = token;
    }
    //Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
