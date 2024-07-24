package com.devart.helloevents.service;
import com.devart.helloevents.dto.LoginDto;
import com.devart.helloevents.dto.SignUpDto;


public interface AuthService {
    String login(LoginDto loginDto);
    String register(SignUpDto signUpDto);
}