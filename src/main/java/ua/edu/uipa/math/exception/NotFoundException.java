package ua.edu.uipa.math.exception;

public class NotFoundException extends RuntimeException {

    private String message;

    public NotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
