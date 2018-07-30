package ua.edu.uipa.math.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.edu.uipa.math.exception.ResourceNotFoundException;
import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.dao.QuestionDao;
import ua.edu.uipa.math.swagger.QuestionDefinition;
import ua.edu.uipa.math.util.PropertyFilter;
import ua.edu.uipa.math.util.query.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * Operations with questions.
 *
 * @author oleksii.slavik
 */
@RestController
@RequestMapping(value = "api/v1/questions")
public class QuestionController implements QuestionDefinition {

    /**
     * Dao object for questions
     */
    private final QuestionDao questionDao;

    @Autowired
    public QuestionController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionDao.findById(id);
        question.orElseThrow(() -> new ResourceNotFoundException("Question with id=" + id + " not found!"));
        return ResponseEntity.ok().body(question);
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    @PostMapping
    public ResponseEntity<?> postQuestion(@Validated @RequestBody Question question) {
        question.timestamp();
        Question response = questionDao.save(question);
        return ResponseEntity.ok().body(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestionById(@PathVariable Long id, @Validated @RequestBody Question question) {
        Optional<Question> oldQuestion = questionDao.findById(id);
        oldQuestion.orElseThrow(() -> new ResourceNotFoundException("Can't update question with id=" + id + " because it not found!"));
        question.setId(id);
        question.setCreated(oldQuestion.get().getCreated());
        Question response = questionDao.save(question);
        return ResponseEntity.ok().body(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionDao.findById(id);
        question.orElseThrow(() -> new ResourceNotFoundException("Can't delete question with id=" + id + " because it not found!"));
        questionDao.deleteById(id);
        return ResponseEntity.ok().body(question);
    }
}
