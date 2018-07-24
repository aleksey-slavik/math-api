package ua.edu.uipa.math.util;

import org.apache.commons.beanutils.PropertyUtils;
import ua.edu.uipa.math.exception.FieldNotFoundException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class PropertyFilter {

    public static void includeFields(Object object, String[] includeFields) {
        for (PropertyDescriptor propertyDescriptor : PropertyUtils.getPropertyDescriptors(object)) {
            if (!Arrays.asList(includeFields).contains(propertyDescriptor.getName())) {
                clearValue(object, propertyDescriptor);
            }
        }
    }

    public static void excludeFields(Object object, String[] excludeFields) {
        for (PropertyDescriptor propertyDescriptor : PropertyUtils.getPropertyDescriptors(object)) {
            if (Arrays.asList(excludeFields).contains(propertyDescriptor.getName())) {
                clearValue(object, propertyDescriptor);
            }
        }
    }

    private static void clearValue(Object object, PropertyDescriptor propertyDescriptor) {
        try {
            if (propertyDescriptor.getPropertyType().equals(Boolean.class)) {
                PropertyUtils.setProperty(object, propertyDescriptor.getName(), false);
            } else {
                PropertyUtils.setProperty(object, propertyDescriptor.getName(), null);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new FieldNotFoundException("Property with name=" + propertyDescriptor.getName() + " not found!");
        }
    }
}
