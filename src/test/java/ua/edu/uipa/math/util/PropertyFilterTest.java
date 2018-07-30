package ua.edu.uipa.math.util;

import org.junit.Test;
import ua.edu.uipa.math.lib.random.Generator;
import ua.edu.uipa.math.lib.builder.TestEntityBuilder;
import ua.edu.uipa.math.lib.entity.TestEntity;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Contains tests for {@link PropertyFilter} class
 *
 * @author oleksii.slavik
 */
public class PropertyFilterTest {

    /**
     * select all fields pattern
     */
    private static final String[] ALL_FIELDS_ARRAY_PATTERN = new String[]{"**"};

    /**
     * array of all fields of {@link TestEntity}
     */
    private static final String[] ALL_FIELDS_ARRAY = new String[]{"intField", "longField", "stringField"};

    /**
     * array of {@link Integer} and {@link Long} fields of {@link TestEntity}
     */
    private static final String[] INT_LONG_ARRAY = new String[]{"intField", "longField"};

    /**
     * array of {@link Integer} and {@link String} fields of {@link TestEntity}
     */
    private static final String[] INT_STRING_ARRAY = new String[]{"intField", "stringField"};

    /**
     * array of {@link Long} and {@link String} fields of {@link TestEntity}
     */
    private static final String[] LONG_STRING_ARRAY = new String[]{"longField", "stringField"};

    /**
     * array of only {@link Integer} field of {@link TestEntity}
     */
    private static final String[] INT_ARRAY = new String[]{"intField"};

    /**
     * array of only {@link Long} field of {@link TestEntity}
     */
    private static final String[] LONG_ARRAY = new String[]{"longField"};

    /**
     * array of only {@link String} field of {@link TestEntity}
     */
    private static final String[] STRING_ARRAY = new String[]{"stringField"};

    /**
     * array of empty fields set
     */
    private static final String[] EMPTY_ARRAY = new String[]{};

    /**
     * {@link TestEntityBuilder} for creating entities and lists of entities
     */
    private TestEntityBuilder builder = new TestEntityBuilder();

    /**
     * Test include properties to {@link TestEntity}
     */
    @Test
    public void testIncludeFields() throws Exception {
        assertIncludeFields(ALL_FIELDS_ARRAY_PATTERN, ALL_FIELDS_ARRAY, EMPTY_ARRAY);
        assertIncludeFields(ALL_FIELDS_ARRAY, EMPTY_ARRAY);
        assertIncludeFields(INT_LONG_ARRAY, STRING_ARRAY);
        assertIncludeFields(INT_STRING_ARRAY, LONG_ARRAY);
        assertIncludeFields(LONG_STRING_ARRAY, INT_ARRAY);
        assertIncludeFields(INT_ARRAY, LONG_STRING_ARRAY);
        assertIncludeFields(LONG_ARRAY, INT_STRING_ARRAY);
        assertIncludeFields(STRING_ARRAY, INT_LONG_ARRAY);
        assertIncludeFields(EMPTY_ARRAY, ALL_FIELDS_ARRAY);
    }

    /**
     * Test exclude properties from {@link TestEntity}
     */
    @Test
    public void testExcludeFields() throws Exception {
        assertExcludeFields(ALL_FIELDS_ARRAY_PATTERN, EMPTY_ARRAY, ALL_FIELDS_ARRAY);
        assertExcludeFields(EMPTY_ARRAY, ALL_FIELDS_ARRAY);
        assertExcludeFields(STRING_ARRAY, INT_LONG_ARRAY);
        assertExcludeFields(LONG_ARRAY, INT_STRING_ARRAY);
        assertExcludeFields(INT_ARRAY, LONG_STRING_ARRAY);
        assertExcludeFields(LONG_STRING_ARRAY, INT_ARRAY);
        assertExcludeFields(INT_STRING_ARRAY, LONG_ARRAY);
        assertExcludeFields(INT_LONG_ARRAY, STRING_ARRAY);
        assertExcludeFields(ALL_FIELDS_ARRAY, EMPTY_ARRAY);
    }

    /**
     * Test include properties to list of {@link TestEntity}
     */
    @Test
    public void testIncludeFieldsInArray() throws Exception {
        assertIncludeFieldsInArray(ALL_FIELDS_ARRAY_PATTERN, ALL_FIELDS_ARRAY, EMPTY_ARRAY);
        assertIncludeFieldsInArray(ALL_FIELDS_ARRAY, EMPTY_ARRAY);
        assertIncludeFieldsInArray(INT_LONG_ARRAY, STRING_ARRAY);
        assertIncludeFieldsInArray(INT_STRING_ARRAY, LONG_ARRAY);
        assertIncludeFieldsInArray(LONG_STRING_ARRAY, INT_ARRAY);
        assertIncludeFieldsInArray(INT_ARRAY, LONG_STRING_ARRAY);
        assertIncludeFieldsInArray(LONG_ARRAY, INT_STRING_ARRAY);
        assertIncludeFieldsInArray(STRING_ARRAY, INT_LONG_ARRAY);
        assertIncludeFieldsInArray(EMPTY_ARRAY, ALL_FIELDS_ARRAY);
    }

