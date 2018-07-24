package ua.edu.uipa.math.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.edu.uipa.math.exception.FieldNotFoundException;
import ua.edu.uipa.math.exception.ResourceNotFoundException;
import ua.edu.uipa.math.model.Error;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        Error error = new Error()
                .code("ResourceNotFoundException")
                .message(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException exception) {
        Error error = new Error()
                .code("DataAccessException")
                .message(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(FieldNotFoundException.class)
    public ResponseEntity<?> handleFieldNotFoundException(FieldNotFoundException exception) {
        Error error = new Error()
                .code("FieldNotFoundException")
                .message(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
