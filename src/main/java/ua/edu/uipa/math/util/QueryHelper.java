package ua.edu.uipa.math.util;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class QueryHelper {

    @PersistenceContext
    private EntityManager entityManager;

    public <T> List<T> findAllByCriteria(Criteria criteria, Class<T> clazz) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root);
        query.orderBy(SortHelper.createOrderByList(builder, root, criteria.getOrderBy()));
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(criteria.getOffset());
        typedQuery.setMaxResults(criteria.getLimit());
        return typedQuery.getResultList();
    }
}
