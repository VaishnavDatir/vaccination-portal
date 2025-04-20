package com.school.vaccineportal.vaccination_portal.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.vaccineportal.vaccination_portal.model.ApiResponse;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        ApiResponse<String> errorResponse = new ApiResponse<>();
        errorResponse.setSuccess(false);
        errorResponse.setMessage("Unauthorized: " + authException.getMessage());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED);

        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}