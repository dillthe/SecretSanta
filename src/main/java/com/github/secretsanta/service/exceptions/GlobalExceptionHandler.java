package com.github.secretsanta.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(NotAcceptException.class)
        public ResponseEntity<Object> handleNotAcceptException(NotAcceptException ex, WebRequest request) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", System.currentTimeMillis());
            body.put("status", HttpStatus.NOT_ACCEPTABLE.value());
            body.put("error", "Not Acceptable");
            body.put("message", ex.getMessage());
            body.put("path", request.getDescription(false).replace("uri=", ""));

            return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
        }

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", System.currentTimeMillis());
            body.put("status", HttpStatus.NOT_FOUND.value());
            body.put("error", "Not Found");
            body.put("message", ex.getMessage());
            body.put("path", request.getDescription(false).replace("uri=", ""));

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(InvalidValueException.class)
        public ResponseEntity<Object> handleInvalidValueException(InvalidValueException ex, WebRequest request) {
            Map<String, Object> body = new HashMap<>();
            body.put("timestamp", System.currentTimeMillis());
            body.put("status", HttpStatus.BAD_REQUEST.value());
            body.put("error", "Bad Request");
            body.put("message", ex.getMessage());
            body.put("path", request.getDescription(false).replace("uri=", ""));

            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }

