package com.example.rateLimiter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<String> handleServiceException(TooManyRequestsException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleServiceException(IllegalArgumentException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<String> handleServiceException(NotLoginException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
