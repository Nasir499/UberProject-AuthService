package com.example.uberprojectauthservice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService implements CommandLineRunner {

    @Value( "${jwt.secret}")
    private  String SECRET;

    @Value( "${jwt.expiry}")
    private int expiry;

    /**
     * Create a brand new jwt token for the user based on the payload
     * @param payload
     * @param username
     * @return
     */

    private String createToken(Map<String, Object> payload,String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000L);
        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate).subject(username)
                .signWith(key)
                .compact();
    }

    @Override
    public void run(String... args) throws Exception {
        String token = createToken(Map.of("email","Nasir@b","phoneNumber","99999999"),"Nasir");
        System.out.println("Generated token is :"+token);
    }
}
