package com.school.vaccineportal.vaccination_portal.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.school.vaccineportal.vaccination_portal.security.AuthEntryPointJwt;
import com.school.vaccineportal.vaccination_portal.security.AuthTokenFilter;
import com.school.vaccineportal.vaccination_portal.security.CustomAccessDeniedHandler;
import com.school.vaccineportal.vaccination_portal.security.CustomAuthenticationProvider;

@Configuration
public class WebSecurityConfig {

        private AuthTokenFilter jwtRequestFilter;
        private CustomAuthenticationProvider customAuthProvider;
        private AuthEntryPointJwt authenticationEntryPoint;
        private CustomAccessDeniedHandler accessDeniedHandler;

        WebSecurityConfig(AuthTokenFilter jwtRequestFilter, CustomAuthenticationProvider customAuthProvider,
                        AuthEntryPointJwt authenticationEntryPoint, CustomAccessDeniedHandler accessDeniedHandler) {
                this.jwtRequestFilter = jwtRequestFilter;
                this.customAuthProvider = customAuthProvider;
                this.authenticationEntryPoint = authenticationEntryPoint;
                this.accessDeniedHandler = accessDeniedHandler;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable())
                                .cors(cors -> cors.configurationSource(
                                                corsConfigurationSource()))
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                new AntPathRequestMatcher("/api/auth/**"),
                                                                new AntPathRequestMatcher("/v2/api-docs/**"),
                                                                new AntPathRequestMatcher("/swagger-ui/**"),
                                                                new AntPathRequestMatcher("/swagger-resources/**"),
                                                                new AntPathRequestMatcher("/webjars/**"))
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .exceptionHandling(handling -> handling
                                                .authenticationEntryPoint(authenticationEntryPoint)
                                                .accessDeniedHandler(accessDeniedHandler))
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("http://localhost:5173"));
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(List.of("*"));
                config.setAllowCredentials(true); // optional, depending on your auth setup

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", config);
                return source;
        }

        @Bean
        public AuthenticationManager authenticationManager() {
                return new ProviderManager(List.of(customAuthProvider));
        }
}