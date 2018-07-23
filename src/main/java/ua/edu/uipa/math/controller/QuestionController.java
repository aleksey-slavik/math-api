package ua.edu.uipa.math.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionRepo.findById(id);
        return ResponseEntity.ok().body(question);
    }

    @PostMapping
    ResponseEntity<?> postQuestion(@Validated @RequestBody Question question) {
        question.timestamp();
        Question response = questionRepo.save(question);
        return ResponseEntity.ok().body(response);
    }
}
