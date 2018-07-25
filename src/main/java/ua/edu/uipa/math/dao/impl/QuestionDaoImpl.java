package ua.edu.uipa.math.dao.impl;

import ua.edu.uipa.math.dao.custom.QuestionDaoCustom;
import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.util.Criteria;
import ua.edu.uipa.math.util.SortHelper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class QuestionDaoImpl implements QuestionDaoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Question> findAllByCriteria(Criteria criteria) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> query = builder.createQuery(Question.class);
        Root<Question> root = query.from(Question.class);
        query.select(root);
        query.orderBy(SortHelper.createOrderByList(builder, query, root, criteria.getOrderBy()));
        TypedQuery<Question> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(criteria.getOffset());
        typedQuery.setMaxResults(criteria.getLimit());
        return typedQuery.getResultList();
    }
}
