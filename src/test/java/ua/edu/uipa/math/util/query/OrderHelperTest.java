package ua.edu.uipa.math.util.query;

import org.junit.Test;
import ua.edu.uipa.math.lib.random.Generator;

import static org.junit.Assert.assertEquals;

/**
 * Contains tests for {@link OrderHelper}
 *
 * @author oleksii.slavik
 */
public class OrderHelperTest {

    /**
     * Test parse attribute name
     */
    @Test
    public void testGetAttributeName() {
        String test = Generator.nextString(10);
        assertAttributeName(test, "+" + test);
        assertAttributeName(test, "-" + test);
        assertAttributeName("*" + test, "*" + test);
        assertAttributeName("+" + test, "++" + test);
        assertAttributeName("-" + test, "--" + test);
        assertAttributeName("*" + test, "-*" + test);
        assertAttributeName("*-" + test, "*-" + test);
        assertAttributeName(test + "+", test + "+");
        assertAttributeName(test + "-", test + "-");
        assertAttributeName(test, test);
    }

    /**
     * Test parse ascending
     */
    @Test
    public void testAscending() {
        assertAscending(true, "+" + Generator.nextString(10));
        assertAscending(true, Generator.nextString(10));
        assertAscending(false, "-" + Generator.nextString(10));
        assertAscending(true, "*" + Generator.nextString(10));
        assertAscending(true, "++" + Generator.nextString(10));
        assertAscending(false, "--" + Generator.nextString(10));
        assertAscending(false, "-*" + Generator.nextString(10));
        assertAscending(true, "*-" + Generator.nextString(10));
        assertAscending(true, Generator.nextString(10) + "+");
        assertAscending(true, Generator.nextString(10) + "-");
    }

    /**
     * Check attribute name
     *
     * @param expected expected attribute name
     * @param property attribute name
     */
    private void assertAttributeName(String expected, String property) {
        assertEquals("Check that property " + property + " parsed as " + expected, expected, OrderHelper.getAttributeName(property));
    }

    /**
     * Check attribute ascending
     *
     * @param isAscending true, if attribute must have ascending direction, false, in otherwise
     * @param property    attribute name
     */
    private void assertAscending(boolean isAscending, String property) {
        assertEquals("Check that property " + property + " have needed direction", isAscending, OrderHelper.isAscending(property));
    }
}
