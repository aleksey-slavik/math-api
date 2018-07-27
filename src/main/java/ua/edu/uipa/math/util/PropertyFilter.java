package ua.edu.uipa.math.util;

import ua.edu.uipa.math.exception.PropertyChangeException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * Contains methods for include or exclude properties from object
 *
 * @author oleksii.slavik
 */
public class PropertyFilter {

    /**
     * pattern for include or exclude all properties from object
     */
    private static final String ALL_FIELDS_PATTERN = "**";

    /**
     * Include all properties from array of properties to all of array of object
     *
     * @param objects       array of objects
     * @param includeFields array of properties
     * @param <T>           type of object
     */
    public static <T> void includeAllFields(List<T> objects, String[] includeFields) {
        for (Object object : objects) {
            includeFields(object, includeFields);
        }
    }

    /**
     * Exclude all properties from array of properties from all of array of object
     *
     * @param objects       array of objects
     * @param excludeFields array of properties
     * @param <T>           type of object
     */
    public static <T> void excludeAllFields(List<T> objects, String[] excludeFields) {
        for (Object object : objects) {
            excludeFields(object, excludeFields);
        }
    }

    /**
     * Include all properties from array of properties to given object
     *
     * @param object        given object
     * @param includeFields array of properties
     */
    public static void includeFields(Object object, String[] includeFields) {
        if (!isAllFields(includeFields)) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (!Arrays.asList(includeFields).contains(field.getName())) {
                    clearValue(object, field);
                }
            }
        }
    }

    /**
     * Exclude all properties from array of properties from given object
     *
     * @param object        given object
     * @param excludeFields array of properties
     */
    public static void excludeFields(Object object, String[] excludeFields) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (Arrays.asList(excludeFields).contains(field.getName()) || isAllFields(excludeFields)) {
                clearValue(object, field);
            }
        }
    }

    /**
     * Remove property from given object
     *
     * @param object given object
     * @param field  name of property
     */
    private static void clearValue(Object object, Field field) {
        try {
            field.setAccessible(true);
            field.set(object, null);
        } catch (IllegalAccessException e) {
            throw new PropertyChangeException("Can't change property with name=" + field.getName() + "!");
        }
    }

    /**
     * Check that requested array of properties contains ALL_FIELDS_PATTERN
     *
     * @param fields array of properties
     * @return true, if array contains ALL_FIELDS_PATTERN, false, in otherwise
     */
    private static boolean isAllFields(String[] fields) {
        return fields.length == 1 && fields[0].equals(ALL_FIELDS_PATTERN);
    }
}
