package com.challenge.techbase.util;

import com.challenge.techbase.models.entity.User;
import com.challenge.techbase.models.security.UserDetail;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${security.config.jwtSecrect}")
    private String jwtSerect;

    @Value("${security.config.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(User user) {
        Claims claims = Jwts.claims()
                .setSubject(user.getEmail());
        claims.put("roles", user.getRoles()
                .stream()
                .map(i -> String.format("ROLE_%s", i.getName().toUpperCase()))
                .collect(Collectors.toSet()));

        Date current = new Date();
        Date expireAt = new Date(current.getTime() + jwtExpirationMs);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(current)
                .setExpiration(expireAt)
                .signWith(SignatureAlgorithm.HS512, jwtSerect)
                .compact();

        return token;
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSerect).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty : " + e.getMessage());
        }

        return false;
    }

    public Claims getClaimsFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSerect)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmailFromJwtToken(String token) {
        return this.getClaimsFromJwtToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        List<?> roles = this.getClaimsFromJwtToken(token).get("roles", List.class);
        return roles.stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        UserDetails userDetails = this.getUserDetails(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private UserDetails getUserDetails(String token) {
        String email = this.getEmailFromJwtToken(token);
        List<String> roles = this.getRoles(token);
        String[] roleArr = new String[roles.size()];
        roles.toArray(roleArr);

        return new UserDetail(email, roleArr);
    }

}
