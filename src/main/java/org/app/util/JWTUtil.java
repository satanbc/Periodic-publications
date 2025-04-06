package org.app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class JWTUtil {

    private static final String SECRET = "your-secret-key";
    private static final String ISSUER = "your-issuer"; // Keycloak або Auth0

    public static boolean isValid(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET); // або використовуй RS256 якщо публічний ключ
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            DecodedJWT jwt = verifier.verify(token);

            return jwt.getExpiresAt().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUsername(String token) {
        return JWT.decode(token).getSubject(); // або .getClaim("preferred_username")
    }

    public static String getRole(String token) {
        return JWT.decode(token).getClaim("role").asString(); // залежить від payload у токені
    }
}
