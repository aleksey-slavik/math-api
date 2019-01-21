package ua.edu.uipa.math.swagger;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import ua.edu.uipa.math.controller.UserController;
import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.exception.ResourceNotFoundException;
import ua.edu.uipa.math.model.user.UserResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * Contains all swagger documentation to {@link UserController} operations
 *
 * @author oleksii.slavik
 */
@Api("Operations with users")
public interface UserDefinition {

    /**
     * API endpoint to get user data by given username and language.
     *
     * @param username username of user
     * @param language language of data of user
     * @return user data with given translation
     * @throws ResourceNotFoundException throws when user with requested username not found
     *                                   or translation of user data with requested language not found
     */
    @ApiOperation(
            value = "Returns user by username and language",
            httpMethod = "GET",
            nickname = "getUserByUsernameAndLanguage",
            produces = "application/json;charset=UTF-8"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            code = HttpServletResponse.SC_OK,
                            message = "operation successfully completed",
                            response = UserResponse.class
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_BAD_REQUEST,
                            message = "user with requested username not found or translation of user data with requested language not found"
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            message = "internal server error"
                    )
            }
    )
    ResponseEntity<?> getUserByUsernameAndLanguage(
            @ApiParam(value = "username", required = true, type = "String") String username,
            Language language);
}
