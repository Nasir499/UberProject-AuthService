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

    /**
     * Constructs a new AuthController with the given AuthService.
     *
     * @param authService the auth service
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registers a new passenger.
     *
     * @param passengerSignupRequestdto the passenger signup request DTO
     * @return the ResponseEntity containing the registered passenger DTO
     */
    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestdto) {
        PassengerDto response  = authService.signupPassenger(passengerSignupRequestdto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    /**
     * Signs in a passenger.
     *
     * @return the ResponseEntity containing the result
     */
    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signUp() {
        return new ResponseEntity<>(1,HttpStatus.FOUND);
    }

}
