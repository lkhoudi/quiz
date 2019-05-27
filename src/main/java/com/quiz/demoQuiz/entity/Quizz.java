package com.quiz.demoQuiz.entity;

import com.quiz.demoQuiz.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class Quizz {
	
	private List<Question> questions;
	private int idQuestion;
	private String theme;
	private String niveau;
	public Quizz(List<Question> questions) {
		this.questions=questions;
	}
	
	public Quizz(String theme,String niveau) {
		this.niveau=niveau;
		this.theme=theme;
		questions= new ArrayList<Question>();
	}
	public Question getQuestion() {
		
		return questions.get(idQuestion);
	}
	
	public void next() {
		idQuestion=!finish()?idQuestion+1:idQuestion;
	}
	
	public boolean finish() {
	
		return idQuestion >=questions.size();
	}
	
	public void recommence() {
		idQuestion=0;
	}
	
	public void add(Question question) {
		questions.add(question);
	}
	
	public void remove(Question question) {
		questions.remove(question);
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	
}
	
