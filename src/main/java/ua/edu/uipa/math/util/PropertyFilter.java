package ua.edu.uipa.math.util;

import ua.edu.uipa.math.exception.PropertyChangeException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class PropertyFilter {

    public static <T> void includeAllFields(List<T> objects, String[] includeFields) {
        for (Object object : objects) {
            includeFields(object, includeFields);
        }
    }

    public static <T> void excludeAllFields(List<T> objects, String[] includeFields) {
        for (Object object : objects) {
            excludeFields(object, includeFields);
        }
    }

    private static void includeFields(Object object, String[] includeFields) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (!Arrays.asList(includeFields).contains(field.getName())) {
                clearValue(object, field);
            }
        }
    }

    private static void excludeFields(Object object, String[] excludeFields) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (Arrays.asList(excludeFields).contains(field.getName())) {
                clearValue(object, field);
            }
        }
    }

    private static void clearValue(Object object, Field field) {
        try {
            field.setAccessible(true);
            field.set(object, null);
        } catch (IllegalAccessException e) {
            throw new PropertyChangeException("Can't change property with name=" + field.getName() + "!");
        }
    }
}