    /**
     * Test exclude properties from list of {@link TestEntity}
     */
    @Test
    public void testExcludeFieldsInArray() throws Exception {
        assertExcludeFieldsInArray(ALL_FIELDS_ARRAY_PATTERN, EMPTY_ARRAY, ALL_FIELDS_ARRAY);
        assertExcludeFieldsInArray(EMPTY_ARRAY, ALL_FIELDS_ARRAY);
        assertExcludeFieldsInArray(STRING_ARRAY, INT_LONG_ARRAY);
        assertExcludeFieldsInArray(LONG_ARRAY, INT_STRING_ARRAY);
        assertExcludeFieldsInArray(INT_ARRAY, LONG_STRING_ARRAY);
        assertExcludeFieldsInArray(LONG_STRING_ARRAY, INT_ARRAY);
        assertExcludeFieldsInArray(INT_STRING_ARRAY, LONG_ARRAY);
        assertExcludeFieldsInArray(INT_LONG_ARRAY, STRING_ARRAY);
        assertExcludeFieldsInArray(ALL_FIELDS_ARRAY, EMPTY_ARRAY);
    }

    /**
     * Check include properties to list of {@link TestEntity}
     *
     * @param includes         array of properties, which need to include
     * @param expectedIncludes expected array of included properties
     * @param expectedExcludes expected array of excluded properties
     */
    private void assertIncludeFieldsInArray(String[] includes, String[] expectedIncludes, String[] expectedExcludes) throws IllegalAccessException {
        List<TestEntity> objects = builder.list(Generator.nextInt(1, 10));
        PropertyFilter.includeAllFields(objects, includes);

        for (TestEntity object : objects) {
            assertObjectFields(object, expectedIncludes, expectedExcludes);
        }
    }

    /**
     * Check include properties to list of {@link TestEntity}
     *
     * @param expectedIncludes expected array of included properties
     * @param expectedExcludes expected array of excluded properties
     */
    private void assertIncludeFieldsInArray(String[] expectedIncludes, String[] expectedExcludes) throws IllegalAccessException {
        assertIncludeFieldsInArray(expectedIncludes, expectedIncludes, expectedExcludes);
    }

    /**
     * Check exclude properties from list of {@link TestEntity}
     *
     * @param excludes         array of properties, which need to exclude
     * @param expectedIncludes expected array of included properties
     * @param expectedExcludes expected array of excluded properties
     */
    private void assertExcludeFieldsInArray(String[] excludes, String[] expectedIncludes, String[] expectedExcludes) throws IllegalAccessException {
        List<TestEntity> objects = builder.list(Generator.nextInt(1, 10));
        PropertyFilter.excludeAllFields(objects, excludes);

        for (TestEntity object : objects) {
            assertObjectFields(object, expectedIncludes, expectedExcludes);
        }
    }

    /**
     * Check exclude properties from list of {@link TestEntity}
     *
     * @param expectedIncludes expected array of included properties
     * @param expectedExcludes expected array of excluded properties
     */
    private void assertExcludeFieldsInArray(String[] expectedIncludes, String[] expectedExcludes) throws IllegalAccessException {
        assertExcludeFieldsInArray(expectedExcludes, expectedIncludes, expectedExcludes);
    }

    /**
     * Check include properties to {@link TestEntity}
     *
     * @param includes         array of properties, which need to include
     * @param expectedIncludes expected array of included properties
     * @param expectedExcludes expected array of excluded properties
     */
    private void assertIncludeFields(String[] includes, String[] expectedIncludes, String[] expectedExcludes) throws IllegalAccessException {
        TestEntity object = builder.build();
        PropertyFilter.includeFields(object, includes);
        assertObjectFields(object, expectedIncludes, expectedExcludes);
    }

    /**
     * Check include properties to {@link TestEntity}
     *
     * @param expectedIncludes expected array of included properties
     * @param expectedExcludes expected array of excluded properties
     */
    private void assertIncludeFields(String[] expectedIncludes, String[] expectedExcludes) throws IllegalAccessException {
        assertIncludeFields(expectedIncludes, expectedIncludes, expectedExcludes);
    }

    /**
     * Check exclude properties from {@link TestEntity}
     *
     * @param excludes         array of properties, which need to exclude
     * @param expectedIncludes expected array of included properties
     * @param expectedExcludes expected array of excluded properties
     */
    private void assertExcludeFields(String[] excludes, String[] expectedIncludes, String[] expectedExcludes) throws IllegalAccessException {
        TestEntity object = builder.build();
        PropertyFilter.excludeFields(object, excludes);
        assertObjectFields(object, expectedIncludes, expectedExcludes);
    }

    /**
     * Check exclude properties from {@link TestEntity}
     *
     * @param expectedIncludes expected array of included properties
     * @param expectedExcludes expected array of excluded properties
     */
    private void assertExcludeFields(String[] expectedIncludes, String[] expectedExcludes) throws IllegalAccessException {
        assertExcludeFields(expectedExcludes, expectedIncludes, expectedExcludes);
    }

    /**
     * Check included and excluded properties in given {@link Object}
     *
     * @param object   given {@link Object}
     * @param includes array of properties, which must be included
     * @param excludes array of properties, which must be excluded
     */
    private void assertObjectFields(Object object, String[] includes, String[] excludes) throws IllegalAccessException {
        for (String property : includes) {
            assertTrue("Check that " + property + " contains in object", isContainProperty(object, property));
        }

        for (String property : excludes) {
            assertTrue("Check that " + property + " not contains in object", !isContainProperty(object, property));
        }
    }

    /**
     * Check that given {@link Object} contains not null property with given name
     *
     * @param object   given {@link Object}
     * @param property name of property
     * @return true, if {@link Object} contains not null property, false, in otherwise
     */
    private boolean isContainProperty(Object object, String property) throws IllegalAccessException {
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.getName().equals(property) && field.get(object) != null) {
                return true;
            }
        }

        return false;
    }
}
