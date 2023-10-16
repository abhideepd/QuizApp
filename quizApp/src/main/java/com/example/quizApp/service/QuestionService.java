package com.example.quizApp.service;

import com.example.quizApp.model.DifficultyLevel;
import com.example.quizApp.model.Question;
import com.example.quizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions(){
        return questionDao.findAll();
    }
    public List<Question> getQuestionsByCategory(String category){
        return questionDao.findQuestionsByCategory(category);
    }
    public List<Question> updateQuestions() throws IOException {
        if(questionDao.count()>0){
            questionDao.deleteAll();
        }
        String path="D:\\Quiz_App\\quizApp\\src\\main\\java\\com\\example\\quizApp\\question_bank";
        FileReader f=new FileReader(path);
        BufferedReader x=new BufferedReader(f);
        String str=x.readLine();
        while(str!=null){
            String arr[]=str.split(";");
            Question question=new Question();
            question.setQuestionTitle(arr[1]);
            question.setOption1(arr[2]);
            question.setOption2(arr[3]);
            question.setOption3(arr[4]);
            question.setOption4(arr[5]);
            question.setCategory(arr[0].trim());
            question.setDifficultylevel(DifficultyLevel.getRandomLevel().toString());
            String temp=(arr[6].trim().charAt(0))+"";
            question.setRightAnswer(temp);
            questionDao.save(question);
            str=x.readLine();
        }
        return questionDao.findAll();
    }
    public Question addQuestion(Question question){
        question.setDifficultylevel(DifficultyLevel.getRandomLevel().toString());
        Question q=questionDao.save(question);
        return q;
    }
}
