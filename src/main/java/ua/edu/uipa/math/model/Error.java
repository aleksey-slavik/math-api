package ua.edu.uipa.math.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {

    private String code;
    private Long timestamp;
    private String message;

    public Error() {
        this.timestamp = System.currentTimeMillis();
    }

    public Error code(String code) {
        this.code = code;
        return this;
    }

    public Error message(String message) {
        this.message = message;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
