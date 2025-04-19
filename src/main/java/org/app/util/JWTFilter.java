package org.app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class JWTFilter implements Filter {

    private static final String ISSUER = "https://YOUR_DOMAIN/";
    private static final String AUDIENCE = "http://localhost:8080";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                DecodedJWT jwt = JWT.require(Algorithm.RSA256(publicKey, null))
                        .withAudience(AUDIENCE)
                        .withIssuer(ISSUER)
                        .build()
                        .verify(token);

                String rolesClaim = jwt.getClaim("http://schemas.yourapp.com/roles").asString();
                req.setAttribute("userRoles", rolesClaim);
                chain.doFilter(request, response);
                return;

            } catch (Exception e) {
                e.printStackTrace();
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        }

        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
    }
}
