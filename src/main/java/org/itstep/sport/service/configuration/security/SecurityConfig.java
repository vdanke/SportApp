package org.itstep.sport.service.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.itstep.sport.service.configuration.security.filter.BasicAuthorizationFilter;
import org.itstep.sport.service.configuration.security.filter.UserAuthenticationFilter;
import org.itstep.sport.service.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/trainees").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/coaches").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/categories").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/posts/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout()
                .and()
                .cors()
                .and()
                .addFilter(corsFilter())
                .addFilter(authFilter(objectMapper, jwtService))
                .addFilter(authorizationFilter(jwtService));
    }

    @Bean
    public Filter authFilter(ObjectMapper objectMapper, JwtService jwtService) throws Exception {
        UserAuthenticationFilter filter = new UserAuthenticationFilter(objectMapper, jwtService, userDetailsService);

        filter.setAuthenticationManager(super.authenticationManager());
        filter.setFilterProcessesUrl("/api/v1/auth/login");

        return filter;
    }

    @Bean
    public Filter authorizationFilter(JwtService jwtService) throws Exception {
        return new BasicAuthorizationFilter(
                super.authenticationManager(), jwtService
        );
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }
}
