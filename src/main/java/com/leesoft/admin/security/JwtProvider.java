package com.leesoft.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

@Service
public class JwtProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    private static final String jwtSecret = new BigInteger(130, new SecureRandom()).toString(32);

    @Value("24")
    private int jwtExpireHrs;

    @Autowired
    private ObjectMapper objectMapper;

    public boolean validatePassword(String originalPassword, String dbPassword){
        try{
            return originalPassword.equals(dbPassword);
            // return BCrypt.checkpw(originalPassword, dbPassword);
        }
        catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    public String generateToken(JwtAccount jwtUser) {
        Date expireDate = new DateTime().plusHours(jwtExpireHrs).toDate();

        return Jwts.builder()
                // HEADERS
                .setId(Long.toString(jwtUser.getId()))
                .setSubject(jwtUser.getAccountInfoDetail().getFullname())
                // PAYLOADS
                .claim("PROFILES", this.serializeToString(jwtUser.getAccountInfoDetail(), jwtUser.getAccountInfoDetail().getFullname()))
                // SIGNATURE
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                // BUILD TOKEN
                .compact();
    }

    private String serializeToString(Object target, String fullname) {
        String jsonData = null;
        try{
            if(target != null){
                jsonData = this.objectMapper.writeValueAsString(target);
            }
        }catch (Exception e){
            LOGGER.error(String.format("Can not write %s of %s to JSON string",target.getClass(), fullname), e);
        }
        return jsonData;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOGGER.warn("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            LOGGER.error("JWT claims string is empty.");
        }
        return false;
    }

    public JwtAccount getJwtUser(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return JwtUtils.retrieveUserFromClaims(claims, objectMapper);
    }
}
