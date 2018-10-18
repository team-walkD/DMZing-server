package com.walkd.dmzing.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.walkd.dmzing.auth.jwt.JwtInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

@Slf4j
public class JwtUtil {

    public static String createToken(UserDetails userDetails) {
        return createToken(userDetails, DateUtil.nowAfterDaysToDate(JwtInfo.EXPIRES_LIMIT));
    }

    private static String createToken(UserDetails userDetails, Date date) {
        try {
            log.info(userDetails.getUsername()+"@@@@@@@@@@@@@@@@@@@@@@@@@");
            return JWT.create()
                    .withIssuer(JwtInfo.ISSUER)
                    .withClaim("email", userDetails.getUsername())
                    .withClaim("role", userDetails.getAuthorities().toArray()[0].toString())
                    .withExpiresAt(date)
                    .sign(JwtInfo.getAlgorithm());
        } catch (JWTCreationException createEx) {
            return null;
        }
    }

    public static Boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(JwtInfo.getAlgorithm()).build();
            verifier.verify(token);

            return Boolean.TRUE;
        } catch (JWTVerificationException verifyEx) {
            return Boolean.FALSE;
        }
    }

    public static String refreshToken(UserDetails userDetails) {
        return createToken(userDetails, DateUtil.nowAfterDaysToDate(JwtInfo.EXPIRES_LIMIT));
    }

    public static DecodedJWT tokenToJwt(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException decodeEx) {
            return null;
        }
    }
}
