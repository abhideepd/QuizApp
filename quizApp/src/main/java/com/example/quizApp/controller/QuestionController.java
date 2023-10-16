package com.example.quizApp.controller;

import com.example.quizApp.model.Question;
import com.example.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("health")
    public String health(){
        return "Im good";
    }
    @GetMapping("secret/createQuestions")
    public ResponseEntity<List<Question>> createQuestions()throws IOException {
        return ResponseEntity.ok().body(questionService.updateQuestions());
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> questionsByCategory(@PathVariable String category) {
        return ResponseEntity.ok().body(questionService.getQuestionsByCategory(category));
    }
    @PostMapping("addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        Question q=questionService.addQuestion(question);
        return ResponseEntity.status(200).body(q);
    }
}
