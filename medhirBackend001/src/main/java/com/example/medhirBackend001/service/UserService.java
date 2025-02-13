package com.example.medhirBackend001.service;

import com.example.medhirBackend001.dto.AuthRequest;
import com.example.medhirBackend001.dto.RegisterRequest;
import com.example.medhirBackend001.exception.DuplicateResourceException;
import com.example.medhirBackend001.model.User;
import com.example.medhirBackend001.repository.UserRepository;
import com.example.medhirBackend001.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException("User with this email already exists.");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles((List<String>) request.getRoles())
                .build();

        userRepository.save(user);
        return "User registered successfully!";
    }

    public Map<String,Object> login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new RuntimeException("Invalid Credentials"));
        // Convert Set<String> to List<String>
        List<String> rolesList = user.getRoles().stream().collect(Collectors.toList());
        String token = jwtUtil.generateToken(user.getEmail(), user.getRoles());

        Map<String,Object> response = new HashMap<>();
        response.put("message","Login successful!");
        response.put("token",token);
        return response;

    }
}
