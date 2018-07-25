package ua.edu.uipa.math.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SortHelper {

    private static final char ASCENDING_PATTERN = '+';

    private static final char DESCENDING_PATTERN = '-';

    public static <T> List<Order> createOrderByList(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> from, String[] fields) {
        List<Order> orders = new ArrayList<>();

        for (String field : fields) {
            if (SortHelper.isAscending(field)) {
                orders.add(builder.asc(from.get(getAttributeName(field))));
            } else {
                orders.add(builder.desc(from.get(getAttributeName(field))));
            }
        }

        return orders;
    }

    private static boolean isAscending(String field) {
        switch (field.charAt(0)) {
            case ASCENDING_PATTERN:
                return true;
            case DESCENDING_PATTERN:
                return false;
            default:
                return false;
        }
    }

    private static String getAttributeName(String field) {
        if (field.charAt(0) == ASCENDING_PATTERN || field.charAt(0) == DESCENDING_PATTERN) {
            return field.substring(1);
        } else {
            return field;
        }
    }
}
