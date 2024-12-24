package com.caocao.quizzapp.controller;

import com.caocao.quizzapp.model.Question;
import com.caocao.quizzapp.model.QuestionWrapper;
import com.caocao.quizzapp.model.Quiz;
import com.caocao.quizzapp.model.QuizSubmit;
import com.caocao.quizzapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("")
    public ResponseEntity<String> createQuiz(@RequestBody Map<String, Object> requestBody) {
        String category = (String) requestBody.get("category");
        int numQ = (int) requestBody.get("numQ");
        String title = (String) requestBody.get("title");
        return new ResponseEntity<>(quizService.createQuiz(category, numQ, title), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Quiz>> getQuizes() {
        return new ResponseEntity<>(quizService.getQuizes(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable String id) {
        return new ResponseEntity<>(quizService.getQuizQuestions(id), HttpStatus.OK);
    }

    @PostMapping("{id}/submit")
    public ResponseEntity<Integer> submitQuiz(@PathVariable String id, @RequestBody List<QuizSubmit> quizSubmits) {
        return new ResponseEntity<>(quizService.calculateResult(id, quizSubmits), HttpStatus.OK);
    }

}
