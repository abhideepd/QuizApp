package com.example.quizApp.controller;

import com.example.quizApp.model.Question;
import com.example.quizApp.model.QuestionWrapper;
import com.example.quizApp.model.Quiz;
import com.example.quizApp.model.QuizResponse;
import com.example.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @GetMapping("create")
    public ResponseEntity<Quiz> create(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return ResponseEntity.ok().body(quizService.createQuiz(category, numQ, title));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        return ResponseEntity.ok().body(quizService.findQuizById(id));
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponse> quizResponsesList){
        Integer correctAnswers=quizService.calculateResult(id, quizResponsesList);
        String output="Correct Answers are: "+correctAnswers;
        return ResponseEntity.ok().body(output);
    }

    @PostMapping("secret/submit/{id}")
    public ResponseEntity<String> secretSubmitQuiz(@PathVariable Integer id, @RequestBody List<String> quizResponsesList){
        Integer correctAnswers=quizService.secretCalculateResult(id, quizResponsesList);
        String output="Correct Answers are: "+correctAnswers;
        return ResponseEntity.ok().body(output);
    }
}
