package ua.edu.uipa.math.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains data for queries
 *
 * @author oleksii.slavik
 */
public class Criteria {

    /**
     * position of the first result to retrieve
     */
    private int offset;

    /**
     * maximum number of results to retrieve
     */
    private int limit;

    /**
     * array of attribute names with order direction in first character('+' or '-')
     */
    private String[] orderBy;

    /**
     * one to one correspondence between attributes and their values
     */
    private Map<String, List<Object>> predicates = new HashMap<>(0);

    /**
     * Set position of the first result to retrieve
     *
     * @param offset position of the first result to retrieve
     */
    public Criteria offset(int offset) {
        this.offset = offset > 0 ? offset : 0;
        return this;
    }

    /**
     * Set maximum number of results to retrieve
     *
     * @param limit maximum number of results to retrieve
     */
    public Criteria limit(int limit) {
        this.limit = limit > 1 ? limit : 1;
        return this;
    }

    /**
     * Set array of attribute names with order direction in first character
     *
     * @param orderBy array of attribute names with order direction in first character
     */
    public Criteria orderBy(String[] orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    /**
     * Set attribute and their values
     *
     * @param attribute attribute name
     * @param values    array of attribute values
     */
    public Criteria predicate(String attribute, Object... values) {
        if (attribute != null && values != null && predicates != null) {
            for (Object value : values) {
                predicate(attribute, value);
            }
        }

        return this;
    }

    /**
     * Add value to attribute
     *
     * @param attribute attribute name
     * @param value     attribute value
     */
    private void predicate(String attribute, Object value) {
        if (attribute != null && value != null && predicates != null) {
            List<Object> values;

            if (predicates.containsKey(attribute)) {
                values = predicates.get(attribute);
            } else {
                values = new ArrayList<>();
            }

            values.add(value);
            predicates.put(attribute, values);
        }
    }

    /**
     * @return position of the first result to retrieve
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @return maximum number of results to retrieve
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @return array of attribute names with order direction in first character
     */
    public String[] getOrderBy() {
        return orderBy;
    }

    /**
     * @return attributes and their values
     */
    public Map<String, List<Object>> getPredicates() {
        return predicates;
    }
}
