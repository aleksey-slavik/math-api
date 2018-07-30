package ua.edu.uipa.math.swagger;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import ua.edu.uipa.math.exception.ResourceNotFoundException;
import ua.edu.uipa.math.model.Question;

import javax.servlet.http.HttpServletResponse;

/**
 * Contains all swagger documentation to {@link ua.edu.uipa.math.controller.QuestionController} operations
 */
@Api("Operations with questions")
public interface QuestionDefinition {

    /**
     * API endpoint to get question by id
     *
     * @param id id of question
     * @return question with requested id
     * @throws ResourceNotFoundException throws when question with requested id not found
     */
    @ApiOperation(
            value = "Returns question by id",
            httpMethod = "GET",
            nickname = "getQuestionById",
            produces = "application/json"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            code = HttpServletResponse.SC_OK,
                            message = "operation successfully completed",
                            response = Question.class
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_BAD_REQUEST,
                            message = "question with given id not found"
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            message = "internal server error"
                    )
            }
    )
    ResponseEntity<?> getQuestionById(@ApiParam(value = "id", required = true, type = "Long") Long id);

    /**
     * API endpoint to get list of question
     *
     * @param offset  position of the first result to retrieve
     * @param limit   maximum number of results to retrieve
     * @param orderBy array of attribute names with order direction in first character('+' or '-')
     * @param fields  array of attribute names, which are must be in response
     * @return list of questions
     */
    @ApiOperation(
            value = "Returns questions list",
            httpMethod = "GET",
            nickname = "getQuestionList",
            produces = "application/json"
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            paramType = "int",
                            name = "offset",
                            dataType = "Integer",
                            dataTypeClass = Integer.class
                    ),
                    @ApiImplicitParam(
                            paramType = "int",
                            name = "limit",
                            dataType = "Integer",
                            dataTypeClass = Integer.class
                    ),
                    @ApiImplicitParam(
                            paramType = "String[]",
                            name = "orderBy",
                            dataType = "String[",
                            dataTypeClass = String[].class
                    ),
                    @ApiImplicitParam(
                            paramType = "String[]",
                            name = "fields",
                            dataType = "String[",
                            dataTypeClass = String[].class
                    )
            }
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            code = HttpServletResponse.SC_OK,
                            message = "operation successfully completed",
                            response = Question.class
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            message = "internal server error"
                    )
            }
    )
    ResponseEntity<?> getQuestionList(int offset, int limit, String[] orderBy, String[] fields);

    /**
     * API endpoint to create new question
     *
     * @param question body of question
     * @return created question
     */
    @ApiOperation(
            value = "Saves questions",
            httpMethod = "POST",
            nickname = "postQuestion",
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            paramType = "body",
                            name = "question",
                            required = true,
                            dataType = "Question",
                            dataTypeClass = Question.class
                    )
            }
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            code = HttpServletResponse.SC_OK,
                            message = "operation successfully completed",
                            response = Question.class
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_BAD_REQUEST,
                            message = "validation failed"
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            message = "internal server error"
                    )
            }
    )
    ResponseEntity<?> postQuestion(Question question);

    /**
     * API endpoint to update existed question
     *
     * @param id       id of question
     * @param question body of question
     * @return updated question
     * @throws ResourceNotFoundException throws when question with requested id not found
     */
    @ApiOperation(
            value = "Updates questions by id",
            httpMethod = "PUT",
            nickname = "updateQuestionById",
            produces = "application/json",
            consumes = "application/json"
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            paramType = "body",
                            name = "question",
                            required = true,
                            dataType = "Question",
                            dataTypeClass = Question.class
                    )
            }
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            code = HttpServletResponse.SC_OK,
                            message = "operation successfully completed",
                            response = Question.class
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_BAD_REQUEST,
                            message = "question with given id not found"
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_BAD_REQUEST,
                            message = "validation failed"
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            message = "internal server error"
                    )
            }
    )
    ResponseEntity<?> updateQuestionById(@ApiParam(value = "id", required = true, type = "Long") Long id, Question question);

    /**
     * API endpoint to delete existed question
     *
     * @param id id of question
     * @return deleted question
     * @throws ResourceNotFoundException throws when question with requested id not found
     */
    @ApiOperation(
            value = "Removes questions by id",
            httpMethod = "DELETE",
            nickname = "deleteQuestionById",
            produces = "application/json"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            code = HttpServletResponse.SC_OK,
                            message = "operation successfully completed",
                            response = Question.class
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_BAD_REQUEST,
                            message = "question with given id not found"
                    ),
                    @ApiResponse(
                            code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            message = "internal server error"
                    )
            }
    )
    ResponseEntity<?> deleteQuestionById(@ApiParam(value = "id", required = true, type = "Long") Long id);
}
