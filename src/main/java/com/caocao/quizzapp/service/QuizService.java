package com.caocao.quizzapp.service;

import com.caocao.quizzapp.dao.QuestionDao;
import com.caocao.quizzapp.dao.QuizDao;
import com.caocao.quizzapp.model.Question;
import com.caocao.quizzapp.model.QuestionWrapper;
import com.caocao.quizzapp.model.Quiz;
import com.caocao.quizzapp.model.QuizSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
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

    public String createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return "Success";
    }

    public List<QuestionWrapper> getQuizQuestions(String id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for (Question q: questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return questionsForUser;
    }

    public List<Quiz> getQuizes() {
        return quizDao.findAll();
    }

    public Integer calculateResult(String id, List<QuizSubmit> quizSubmits) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (QuizSubmit qs: quizSubmits) {
            if (qs.getResponse().equals(questions.get(i).getRightAnswer())) {
                right++;
            }
            i++;
        }
        return right;
    }
}
