package com.mina.khedma.service;

import com.mina.khedma.DAO.UserDAO;
import com.mina.khedma.model.UserRequest;
import com.mina.khedma.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

    public ResponseEntity<String> register(UserRequest userRequest) {
        UserDAO existUserDAO = repo.findByUsername(userRequest.getUsername());

        if (existUserDAO != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        UserDAO userDAO = new UserDAO();
        userDAO.setUsername(userRequest.getUsername());
        userDAO.setPassword(encoder.encode(userRequest.getPassword()));

        userDAO = repo.save(userDAO);

        if (userDAO.getId() != null)
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        else return ResponseEntity.badRequest().body("Failed to register user");
    }

    public String login(UserRequest userRequest) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userRequest.getUsername());
        } else {
            throw new BadCredentialsException("Invalid username and password");
        }
    }
}