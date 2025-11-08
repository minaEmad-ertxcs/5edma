package com.mina.khedma.service;

import com.mina.khedma.model.User;
import com.mina.khedma.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JWTService jwtService;

    private final AuthenticationManager authManager;

    private final UserRepo repo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(JWTService jwtService, AuthenticationManager authManager, UserRepo repo) {
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.repo = repo;
    }

    public ResponseEntity<String> register(User requestUser) {
        User existUser = repo.findByUsername(requestUser.getUsername());

        if (existUser != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Username already exists");
        }
        requestUser.setPassword(encoder.encode(requestUser.getPassword()));
        User user = repo.save(requestUser);

        if (user.getId() != null)
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        else
            return ResponseEntity.badRequest().body("Failed to register user");
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }
}