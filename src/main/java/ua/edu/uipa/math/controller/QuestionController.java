package ua.edu.uipa.math.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.edu.uipa.math.exception.ResourceNotFoundException;
import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.dao.QuestionDao;
import ua.edu.uipa.math.util.PropertyFilter;
import ua.edu.uipa.math.util.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * Operations with questions.
 *
 * @author oleksii.slavik
 */
@RestController
@RequestMapping(value = "api/v1/questions")
public class QuestionController {

    /**
     * Dao object for questions
     */
    private final QuestionDao questionDao;

    @Autowired
    public QuestionController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    /**
     * API endpoint to get question by id
     *
     * @param id id of question
     * @return question with requested id
     * @throws ResourceNotFoundException throws when question with requested id not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionDao.findById(id);
        question.orElseThrow(() -> new ResourceNotFoundException("Question with id=" + id + " not found!"));
        return ResponseEntity.ok().body(question);
    }

    /**
     * API endpoint to get list of question
     *
     * @param offset  position of the first result to retrieve
     * @param limit   maximum number of results to retrieve
     * @param orderBy array of attribute names with order direction in first character('+' or '-')
     * @param fields  array of attribute names, which are must be in response
     * @return list of questions
     */
    @GetMapping
    public ResponseEntity<?> getQuestionList(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "+id") String[] orderBy,
            @RequestParam(defaultValue = "id,title,description,created") String[] fields) {

        Criteria criteria = new Criteria()
                .offset(offset)
                .limit(limit)
                .orderBy(orderBy);

        List<Question> questions = questionDao.findAllByCriteria(criteria);
        PropertyFilter.includeAllFields(questions, fields);
        return ResponseEntity.ok().body(questions);
    }

    /**
     * API endpoint to create new question
     *
     * @param question body of question
     * @return created question
     */
    @PostMapping
    public ResponseEntity<?> postQuestion(@Validated @RequestBody Question question) {
        question.timestamp();
        Question response = questionDao.save(question);
        return ResponseEntity.ok().body(response);
    }

    /**
     * API endpoint to update existed question
     *
     * @param id       id of question
     * @param question body of question
     * @return updated question
     * @throws ResourceNotFoundException throws when question with requested id not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @Validated @RequestBody Question question) {
        Optional<Question> oldQuestion = questionDao.findById(id);
        oldQuestion.orElseThrow(() -> new ResourceNotFoundException("Can't update question with id=" + id + " because it not found!"));
        question.setId(id);
        question.setCreated(oldQuestion.get().getCreated());
        Question response = questionDao.save(question);
        return ResponseEntity.ok().body(response);
    }

    /**
     * API endpoint to delete existed question
     *
     * @param id id of question
     * @return deleted question
     * @throws ResourceNotFoundException throws when question with requested id not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionDao.findById(id);
        question.orElseThrow(() -> new ResourceNotFoundException("Can't delete question with id=" + id + " because it not found!"));
        questionDao.deleteById(id);
        return ResponseEntity.ok().body(question);
    }
}
