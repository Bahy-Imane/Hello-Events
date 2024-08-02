package com.devart.helloevents.controller;

import com.devart.helloevents.dto.JwtAuthResponse;
import com.devart.helloevents.dto.LoginDto;
import com.devart.helloevents.dto.SignUpDto;
import com.devart.helloevents.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        JwtAuthResponse response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto) {
        String response = authService.register(signUpDto);
        return ResponseEntity.ok(response);
    }
}