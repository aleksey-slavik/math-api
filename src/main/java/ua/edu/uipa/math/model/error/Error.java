package ua.edu.uipa.math.model.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Domain object that represents error details for some custom errors.
 *
 * @author oleksii.slavik
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {

    /**
     * error code
     */
    private String code;

    /**
     * date of error creation(in milliseconds)
     */
    private Long timestamp;

    /**
     * error message
     */
    private String message;

    /**
     * Save date of error
     */
    public Error() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Add error code
     *
     * @param code error code
     */
    public Error code(String code) {
        this.code = code;
        return this;
    }

    /**
     * Add error message
     *
     * @param message error message
     */
    public Error message(String message) {
        this.message = message;
        return this;
    }

    /**
     * @return error code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return date of error creation(in milliseconds)
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @return error message
     */
    public String getMessage() {
        return message;
    }
}
