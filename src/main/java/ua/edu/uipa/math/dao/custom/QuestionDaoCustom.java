package ua.edu.uipa.math.dao.custom;

import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.util.Criteria;

import java.util.List;

/**
 * Additional DAO interface for questions.
 *
 * @author oleksii.slavik
 */
public interface QuestionDaoCustom {

    /**
     * Finds all {@link Question} entities by {@link Criteria}
     *
     * @param criteria set of criteria
     * @return list of questions
     */
    List<Question> findAllByCriteria(Criteria criteria);
}
