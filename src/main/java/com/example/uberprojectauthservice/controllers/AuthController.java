package com.example.uberprojectauthservice.controllers;

import com.example.uberprojectauthservice.dto.PassengerDto;
import com.example.uberprojectauthservice.dto.PassengerSignupRequestDto;
import com.example.uberprojectauthservice.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    PassengerSignupRequestDto passengerSignupRequestdto;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestdto) {
        PassengerDto response  = authService.signupPassenger(passengerSignupRequestdto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signUp() {
        return new ResponseEntity<>(1,HttpStatus.FOUND);
    }
}
