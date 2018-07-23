package ua.edu.uipa.math.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.uipa.math.model.Question;

public interface QuestionRepo extends CrudRepository<Question, Long> {

}
