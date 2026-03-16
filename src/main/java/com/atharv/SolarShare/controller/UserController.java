package com.atharv.SolarShare.controller;

import com.atharv.SolarShare.dto.SignupRequest;
import com.atharv.SolarShare.model.User;
import com.atharv.SolarShare.service.JwtService;
import com.atharv.SolarShare.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "*")
public class UserController {

    @Autowired
    private UserRegistrationService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody SignupRequest signupRequest) {
        service.registerNewUser(signupRequest);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }else{
            return "Fail";
        }
    }
}
