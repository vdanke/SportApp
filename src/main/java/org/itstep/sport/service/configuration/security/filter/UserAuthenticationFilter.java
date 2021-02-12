package org.itstep.sport.service.configuration.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.itstep.sport.service.dto.request.UserLoginRequest;
import org.itstep.sport.service.model.User;
import org.itstep.sport.service.model.UserDetailsImpl;
import org.itstep.sport.service.service.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ServletInputStream inputStream = request.getInputStream();

            UserLoginRequest loginRequest = objectMapper.readValue(inputStream, UserLoginRequest.class);

            if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
                return null;
            }
            return new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword()
            );
        } catch (IOException e) {
            log.error(String.format(
                    "Error %s of type %s", e.getLocalizedMessage(), e.getClass().getSimpleName())
            );
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String principal = (String) authResult.getPrincipal();

        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(principal);

        String token = jwtService.createToken(userDetails.getUser());

        response.setStatus(HttpStatus.OK.value());
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
        response.setHeader(HttpHeaders.AUTHORIZATION, token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(
                "Authentication failure, password or username is not correct"
        );
    }
}
