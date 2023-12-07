package com.todolist.todolistapi.exceptions;

import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ProblemDetail> todoNotFoundExceptionHandler(TodoNotFoundException ex) {
        ProblemDetail problemDetail = this.getProblemDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> fieldsMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getStatusCode());
        problemDetail.setDetail("Campos não preenchidos corretamente.");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("fieldsMessage", fieldsMessage);

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ProblemDetail> usernameNotFoundExceptionHandler(UsernameNotFoundException ex) {
        ProblemDetail problemDetail = this.getProblemDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @ExceptionHandler(EmailInUseException.class)
    public ResponseEntity<ProblemDetail> emailInUseExceptionHandler(EmailInUseException ex) {
        ProblemDetail problemDetail = this.getProblemDetail(HttpStatus.CONFLICT, ex.getMessage());

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ProblemDetail> badCredentialsExceptionHandler(BadCredentialsException ex) {
        ProblemDetail problemDetail = this.getProblemDetail(HttpStatus.BAD_REQUEST, ex.getMessage());

        return ResponseEntity.status(problemDetail.getStatus()).body(problemDetail);
    }

    private ProblemDetail getProblemDetail(HttpStatus status, String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, message);
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }
}
