package ua.edu.uipa.math.exception;

/**
 * Exception throws when entity with requested parameters is not found
 *
 * @author oleksii.slavik
 */
public class ResourceNotFoundException extends RuntimeException {

    private String message;

    public ResourceNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
