package com.king.springposapi.exception;

import com.king.springposapi.config.ApiDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleException(IllegalStateException exception){
        return ResponseEntity
                .badRequest()
                .body(new ApiDTO(exception.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiDTO(exception.getMessage()));
    }

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<?> handleException(ObjectNotValidException exception){
        System.out.println("Exception Message: ");
        System.out.println(exception.getErrorMessages());
         return ResponseEntity
                .badRequest()
                .body(exception.getErrorMessages());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiDTO> handleException(AuthenticationException exception){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiDTO(exception.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiDTO> handleException(BadCredentialsException exception){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiDTO("Invalid Credentials"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiDTO> handleException(AccessDeniedException exception){
        return  ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ApiDTO(exception.getMessage()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiDTO> handleException(ExpiredJwtException exception) {
        ApiDTO apiDTO = new ApiDTO("Your session has expired. Please log in again.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiDTO);
    }
}
