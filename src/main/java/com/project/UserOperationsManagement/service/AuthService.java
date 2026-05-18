package com.project.UserOperationsManagement.service;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.UserOperationsManagement.DTO.AuthResponce;
import com.project.UserOperationsManagement.DTO.LoginRequest;
import com.project.UserOperationsManagement.Entity.User;
import com.project.UserOperationsManagement.Repository.UserRepository;
import com.project.UserOperationsManagement.Security.JwtUtil;

@Service
public class AuthService {

	@Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public AuthResponce login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponce(token);
    }
	
	
	
}




