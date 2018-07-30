package ua.edu.uipa.math.entity;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.edu.uipa.math.lib.builder.QuestionBuilder;
import ua.edu.uipa.math.lib.random.Generator;
import ua.edu.uipa.math.model.Question;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Contains test for validation of {@link Question} entity
 *
 * @author oleksii.slavik
 */
public class QuestionTest {

    /**
     * title of question
     */
    private static final String TITLE = "title";

    /**
     * description of question
     */
    private static final String DESCRIPTION = "description";

    /**
     * contacts of question
     */
    private static final String CONTACTS = "contacts";

    /**
     * error message for title length validation
     */
    private static final String TITLE_LENGTH_ERROR = "length must be between 0 and 250";

    /**
     * error message for title not null validation
     */
    private static final String TITLE_NOT_NULL_ERROR = "must not be null";

    /**
     * error message for description length validation
     */
    private static final String DESCRIPTION_LENGTH_ERROR = "length must be between 0 and 10000";

    /**
     * error message for description not null validation
     */
    private static final String DESCRIPTION_NOT_NULL_ERROR = "must not be null";

    /**
     * error message for contacts length validation
     */
    private static final String CONTACTS_LENGTH_ERROR = "length must be between 0 and 250";

    /**
     * error message for contacts not null validation
     */
    private static final String CONTACTS_NOT_NULL_ERROR = "must not be null";

    private static Validator validator;

    private static QuestionBuilder builder;

    @BeforeClass
    public static void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        builder = new QuestionBuilder();
    }

    /**
     * Test success validation
     */
    @Test
    public void testRandomQuestion() {
        Question question = builder.build();
        Set<ConstraintViolation<Question>> violations = validator.validate(question);
        assertEquals(0, violations.size());
    }

    /**
     * Test title length validation
     */
    @Test
    public void testTitleProperty() throws Exception {
        assertStringLength(0, TITLE, TITLE_NOT_NULL_ERROR);

        assertStringLength(1, TITLE, null);
        assertStringLength(18, TITLE, null);
        assertStringLength(175, TITLE, null);
        assertStringLength(250, TITLE, null);

        assertStringLength(251, TITLE, TITLE_LENGTH_ERROR);
        assertStringLength(456, TITLE, TITLE_LENGTH_ERROR);
        assertStringLength(544, TITLE, TITLE_LENGTH_ERROR);
        assertStringLength(755, TITLE, TITLE_LENGTH_ERROR);
    }

    /**
     * Test description length validation
     */
    @Test
    public void testDescriptionLength() throws Exception {
        assertStringLength(0, DESCRIPTION, DESCRIPTION_NOT_NULL_ERROR);

        assertStringLength(1, DESCRIPTION, null);
        assertStringLength(18, DESCRIPTION, null);
        assertStringLength(1275, DESCRIPTION, null);
        assertStringLength(10000, DESCRIPTION, null);

        assertStringLength(10001, DESCRIPTION, DESCRIPTION_LENGTH_ERROR);
        assertStringLength(12345, DESCRIPTION, DESCRIPTION_LENGTH_ERROR);
        assertStringLength(14234, DESCRIPTION, DESCRIPTION_LENGTH_ERROR);
        assertStringLength(17542, DESCRIPTION, DESCRIPTION_LENGTH_ERROR);
    }

    /**
     * Test contacts length validation
     */
    @Test
    public void testContactsLength() throws Exception {
        assertStringLength(0, CONTACTS, CONTACTS_NOT_NULL_ERROR);

        assertStringLength(1, CONTACTS, null);
        assertStringLength(18, CONTACTS, null);
        assertStringLength(175, CONTACTS, null);
        assertStringLength(250, CONTACTS, null);

        assertStringLength(251, CONTACTS, CONTACTS_LENGTH_ERROR);
        assertStringLength(456, CONTACTS, CONTACTS_LENGTH_ERROR);
        assertStringLength(544, CONTACTS, CONTACTS_LENGTH_ERROR);
        assertStringLength(987, CONTACTS, CONTACTS_LENGTH_ERROR);
    }

    /**
     * Check entity validation
     *
     * @param length    length of string for property
     * @param fieldName name of property
     * @param error     error message
     */
    private void assertStringLength(int length, String fieldName, String error) throws Exception {
        Question question = builder.build();
        Field field = question.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(question, length < 1 ? null : Generator.nextString(length));
        Set<ConstraintViolation<Question>> violations = validator.validate(question);

        if (error == null) {
            assertEquals(0, violations.size());
        } else {
            assertEquals(1, violations.size());
            assertEquals(error, violations.iterator().next().getMessage());
        }
    }
}
