package com.todolist.todolistapi.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ExceptionDetails details = new ExceptionDetails();

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ExceptionDetails> todoNotFoundExceptionHandler(TodoNotFoundException ex,
                                                                         HttpServletRequest request) {
        details.setMessage(ex.getMessage());
        details.setStatus(HttpStatus.NOT_FOUND.value());
        details.setError(HttpStatus.NOT_FOUND.name());
        details.setTimestamp(LocalDateTime.now());
        details.setPath(request.getRequestURI());

        return ResponseEntity.status(details.getStatus()).body(details);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));

        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ValidationExceptionDetails details = new ValidationExceptionDetails();
        details.setMessage("Campos não preenchidos corretamente!");
        details.setStatus(ex.getStatusCode().value());
        details.setError(HttpStatus.valueOf(ex.getStatusCode().value()).name());
        details.setTimestamp(LocalDateTime.now());
        details.setPath(request.getRequestURI());
        details.setFields(fields);
        details.setFieldsMessage(fieldsMessage);

        return ResponseEntity.status(ex.getStatusCode()).body(details);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDetails> usernameNotFoundExceptionHandler(UsernameNotFoundException ex,
                                                                                 HttpServletRequest request) {
        details.setMessage(ex.getMessage());
        details.setStatus(HttpStatus.NOT_FOUND.value());
        details.setError(HttpStatus.NOT_FOUND.name());
        details.setTimestamp(LocalDateTime.now());
        details.setPath(request.getRequestURI());

        return ResponseEntity.status(details.getStatus()).body(details);
    }

    @ExceptionHandler(EmailInUseException.class)
    public ResponseEntity<ExceptionDetails> emailInUseExceptionHandler(EmailInUseException ex,
                                                                       HttpServletRequest request) {
        details.setMessage(ex.getMessage());
        details.setStatus(HttpStatus.CONFLICT.value());
        details.setError(HttpStatus.CONFLICT.name());
        details.setTimestamp(LocalDateTime.now());
        details.setPath(request.getRequestURI());

        return ResponseEntity.status(details.getStatus()).body(details);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionDetails> badCredentialsExceptionHandler(BadCredentialsException ex,
                                                                           HttpServletRequest request) {
        details.setMessage(ex.getMessage());
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setError(HttpStatus.BAD_REQUEST.name());
        details.setTimestamp(LocalDateTime.now());
        details.setPath(request.getRequestURI());

        return ResponseEntity.status(details.getStatus()).body(details);
    }
}
