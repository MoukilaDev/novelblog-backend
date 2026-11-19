package com.moukiladev.novelblog;

import com.moukiladev.novelblog.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtService jwtService,
                                           UserDetailsService userDetailsService) throws Exception{
        JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtService, userDetailsService);
        http
                .csrf(csrf -> csrf.disable()) // disactivate CSRF
                .cors(cors -> {}) // activate CORS (Cross Origin Requests)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers( headers -> headers.frameOptions((frame -> frame.disable())))// autorisation for H2 database call
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/h2-console/**").authenticated()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/categories").permitAll()
                        .requestMatchers("/api/categories/**").hasRole("ADMIN")
                        .requestMatchers("/api/users/me", "/api/users/me/password").authenticated()
                        .requestMatchers("/api/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/posts/**").permitAll()
                        .requestMatchers("/api/posts/comment").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/posts/{commentId}").hasRole("ADMIN")
                        .requestMatchers("/api/posts/{id}").hasRole("ADMIN")
                        .requestMatchers("/api/posts/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                    .authenticationEntryPoint((request, response, authException) -> {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.getWriter().write(
                                "{\"message\":\"Authentication required\"}"
                        );
                    })
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        response.getWriter().write(
                                "{\"message\":\"Access denied\"}"
                        );
                    })

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return (http.build());
    }
}