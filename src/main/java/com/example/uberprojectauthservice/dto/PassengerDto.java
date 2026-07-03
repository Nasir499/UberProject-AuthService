package com.example.uberprojectauthservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private String id;

    private  String name;

    private  String email;

    private  String password;

    private  String phoneNumber;

    private Date createdAt;
}
