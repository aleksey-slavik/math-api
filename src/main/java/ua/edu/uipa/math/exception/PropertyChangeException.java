package ua.edu.uipa.math.exception;

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
