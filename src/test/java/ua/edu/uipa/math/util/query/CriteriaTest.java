package ua.edu.uipa.math.util.query;

import org.junit.Test;
import ua.edu.uipa.math.lib.builder.TestEntityBuilder;
import ua.edu.uipa.math.lib.entity.TestEntity;
import ua.edu.uipa.math.lib.random.Generator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Contains tests for {@link Criteria}
 *
 * @author oleksii.slavik
 */
public class CriteriaTest {

    /**
     * Test add offset to {@link Criteria}
     */
    @Test
    public void testOffset() {
        assertOffset(4567, 4567);
        assertOffset(1, 1);
        assertOffset(0, 0);
        assertOffset(0, -1);
        assertOffset(0, -876);
    }

    /**
     * Test add limit to {@link Criteria}
     */
    @Test
    public void testLimit() {
        assertLimit(4567, 4567);
        assertLimit(2, 2);
        assertLimit(1, 1);
        assertLimit(1, 0);
        assertLimit(1, -876);
    }

    /**
     * Test add orderBy to {@link Criteria}
     */
    @Test
    public void testOrderBy() {
        String[] orderBy = new String[Generator.nextInt(1, 10)];

        for (int i = 0; i < orderBy.length; i++) {
            orderBy[i] = Generator.nextString(5);
        }

        Criteria criteria = new Criteria().orderBy(orderBy);
        assertArrayEquals("Check that orderBy value=" + Arrays.toString(orderBy), orderBy, criteria.getOrderBy());
    }

    /**
     * Test add predicates to {@link Criteria}
     */
    @Test
    public void testPredicate() {
        Map<String, List<TestEntity>> predicates = new HashMap<>();
        TestEntityBuilder builder = new TestEntityBuilder();
        Criteria criteria = new Criteria();

        for (int i = 0; i < Generator.nextInt(1, 10); i++) {
            String key = Generator.nextString(5);
            predicates.put(key, builder.list(Generator.nextInt(1, 5)));
            criteria.predicate(key, predicates.get(key).toArray());
        }

        for (String key : predicates.keySet()) {
            assertValues(predicates.get(key), criteria.getPredicates().get(key));
        }
    }

    /**
     * Check offset in {@link Criteria}
     *
     * @param expected expected offset value
     * @param offset   actual offset value
     */
    private void assertOffset(int expected, int offset) {
        Criteria criteria = new Criteria().offset(offset);
        assertEquals("Check that offset value=" + expected, expected, criteria.getOffset());
    }

    /**
     * Check limit in {@link Criteria}
     *
     * @param expected expected limit value
     * @param limit    actual limit value
     */
    private void assertLimit(int expected, int limit) {
        Criteria criteria = new Criteria().limit(limit);
        assertEquals("Check that limit value=" + expected, expected, criteria.getLimit());
    }

    /**
     * Check list of values for predicate in {@link Criteria}
     *
     * @param expected expected list of values
     * @param actual   actual list of values
     */
    private void assertValues(List<TestEntity> expected, List<Object> actual) {
        assertEquals("Check values size", expected.size(), actual.size());

        for (TestEntity entity : expected) {
            assertTrue("Check that entity contains in criteria", actual.contains(entity));
        }
    }
}
