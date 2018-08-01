package ua.edu.uipa.math.dao;

import org.springframework.data.repository.CrudRepository;
import ua.edu.uipa.math.dao.custom.QuestionDaoCustom;
import ua.edu.uipa.math.model.message.Question;

/**
 * Contains all data access layer (DAL) for Question Service.
 *
 * @author oleksii.slavik
 */
public interface QuestionDao extends CrudRepository<Question, Long>, QuestionDaoCustom {

}
