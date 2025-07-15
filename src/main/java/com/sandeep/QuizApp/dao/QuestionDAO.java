package com.sandeep.QuizApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sandeep.QuizApp.entity.Question;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer> {
	List<Question> findByCategory(String category);
//	@Query(value="select question q where q.category=:category ORDER BY RANDOM() LIMIT:noofQue",nativeQuery = true)
//	List<Question> findRandomQuestionsByCategory(String category, int noofQue);
	@Query(value = "SELECT * FROM question WHERE category = :category ORDER BY RAND() LIMIT :noofQue", nativeQuery = true)
	List<Question> findRandomQuestionsByCategory(String category, int noofQue);

}
