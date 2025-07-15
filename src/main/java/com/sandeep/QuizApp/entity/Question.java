package com.sandeep.QuizApp.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

//    private Integer id;

    @Column(name = "question_title")
    private String questionTitle;

    private String option1;
    private String option2;
    
    private String option3;
    private String option4;

    @Column(name = "right_answer")
    private String rightAnswer;
 
    @Column(name = "difficulty_level")
    private String difficultyLevel;

    private String category;
}
