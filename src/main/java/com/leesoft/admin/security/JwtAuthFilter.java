package com.leesoft.admin.security;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/api/*")
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtProvider tokenProvider;
    public static final String JWT_USER = "jwtUser";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(tokenProvider == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
            tokenProvider = webApplicationContext.getBean(JwtProvider.class);
        }

        String CORSHeader = request.getHeader("Access-Control-Request-Headers");
        if(CORSHeader == null){
            String jwt = request.getHeader("Authorization");
            if (tokenProvider.validateToken(jwt)) {
                JwtAccount jwtUser = tokenProvider.getJwtUser(jwt);
                request.setAttribute(JWT_USER, jwtUser);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
