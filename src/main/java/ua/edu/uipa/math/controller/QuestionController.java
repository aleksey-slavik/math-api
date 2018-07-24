package ua.edu.uipa.math.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.edu.uipa.math.exception.NotFoundException;
import ua.edu.uipa.math.model.Question;
import ua.edu.uipa.math.repository.QuestionRepo;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/questions")
public class QuestionController {

    private final QuestionRepo questionRepo;

    @Autowired
    public QuestionController(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionRepo.findById(id);
        question.orElseThrow(() -> new NotFoundException("Question with id=" + id + " not found!"));
        return ResponseEntity.ok().body(question);
    }

    @PostMapping
    public ResponseEntity<?> postQuestion(@Validated @RequestBody Question question) {
        question.timestamp();
        Question response = questionRepo.save(question);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @Validated @RequestBody Question question) {
        Optional<Question> oldQuestion = questionRepo.findById(id);
        oldQuestion.orElseThrow(() -> new NotFoundException("Can't update question with id=" + id + " because it not found!"));
        question.setId(id);
        question.setCreated(oldQuestion.get().getCreated());
        Question response = questionRepo.save(question);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionRepo.findById(id);
        question.orElseThrow(() -> new NotFoundException("Can't delete question with id=" + id + " because it not found!"));
        questionRepo.deleteById(id);
        return ResponseEntity.ok().body(question);
    }
}
