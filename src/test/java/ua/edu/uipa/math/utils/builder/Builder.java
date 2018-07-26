package ua.edu.uipa.math.utils.builder;

import java.util.ArrayList;
import java.util.List;

public interface Builder<T> {

    T build();

    default List<T> list(int length) {
        List<T> questions = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            questions.add(build());
        }

        return questions;
    }
}
