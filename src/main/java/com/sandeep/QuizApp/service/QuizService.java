package com.sandeep.QuizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sandeep.QuizApp.dao.QuestionDAO;
import com.sandeep.QuizApp.dao.QuizDAO;
import com.sandeep.QuizApp.entity.Question;
import com.sandeep.QuizApp.entity.QuestionWrapper;
import com.sandeep.QuizApp.entity.Quiz;
import com.sandeep.QuizApp.entity.Response;

@Service
public class QuizService {
	@Autowired
	QuizDAO quizDAO;
	@Autowired
	QuestionDAO questionDAO;
	
	public ResponseEntity<String> createQuiz(String category, int noofQue, String title) {
		Quiz quiz= new Quiz();
		quiz.setTitle(title);
		List<Question> questions =questionDAO.findRandomQuestionsByCategory(category,noofQue);
//		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDAO.save(quiz);
		return new ResponseEntity<String>("Succes",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizById(Integer id) {
//		return new ResponseEntity<>(quizDAO.findById(id),HttpStatus.OK);
		Optional<Quiz> quiz=quizDAO.findById(id);
		List<Question> questionsFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUsers = new ArrayList<>();
		for(Question q: questionsFromDB)
		{
			QuestionWrapper qr = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionsForUsers.add(qr);
		}
		return new ResponseEntity<List<QuestionWrapper>>(questionsForUsers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
	    Optional<Quiz> quizOpt = quizDAO.findById(id);
	    if (quizOpt.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    Quiz quiz = quizOpt.get();
	    List<Question> questions = quiz.getQuestions();
	    int correct = 0;

	    for (Response response : responses) {
	        for (Question question : questions) {
	            if (question.getId().equals(response.getId())
	                && question.getRightAnswer().equalsIgnoreCase(response.getResponse())) {
	                correct++;
	                break;
	            }
	        }
	    }
	    return new ResponseEntity<>(correct, HttpStatus.OK);
	}

}
