package com.alta.miniprojectcheckpoint.security;

import com.alta.miniprojectcheckpoint.model.Users;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
public class JwtProvider {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private Long expiration =1000L * 60 * 60; //ms * s * m = 1 jam;

    public String generateToken(Authentication authentication){
        final Users users = (Users) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());
        Date expireDate = new Date(now.getTime()+ expiration);

        Map<String, Object> claims =new HashMap<>();
        claims.put("username", users.getUsername());

        return Jwts.builder()
                .setId(users.getId_user().toString())
                .setSubject(users.getUsername())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            log.error("Invalid Jwt signature : {}", ex.getMessage());
        }catch (MalformedJwtException ex){
            log.error("Invalid Jwt Token : {}", ex.getMessage());
        }catch (ExpiredJwtException ex){
            log.error("Expired Jwt Token : {}", ex.getMessage());
        }catch (UnsupportedJwtException ex){
            log.error("Unsupported Jwt Token : {}", ex.getMessage());
        }catch (IllegalArgumentException ex){
            log.error("Jwt claim string empty: {}", ex.getMessage());
        }
        return false;
    }

    public String getUsername (String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }
}
