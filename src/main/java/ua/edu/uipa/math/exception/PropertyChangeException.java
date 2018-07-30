package ua.edu.uipa.math.exception;

/**
 * Exception throws when obtain any error during remove unnecessary attributes from object(for example, when converting it to json)
 *
 * @author oleksii.slavik
 */
public class PropertyChangeException extends RuntimeException {

    private String message;

    public PropertyChangeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
