package com.gp.quiz_service.service;


import com.gp.quiz_service.dao.QuizDao;
import com.gp.quiz_service.model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

 @Autowired
 QuizDao quizDao;

 // @Autowired
//  QuestionDao questionDao;
    @Transactional
    public ResponseEntity<String> createQuiz(String categoryName, Integer numQuestions, String title) {


//        // 1. Fetch questions from DB
       List<Question> questions= //generate
               //questionDao.findRandomQuestionsByCategory(category,numQ);
//
//        // 2. ADD THIS LINE HERE
//        System.out.println("DEBUG: Category passed: " + category);
//        System.out.println("DEBUG: Questions found in DB: " + questions.size());
//
//        // 3. Logic continues to save quiz to database
//        Quiz quiz=new Quiz();
//     quiz.setTitle(title);
//     quiz.setQuestions(questions);
//     quizDao.save(quiz);
return new ResponseEntity<>("success", HttpStatus.CREATED);


    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz= quizDao.findById(id);
        List<Question> questionsFromDB=quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();
         for(Question q:questionsFromDB){
             QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
             questionsForUser.add(qw);
         }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

   /* public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
       Quiz quiz = quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0 ;
        int i=0;
        for(Response response : responses){
          if(response.getResponse().equals(questions.get(i).getRightAnswer())){
              right++;
          }

              i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }*/

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;


        for (int i = 0; i < responses.size(); i++) {
            if (responses.get(i).getResponse().equals(questions.get(i).getRightAnswer())) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
