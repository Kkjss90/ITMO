package com.example.util;

import com.example.DB.User;
import com.example.REST.filters.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.TextCodec;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class JWTutil {
    private final static String USER_ID_CLAIM = "user_id";
    private final static String USERNAME_CLAIM = "username";
    private final static String SECRET_KEY = "wSw3W3NU2c6cjogv6OaqvEtfJg8aF5D";
    private final static long EXPIRATION_TIME = TimeUnit.HOURS.toMillis(2);

    public static String tokenForUser(User user) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expiration)
                .claim(USER_ID_CLAIM, user.getId().toString())
                .claim(USERNAME_CLAIM, user.getUsername())
                .signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.decode(SECRET_KEY))
                .compact();
    }

    public static Optional<UserPrincipal> verify(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.decode(SECRET_KEY))
                    .parseClaimsJws(token)
                    .getBody();

            Date now = new Date();
            Date expiration = claims.getExpiration();
            if (now.compareTo(expiration) > 0) {
                return Optional.empty();
            }

            String username = (String) claims.get(USERNAME_CLAIM);
            String userIDString = (String) claims.get(USER_ID_CLAIM);
            Long userID = Long.valueOf(userIDString);

            UserPrincipal userPrincipal = new UserPrincipal(userID, username);

            return Optional.of(userPrincipal);
        } catch (SignatureException ignored) {
            return Optional.empty();
        }
    }
}
