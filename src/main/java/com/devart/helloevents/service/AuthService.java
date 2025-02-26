package com.devart.helloevents.service;

import com.devart.helloevents.jwt.JwtTokenProvider;
import com.devart.helloevents.dto.JwtAuthResponse;
import com.devart.helloevents.dto.LoginDto;
import com.devart.helloevents.dto.SignUpDto;
import com.devart.helloevents.model.Role;
import com.devart.helloevents.model.User;
import com.devart.helloevents.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUserNameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        User user = userRepository.findByUserNameOrEmail(loginDto.getUserNameOrEmail(), loginDto.getUserNameOrEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        JwtAuthResponse response = new JwtAuthResponse();
        response.setAccessToken(token);
        response.setTokenType("Bearer");
        response.setUserName(user.getUserName());
        response.setUserRole(user.getRole().name());
        response.setUserId(user.getId());

        return response;
    }

    public String register(SignUpDto signUpDto) {
        if (userRepository.existsByUserName(signUpDto.getUserName())) {
            throw new RuntimeException("Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new RuntimeException("Email is already taken!");
        }

        User user = new User();
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        return "User registered successfully!";
    }
}