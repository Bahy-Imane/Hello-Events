package com.devart.helloevents.service;

import com.devart.helloevents.dto.LoginDto;
import com.devart.helloevents.dto.SignUpDto;
import com.devart.helloevents.jwt.JwtTokenProvider;
import com.devart.helloevents.model.User;
import com.devart.helloevents.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_Success() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUserNameOrEmail("testUser");
        loginDto.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(jwtTokenProvider.generateToken(authentication)).thenReturn("testToken");

        String result = authService.login(loginDto);

        assertEquals("testToken", result);
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider, times(1)).generateToken(authentication);
    }

    @Test
    void register_Success() {
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setUserName("newUser");
        signUpDto.setEmail("newuser@example.com");
        signUpDto.setPassword("password");

        when(userRepository.existsByUserName("newUser")).thenReturn(false);
        when(userRepository.existsByEmail("newuser@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        String result = authService.register(signUpDto);

        assertEquals("User registered successfully!", result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void register_UsernameTaken() {
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setUserName("existingUser");
        signUpDto.setEmail("newuser@example.com");
        signUpDto.setPassword("password");

        when(userRepository.existsByUserName("existingUser")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> authService.register(signUpDto));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void register_EmailTaken() {
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setUserName("newUser");
        signUpDto.setEmail("existing@example.com");
        signUpDto.setPassword("password");

        when(userRepository.existsByUserName("newUser")).thenReturn(false);
        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> authService.register(signUpDto));
        verify(userRepository, never()).save(any(User.class));
    }
}