package ua.edu.uipa.math.util.query;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Contains methods for get data from DB
 *
 * @author oleksii.slavik
 */
@Service
public class QueryHelper {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Find all entities, which are corresponds to {@link Criteria}
     *
     * @param criteria search criteria data
     * @param clazz    class of object
     * @param <T>      type of entity
     * @return list of entities
     */
    public <T> List<T> findAllByCriteria(Criteria criteria, Class<T> clazz) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root);
        query.orderBy(OrderHelper.createOrderByList(builder, root, criteria.getOrderBy()));
        TypedQuery<T> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(criteria.getOffset());
        typedQuery.setMaxResults(criteria.getLimit());
        return typedQuery.getResultList();
    }
}
