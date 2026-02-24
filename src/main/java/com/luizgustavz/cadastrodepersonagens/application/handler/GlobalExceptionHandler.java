package com.luizgustavz.cadastrodepersonagens.application.handler;

import com.luizgustavz.cadastrodepersonagens.domain.exceptions.FieldsNotNullOrBlankException;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidAgeArgument;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidNameFormatException;
import com.luizgustavz.cadastrodepersonagens.domain.exceptions.InvalidUriException;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.DataViolationNameException;
import com.luizgustavz.cadastrodepersonagens.infrastructure.exceptions.EntityNotFoundException;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Map<String, Object> handlerResponse = new HashMap<>();
    final String MESSAGE = "Erros de entrada de dados";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlerMethodArgumentNotValidException(@Nonnull MethodArgumentNotValidException exception){

        handlerResponse.put("status", exception.getStatusCode());
        handlerResponse.put("timestamp", LocalDateTime.now());
        handlerResponse.put("problem", MESSAGE);
        handlerResponse.put("error:", exception.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> Map.of("Campo", Objects.requireNonNull(error.getField()), "Erro:", Objects.requireNonNull(error.getDefaultMessage())))
                .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handlerResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handlerHttpMessageNotReadableException(HttpMessageNotReadableException exception){

        handlerResponse.put("status", HttpStatus.CONFLICT);
        handlerResponse.put("timestamp", LocalDateTime.now());
        handlerResponse.put("problem", "Erro de tipagem de dados");
        handlerResponse.put("error", "O tipo de valor informado não corresponde com o tipo de valor esperado");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(handlerResponse);
    }

    @ExceptionHandler(InvalidNameFormatException.class)
    public ResponseEntity<Map<String, Object>> handlerInvalidNameFormatException(InvalidNameFormatException exception){

        handlerResponse.put("status", HttpStatus.BAD_REQUEST);
        handlerResponse.put("timestamp", LocalDateTime.now());
        handlerResponse.put("problem", MESSAGE);
        handlerResponse.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handlerResponse);
    }

    @ExceptionHandler(FieldsNotNullOrBlankException.class)
    public ResponseEntity<Map<String, Object>> handlerFieldsNotNullOrBlankException(FieldsNotNullOrBlankException exception){

        handlerResponse.put("status", HttpStatus.BAD_REQUEST);
        handlerResponse.put("timestamp", LocalDateTime.now());
        handlerResponse.put("problem", MESSAGE);
        handlerResponse.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handlerResponse);
    }

    @ExceptionHandler(InvalidAgeArgument.class)
    public ResponseEntity<Map<String, Object>> handlerInvalidAgeArgument(InvalidAgeArgument exception){

        handlerResponse.put("status", HttpStatus.BAD_REQUEST);
        handlerResponse.put("timestamp", LocalDateTime.now());
        handlerResponse.put("problem", MESSAGE);
        handlerResponse.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handlerResponse);
    }

    @ExceptionHandler(InvalidUriException.class)
    public ResponseEntity<Map<String, Object>> handlerInvalidUriException(InvalidUriException exception){

        handlerResponse.put("status", HttpStatus.BAD_REQUEST);
        handlerResponse.put("timestamp", LocalDateTime.now());
        handlerResponse.put("problem", MESSAGE);
        handlerResponse.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handlerResponse);
    }

    @ExceptionHandler(DataViolationNameException.class)
    public ResponseEntity<Map<String, Object>> handlerDataViolationNameException(DataViolationNameException exception){

        handlerResponse.put("status", HttpStatus.CONFLICT);
        handlerResponse.put("timestamp", LocalDateTime.now());
        handlerResponse.put("problem", MESSAGE);
        handlerResponse.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(handlerResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerEntityNotFoundException(EntityNotFoundException exception){

        handlerResponse.put("status", HttpStatus.NOT_FOUND);
        handlerResponse.put("timestamp", LocalDateTime.now());
        handlerResponse.put("problem", MESSAGE);
        handlerResponse.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(handlerResponse);
    }
}
