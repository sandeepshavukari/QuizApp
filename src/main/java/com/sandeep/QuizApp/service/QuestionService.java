package com.sandeep.QuizApp.service;

import com.sandeep.QuizApp.dao.QuestionDAO;
import com.sandeep.QuizApp.entity.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity< List<Question>>getAllQuestions()
    {
    	try 
    	{
    		return new ResponseEntity<> (questionDAO.findAll(),HttpStatus.OK);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return new ResponseEntity<> (new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
	public ResponseEntity<List<Question>>getQuestionCategory(String category){
		try 
    	{
    		return new ResponseEntity<> (questionDAO.findByCategory(category),HttpStatus.OK);
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	return new ResponseEntity<> (new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String>addQuestion(Question question) {
		try 
		{
			questionDAO.save(question);
			return new ResponseEntity<String>("Succesfully Question Added",HttpStatus.CREATED);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<String> ("Error Saving",HttpStatus.BAD_REQUEST);
	}
//	public ResponseEntity< List<Question>> editQuestion(Question question) {
//		try {
//		questionDAO.save(question);
//		return new ResponseEntity<List<Question>>( questionDAO.findAll(),HttpStatus.CREATED);
//		}
//		catch(Exception e)
//		{
//			System.out.println(e.getMessage());
//		}
//		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
//	}
	public ResponseEntity<Question> editQuestion(Question question) {
	    try {
	        Question updatedQuestion = questionDAO.save(question);
	        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
	    } catch (Exception e) {
	        System.out.println("Error updating question: " + e.getMessage());
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
	}

	public ResponseEntity<List<Question>> deleteQuestion(Integer id) {
	    try {
	        questionDAO.deleteById(id);
	        return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	    }
	}

}
