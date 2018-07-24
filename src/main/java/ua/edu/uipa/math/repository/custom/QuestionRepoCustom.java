package ua.edu.uipa.math.repository.custom;

import ua.edu.uipa.math.model.Question;

import java.util.List;

public interface QuestionRepoCustom {

    List<Question> findAllWithSortQuery(int offset, int limit, String[] sortBy);
}
