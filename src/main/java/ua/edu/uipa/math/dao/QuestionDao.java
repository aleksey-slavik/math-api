package ua.edu.uipa.math.dao;

import org.springframework.data.repository.CrudRepository;
import ua.edu.uipa.math.dao.custom.QuestionDaoCustom;
import ua.edu.uipa.math.model.Question;

public interface QuestionDao extends CrudRepository<Question, Long>, QuestionDaoCustom {

}
