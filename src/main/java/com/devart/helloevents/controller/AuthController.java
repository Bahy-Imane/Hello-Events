package com.devart.helloevents.controller;

import com.devart.helloevents.dto.LoginDto;
import com.devart.helloevents.dto.SignUpDto;
import com.devart.helloevents.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto) {
        String response = authService.register(signUpDto);
        return ResponseEntity.ok(response);
    }
}
