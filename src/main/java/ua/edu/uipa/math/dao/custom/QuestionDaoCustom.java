package ua.edu.uipa.math.dao.custom;

import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.util.Criteria;

import java.util.List;

public interface QuestionDaoCustom {

    List<Question> findAllByCriteria(Criteria criteria);
}
