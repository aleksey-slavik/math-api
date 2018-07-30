package ua.edu.uipa.math.util.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods for create {@link Order} list for queries
 *
 * @author oleksii.slavik
 */
public class OrderHelper {

    /**
     * ascending pattern
     */
    private static final char ASCENDING_PATTERN = '+';

    /**
     * descending pattern
     */
    private static final char DESCENDING_PATTERN = '-';

    /**
     * Create {@link Order} list for queries
     *
     * @param builder {@link CriteriaBuilder} object of query
     * @param from    {@link Root} object of query
     * @param fields  array of properties for sorting
     * @param <T>     type of entity
     * @return list of {@link Order} for query
     */
    public static <T> List<Order> createOrderByList(CriteriaBuilder builder, Root<T> from, String[] fields) {
        List<Order> orders = new ArrayList<>();

        for (String field : fields) {
            if (isAscending(field)) {
                orders.add(builder.asc(from.get(getAttributeName(field))));
            } else {
                orders.add(builder.desc(from.get(getAttributeName(field))));
            }
        }

        return orders;
    }

    /**
     * Check sorting direction of given property
     *
     * @param field property
     * @return true, if property have ascending direction, false, in otherwise
     */
    public static boolean isAscending(String field) {
        switch (field.charAt(0)) {
            case ASCENDING_PATTERN:
                return true;
            case DESCENDING_PATTERN:
                return false;
            default:
                return true;
        }
    }

    /**
     * Parse attribute name.
     * Remove sorting direction from first position if it present.
     *
     * @param field property
     * @return attribute name
     */
    public static String getAttributeName(String field) {
        if (field.charAt(0) == ASCENDING_PATTERN || field.charAt(0) == DESCENDING_PATTERN) {
            return field.substring(1);
        } else {
            return field;
        }
    }
}
