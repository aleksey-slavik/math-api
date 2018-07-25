package ua.edu.uipa.math.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.edu.uipa.math.dao.custom.QuestionDaoCustom;
import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.util.Criteria;
import ua.edu.uipa.math.util.QueryHelper;

import java.util.List;

/**
 * Additional DAO method implementation.
 *
 * @author oleksii.slavik
 */
@Component
public class QuestionDaoImpl implements QuestionDaoCustom {

    private final QueryHelper query;

    @Autowired
    public QuestionDaoImpl(QueryHelper query) {
        this.query = query;
    }

    /** {@inheritDoc} */
    @Override
    public List<Question> findAllByCriteria(Criteria criteria) {
        return query.findAllByCriteria(criteria, Question.class);
    }
}
