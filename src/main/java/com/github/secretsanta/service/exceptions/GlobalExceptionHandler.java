package com.github.secretsanta.service.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(NotAcceptException.class)
        public ResponseEntity<Object> handleNotAcceptException(NotAcceptException ex, WebRequest request) {
            log.error("NotAcceptException: {}", ex.getMessage());  // Logging the error
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("timestamp", System.currentTimeMillis());
            errorDetails.put("status", HttpStatus.NOT_ACCEPTABLE.value());
            errorDetails.put("error", "Not Acceptable");
            errorDetails.put("message", ex.getMessage());
            errorDetails.put("path", request.getDescription(false).replace("uri=", ""));

            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
        }

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
            log.error("InvalidValueException: {}", ex.getMessage());
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("timestamp", System.currentTimeMillis());
            errorDetails.put("status", HttpStatus.NOT_FOUND.value());
            errorDetails.put("error", "Not Found");
            errorDetails.put("message", ex.getMessage());
            errorDetails.put("path", request.getDescription(false).replace("uri=", ""));

            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(InvalidValueException.class)
        public ResponseEntity<Object> handleInvalidValueException(InvalidValueException ex, WebRequest request) {
            Map<String, Object> errorDetails = new HashMap<>();
            errorDetails.put("timestamp", System.currentTimeMillis());
            errorDetails.put("status", HttpStatus.BAD_REQUEST.value());
            errorDetails.put("error", "Bad Request");
            errorDetails.put("message", ex.getMessage());
            errorDetails.put("path", request.getDescription(false).replace("uri=", ""));

            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        }

//    @ExceptionHandler(JsonMappingException.class)
//    public ResponseEntity<Map<String, String>> handleJsonMappingException(JsonMappingException ex, WebRequest request) {
//        Map<String, String> errorDetails = new HashMap<>();
//        errorDetails.put("timestamp", String.valueOf(System.currentTimeMillis()));
//        errorDetails.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
//        errorDetails.put("error", "Invalid JSON Format");
//        errorDetails.put("message", "JSON 형식이 잘못되었습니다. 올바른 필드를 입력하세요.");
//        errorDetails.put("details", ex.getPathReference());  // 예외 발생 위치를 포함한 상세 정보
//        errorDetails.put("path", request.getDescription(false).replace("uri=", ""));
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }
    }

