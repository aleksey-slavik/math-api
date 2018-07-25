package ua.edu.uipa.math.repository.impl;

import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.repository.custom.QuestionRepoCustom;
import ua.edu.uipa.math.util.SortHelper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class QuestionRepoCustomImpl implements QuestionRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Question> findAllWithSortQuery(int offset, int limit, String[] sortBy) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);
        query.select(root);
        query.orderBy(SortHelper.createOrderByList(builder, query, root, sortBy));
        TypedQuery<Question> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(limit);
        return typedQuery.getResultList();
    }
}
