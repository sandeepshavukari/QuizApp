package com.sandeep.QuizApp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.QuizApp.entity.QuestionWrapper;
import com.sandeep.QuizApp.entity.Quiz;
import com.sandeep.QuizApp.entity.Response;
import com.sandeep.QuizApp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	@Autowired
	QuizService quizService;
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int noofQue,@RequestParam String title)
	{
		return quizService.createQuiz(category, noofQue, title);		
	}
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable Integer id)
	{
		return quizService.getQuizById(id);
	}
	@PostMapping("submitQuiz/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response)
	{
		return quizService.calculateScore(id,response);
	}
}
