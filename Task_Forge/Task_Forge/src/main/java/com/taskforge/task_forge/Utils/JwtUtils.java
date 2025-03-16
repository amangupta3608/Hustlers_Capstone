package com.taskforge.task_forge.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {
    private final String jwtSecretKey = "YourSecretKey";
    private final long jwtExpirationTime = 86400000;

    public String generateToken(UUID userId, String email){
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("email", email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .compact();
    }
    
    public String extractUserId(String token){
        return getClaims(token).getSubject();
    }
    
    public String extractEmail(String token){
        return getClaims(token).get("email", String.class);
    }
    
    public boolean validateToken(String token){
        try {
            getClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    
    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(org.hibernate.validator.constraints.UUID id, String email) {
        return null;
    }
}
