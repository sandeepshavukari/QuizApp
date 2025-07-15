package com.sandeep.QuizApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sandeep.QuizApp.entity.Quiz;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer>{
	
}
