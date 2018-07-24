package ua.edu.uipa.math.repository.impl;

import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.repository.custom.QuestionRepoCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class QuestionRepoCustomImpl implements QuestionRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Question> findAllWithSortQuery(int offset, int limit, String[] sortBy) {
        Query query = entityManager.createNativeQuery(String.format("SELECT * FROM question q ORDER BY"), Question.class);
        return query.getResultList();
    }
}
