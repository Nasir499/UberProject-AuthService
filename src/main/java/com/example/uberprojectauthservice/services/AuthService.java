package com.example.uberprojectauthservice.services;

import com.example.uberprojectauthservice.dto.PassengerDto;
import com.example.uberprojectauthservice.dto.PassengerSignupRequestDto;
import com.example.uberprojectauthservice.model.Passenger;
import com.example.uberprojectauthservice.repository.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PassengerRepository PassengerRepository;
    /**
     * Constructs a new AuthService with the given PassengerRepository and BCryptPasswordEncoder.
     *
     * @param passengerRepository the passenger repository
     * @param bCryptPasswordEncoder the bcrypt password encoder
     */
    public AuthService( PassengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        PassengerRepository = passengerRepository;
    }
    /**
     * Signs up a new passenger.
     *
     * @param passengerSignupRequestDto the passenger signup request DTO
     * @return the registered passenger DTO
     */
    public PassengerDto signupPassenger(PassengerSignupRequestDto passengerSignupRequestDto) {
        Passenger passenger = Passenger.builder()
                .email(passengerSignupRequestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(passengerSignupRequestDto.getPassword()))
                .name(passengerSignupRequestDto.getName())
                .phoneNumber(passengerSignupRequestDto.getPhoneNumber())
                .build();
        Passenger newPassenger = PassengerRepository.save(passenger);
        return PassengerDto.from(newPassenger);
    }
}
