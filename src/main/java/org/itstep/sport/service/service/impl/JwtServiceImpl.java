package org.itstep.sport.service.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.itstep.sport.service.model.User;
import org.itstep.sport.service.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${auth.secret}")
    private String secret;
    @Value("${auth.expiration}")
    private String expiration;

    @Override
    public String createToken(User user) {
        HashMap<String, Object> claimsMap = new HashMap<>();

        claimsMap.put("username", user.getUsername());
        claimsMap.put("authority", user.getAuthority().name());

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setClaims(claimsMap)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    @Override
    public Claims getClaimsFromToken(String token) {
        String secret = Base64.getEncoder().encodeToString(this.secret.getBytes());

        return Jwts
                .parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean validateToken(String token) {
        return this.getClaimsFromToken(token)
                .getExpiration()
                .after(new Date());
    }
}
