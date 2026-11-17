package com.moukiladev.novelblog.controller;

import com.moukiladev.novelblog.dto.AuthenticationRequest;
import com.moukiladev.novelblog.dto.AuthenticationResponse;
import com.moukiladev.novelblog.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthenticationResponse authenticate(
            @Valid @RequestBody AuthenticationRequest request){
        return authService.authenticate(request);
    }
}
