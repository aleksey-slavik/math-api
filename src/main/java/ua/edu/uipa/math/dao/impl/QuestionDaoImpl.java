package ua.edu.uipa.math.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.edu.uipa.math.dao.custom.QuestionDaoCustom;
import ua.edu.uipa.math.model.message.Question;
import ua.edu.uipa.math.util.query.Criteria;
import ua.edu.uipa.math.util.query.QueryHelper;

import java.util.List;

/**
 * Additional DAO method implementation.
 *
 * @author oleksii.slavik
 */
@Component
public class QuestionDaoImpl implements QuestionDaoCustom {

    private final QueryHelper queryHelper;

    @Autowired
    public QuestionDaoImpl(QueryHelper queryHelper) {
        this.queryHelper = queryHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Question> findAllByCriteria(Criteria criteria) {
        return queryHelper.findAllByCriteria(criteria, Question.class);
    }
}
