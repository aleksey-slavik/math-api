package ua.edu.uipa.math.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.edu.uipa.math.exception.NotFoundException;
import ua.edu.uipa.math.model.Error;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
        Error error = new Error()
                .code("NotFoundException")
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
}
