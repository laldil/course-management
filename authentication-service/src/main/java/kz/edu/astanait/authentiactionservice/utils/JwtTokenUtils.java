package kz.edu.astanait.authentiactionservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kz.edu.astanait.authentiactionservice.model.RoleEntity;
import kz.edu.astanait.authentiactionservice.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author aldi
 * @since 20.03.2024
 */
@Slf4j
@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;

    public String createToken(UserEntity user) {
        return createToken(user, jwtLifetime);
    }

    public String createToken(UserEntity user, Duration lifeTime) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toList()));
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());

        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + lifeTime.toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getName())
                .setIssuedAt(issuedDate)
                .setExpiration(expirationDate)
                .signWith(getKey())
                .compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Token {} is not valid", token);
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return getAllClaims(token).get("email", String.class);
    }

    public List<String> getRole(String token) {
        return getAllClaims(token).get("role", List.class);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
