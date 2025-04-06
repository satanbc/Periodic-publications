package org.app.filter;

import org.app.util.JWTUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*") // Фільтрує всі запити
public class JWTAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        // Дозволити публічні ендпоінти
        if (path.startsWith("/login") || path.startsWith("/public") || "OPTIONS".equals(req.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        // Читання заголовку Authorization
        String authHeader = req.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (JWTUtil.isValid(token)) {
                chain.doFilter(request, response);
                return;
            }
        }

        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.getWriter().write("Unauthorized");
    }
}
