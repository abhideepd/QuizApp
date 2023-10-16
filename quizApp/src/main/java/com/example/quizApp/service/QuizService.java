package com.example.quizApp.service;

import com.example.quizApp.dao.QuestionDao;
import com.example.quizApp.dao.QuizDao;
import com.example.quizApp.model.Question;
import com.example.quizApp.model.QuestionWrapper;
import com.example.quizApp.model.Quiz;
import com.example.quizApp.model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public Quiz createQuiz(String category, int numQ, String title){
        List<Question> questions=questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return quiz;
    }

    public List<QuestionWrapper> findQuizById(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionsFromDb=quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUsers=new ArrayList<>();
        for(Question q:questionsFromDb){
            QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUsers.add(qw);
        }
        return questionsForUsers;
    }

    public Integer calculateResult(Integer id, List<QuizResponse> quizResponsesList) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0, i=0;
        for(QuizResponse quizResponse:quizResponsesList){
            if(quizResponse.getResponse().equals(questions.get(i++).getRightAnswer())){
                ++right;
            }
        }
        return right;
    }
    public Integer secretCalculateResult(Integer id, List<String> quizResponsesList) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0, i=0;
        for(String quizResponse:quizResponsesList){
            if(quizResponse.equals(questions.get(i++).getRightAnswer())){
                ++right;
            }
        }
        return right;
    }
}
