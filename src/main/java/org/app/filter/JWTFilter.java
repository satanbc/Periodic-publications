//package org.app.filter;
//
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebFilter("/admin/*") // ⬅️ Фільтр тільки для /admin/*
//public class JWTFilter implements Filter {
//
//    private static final String ISSUER = "https://dev-kqpgdhqmlnnp5fjc.us.auth0.com/";
//    private static final String AUDIENCE = "http://localhost:3000";
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        String authHeader = req.getHeader("Authorization");
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//
//            try {
//                DecodedJWT jwt = JwtVerifierUtil.verifyToken(token);
//
//                List<String> roles = jwt.getClaim("http://schemas.periodicals.com/roles").asList(String.class);
//                if (roles != null && roles.contains("admin")) {
//                    req.setAttribute("userRoles", roles);
//                    chain.doFilter(request, response); // ⬅️ доступ дозволено
//                    return;
//                } else {
//                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
//                    return;
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
//                return;
//            }
//        }
//
//        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
//    }
//}
