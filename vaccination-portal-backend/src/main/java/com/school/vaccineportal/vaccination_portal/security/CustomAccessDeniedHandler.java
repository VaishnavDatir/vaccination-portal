package com.school.vaccineportal.vaccination_portal.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.vaccineportal.vaccination_portal.model.ApiResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        ApiResponse<String> errorResponse = new ApiResponse<>();
        errorResponse.setSuccess(false);
        errorResponse.setMessage("Access Denied: " + accessDeniedException.getMessage());
        errorResponse.setStatus(HttpStatus.FORBIDDEN);

        mapper.writeValue(response.getOutputStream(), errorResponse);
    }
}