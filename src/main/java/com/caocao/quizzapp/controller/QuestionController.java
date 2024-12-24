package com.caocao.quizzapp.controller;

import com.caocao.quizzapp.model.Question;
import com.caocao.quizzapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("")
    public ResponseEntity<List<Question>> getQuestions(@RequestParam(value = "category", required = false) String category) {
        try {
            if (category != null && !category.isEmpty()) {
                return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
            }
            return new ResponseEntity<>(questionService.getQuestions(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

}
