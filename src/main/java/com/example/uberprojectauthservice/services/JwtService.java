package com.example.uberprojectauthservice.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService implements CommandLineRunner {

    @Value( "${jwt.secret}")
    private  String SECRET;

    @Value( "${jwt.expiry}")
    private int expiry;


    private String createToken(Map<String, Object> payload,String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000L);
        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate).subject(email)
                .signWith(getSigningKey())
                .compact();
    }
    private Claims extractAllPayload(String token){
        return  Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private <T> T extractPayload(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllPayload(token);
        return claimsResolver.apply(claims);
    }

    private String extractEmail(String token) {
        return extractPayload(token,Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractPayload(token,Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    private Boolean validateToken(String token, String email) {
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }

    private String extractPhoneNumber(String token) {
        return extractPayload(token,claims -> claims.get("phoneNumber").toString());
    }

    @Override
    public void run(String... args) throws Exception {
        String token = createToken(Map.of("email","Nasir@b","phoneNumber","99999999"),"Nasir");
        System.out.println("Generated token is :"+token);
        System.out.println("extracted email is :"+extractEmail(token));
        System.out.println("extracted phoneNumber is :"+extractPhoneNumber(token));
        System.out.println("is token expired :"+isTokenExpired(token));
        System.out.println("is token valid :"+validateToken(token,"Nasir"));
    }
}
