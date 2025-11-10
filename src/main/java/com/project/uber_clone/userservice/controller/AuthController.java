package com.project.uber_clone.userservice.controller;

import com.project.uber_clone.userservice.dto.AuthResponse;
import com.project.uber_clone.userservice.dto.LoginRequest;
import com.project.uber_clone.userservice.model.User;
import com.project.uber_clone.userservice.repository.UserRepository;
import com.project.uber_clone.userservice.service.JwtService;
import com.project.uber_clone.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    public UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public User RegisterUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){
       User user = userRepository.findByEmail(loginRequest.getEmail())
               .orElseThrow(()->new RuntimeException("User not found"));

       if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
          throw new RuntimeException("Invalid password");
       }

      String token =  jwtService.generateToken(user.getEmail());
      return new AuthResponse(token);
    }



}
