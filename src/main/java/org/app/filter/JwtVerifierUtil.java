package org.app.filter;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.interfaces.RSAPublicKey;

public class JwtVerifierUtil {
    private static final String ISSUER = "https://dev-kqpgdhqmlnnp5fjc.us.auth0.com/";
    private static final String JWKS_URL = ISSUER + ".well-known/jwks.json";

    public static DecodedJWT verifyToken(String token) throws Exception {
        JwkProvider provider = new UrlJwkProvider(new java.net.URL(JWKS_URL));
        DecodedJWT jwt = JWT.decode(token);
        Jwk jwk = provider.get(jwt.getKeyId());
        RSAPublicKey publicKey = (RSAPublicKey) jwk.getPublicKey();

        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }
}
