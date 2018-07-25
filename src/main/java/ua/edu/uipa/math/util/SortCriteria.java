package ua.edu.uipa.math.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCriteria {

    private int offset;
    private int limit;
    private String[] orderBy;
    private Map<String, List<Object>> predicates = new HashMap<>(0);

    public SortCriteria offset(int offset) {
        this.offset = offset > 1 ? offset : 1;
        return this;
    }

    public SortCriteria limit(int limit) {
        this.limit = limit > 1 ? limit : 1;
        return this;
    }

    public SortCriteria orderBy(String[] orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public SortCriteria predicate(String attribute, Object... values) {
        if (attribute != null && values != null && predicates != null) {
            for (Object value : values) {
                predicate(attribute, value);
            }
        }

        return this;
    }

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

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public String[] getOrderBy() {
        return orderBy;
    }

    public Map<String, List<Object>> getPredicates() {
        return predicates;
    }
}
