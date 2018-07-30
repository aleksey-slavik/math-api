package ua.edu.uipa.math.lib.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Contain methods for create entities or list of entities
 *
 * @param <T> entity type
 * @author oleksii.slavik
 */
public interface Builder<T> {

    /**
     * Create entity
     *
     * @return entity
     */
    T build();

    /**
     * Create list of entities
     *
     * @param length length of list
     * @return list of entities
     */
    default List<T> list(int length) {
        List<T> questions = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            questions.add(build());
        }

        return questions;
    }
}
