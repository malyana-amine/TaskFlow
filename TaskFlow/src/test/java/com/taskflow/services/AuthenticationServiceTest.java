package com.taskflow.services;

import com.taskflow.config.JwtService;
import com.taskflow.dto.request.AuthenticationRequest;
import com.taskflow.dto.request.RegisterRequest;
import com.taskflow.dto.response.AuthenticationResponse;
import com.taskflow.entities.Role;
import com.taskflow.entities.Users;
import com.taskflow.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void register() {
        // Given
        RegisterRequest registerRequest = new RegisterRequest("John Doe", "john.doe@example.com", "password123");
        Users mockedUser = Users.builder()
                .name(registerRequest.getName())
                .coins(20)
                .email(registerRequest.getEmail())
                .password("encodedPassword")
                .role(Role.USER)
                .build();

        when(userRepository.save(any(Users.class))).thenReturn(mockedUser);
        when(jwtService.generateToken(any(Users.class))).thenReturn("mockedToken");

        // When
        AuthenticationResponse response = authenticationService.register(registerRequest);

        // Then
        assertEquals("mockedToken", response.getToken());
    }

    @Test
    void authenticate() {
        // Given
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("john.doe@example.com", "password123");
        Users mockedUser = Users.builder()
                .name("John Doe")
                .coins(20)
                .email("john.doe@example.com")
                .password("encodedPassword")
                .role(Role.USER)
                .build();

        when(userRepository.findByEmailNativeQuery(authenticationRequest.getEmail())).thenReturn(Optional.of(mockedUser));
        when(jwtService.generateToken(any(Users.class))).thenReturn("mockedToken");

        // When
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        // Then
        assertEquals("mockedToken", response.getToken());
    }
}
