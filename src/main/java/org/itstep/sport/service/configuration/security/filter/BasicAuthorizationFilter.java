package org.itstep.sport.service.configuration.security.filter;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.itstep.sport.service.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Slf4j
public class BasicAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtService jwtService;

    public BasicAuthorizationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        super(authenticationManager);
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authToken = request.getHeader("Authorization");

        if (authToken == null || authToken.equals("")) {
            chain.doFilter(request, response);
            return;
        }
        boolean isValid = jwtService.validateToken(authToken);
        if (!isValid) {
            chain.doFilter(request, response);
            return;
        }
        Claims userClaims = jwtService.getClaimsFromToken(authToken);

        String username = userClaims.get("username", String.class);
        String authority = userClaims.get("authority", String.class);

        if (username == null || authority == null) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                username,
                null,
                Collections.singletonList(new SimpleGrantedAuthority(authority))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
