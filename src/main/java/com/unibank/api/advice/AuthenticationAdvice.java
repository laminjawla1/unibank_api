package com.unibank.api.advice;

import com.unibank.api.commons.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class AuthenticationAdvice {
    @ExceptionHandler(BadCredentialsException.class)
    public ApiErrorResponse handleBadCredentialException(HttpServletRequest request) {
        return ApiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(Map.of("credentials", "Invalid username or password"))
                .message("Invalid username or password")
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
