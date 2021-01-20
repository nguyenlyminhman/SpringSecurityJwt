package com.leesoft.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leesoft.admin.models.AccountInfoDetail;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    public static JwtAccount retrieveUserFromClaims(Claims claims, ObjectMapper objectMapper) {
        JwtAccount jwtUser = new JwtAccount(
                Integer.valueOf(claims.getId()),
                claims.getSubject()
        );
        try {
            jwtUser.setAccountInfoDetail(objectMapper.readValue(getStringValue(claims, "PROFILES"), AccountInfoDetail.class));
        } catch (IOException e) {
            LOGGER.error(String.format("Error occurred while trying to retrieve UserProfile and Selector of user [%s] from JwtClaims", jwtUser.getAccountid()), e);
        }
        return jwtUser;
    }

    private static String getStringValue(Claims claims, String keyName) {
        Object value = claims.get(keyName);
        return value == null ? null : String.valueOf(value);
    }
}
