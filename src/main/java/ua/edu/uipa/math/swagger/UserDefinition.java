package ua.edu.uipa.math.swagger;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import ua.edu.uipa.math.controller.UserController;
import ua.edu.uipa.math.enums.Language;
import ua.edu.uipa.math.exception.ResourceNotFoundException;
import ua.edu.uipa.math.dto.user.UserResponse;

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

    /**
     * API endpoint to get all data of users by given language.
     *
     * @param language language of data of user
     * @return list of users with given translation
     * @throws ResourceNotFoundException throws when translation of some user data with requested language not found
     */
    @ApiOperation(
            value = "Returns list of users data with given translation",
            httpMethod = "GET",
            nickname = "getUsersListByLanguage",
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
                            message = "translation of some user data with requested language not found"
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            message = "internal server error"
                    )
            }
    )
    ResponseEntity<?> getUsersListByLanguage(
            Language language);

    /**
     * API endpoint to get user data with with all accessible translations by username.
     *
     * @param username username of user
     * @return user data with all translations
     * @throws ResourceNotFoundException throws when user with requested username not found
     *                                   or any translation of user not found
     */
    @ApiOperation(
            value = "Returns user data with all accessible translations by username",
            httpMethod = "GET",
            nickname = "getAllUserDataByUsername",
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
                            message = "user with requested username not found or translation of user data with any language not found"
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            message = "internal server error"
                    )
            }
    )
    ResponseEntity<?> getAllUserDataByUsername(
            @ApiParam(value = "username", required = true, type = "String") String username
    );

    /**
     * API endpoint to generate new password for user by username.
     *
     * @param username username of user
     * @return new user password
     * @throws ResourceNotFoundException throws when user with requested username not found
     */
    @ApiOperation(
            value = "Returns user data with all accessible translations by username",
            httpMethod = "PATCH",
            nickname = "resetPasswordByUsername",
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
                            message = "user with requested username not found"
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            message = "internal server error"
                    )
            }
    )
    ResponseEntity<?> resetPasswordByUsername(
            @ApiParam(value = "username", required = true, type = "String") String username
    );
}
