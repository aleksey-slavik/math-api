package ua.edu.uipa.math.exception;

public class FieldNotFoundException extends RuntimeException {

    private String message;

    public FieldNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
