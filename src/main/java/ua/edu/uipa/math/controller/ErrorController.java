package ua.edu.uipa.math.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.edu.uipa.math.exception.PropertyChangeException;
import ua.edu.uipa.math.exception.ResourceNotFoundException;
import ua.edu.uipa.math.model.error.Error;

/**
 * Exception handlers.
 *
 * @author oleksii.slavik
 */
@RestControllerAdvice
public class ErrorController {

    /**
     * @param exception {@link ResourceNotFoundException}
     * @return response
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        Error error = new Error()
                .code("ResourceNotFoundException")
                .message(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * @param exception {@link DataAccessException}
     * @return response
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccessException(DataAccessException exception) {
        Error error = new Error()
                .code("DataAccessException")
                .message(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * @param exception {@link PropertyChangeException}
     * @return response
     */
    @ExceptionHandler(PropertyChangeException.class)
    public ResponseEntity<?> handlePropertyChangeException(PropertyChangeException exception) {
        Error error = new Error()
                .code("PropertyChangeException")
                .message(exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
