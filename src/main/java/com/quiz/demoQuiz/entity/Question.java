package com.quiz.demoQuiz.entity;

import java.util.List;

public class Question {

	private int id;

	private String question;

	private List<String> propositions;

	private String réponse;

	private String anecdote;

	public Question(int id, String question, List<String> propositions, String réponse, String anecdote) {

		this.id = id;

		this.question = question;

		this.propositions = propositions;

		this.réponse = réponse;

		this.anecdote = anecdote;

	}

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public String getQuestion() {

		return question;

	}

	public void setQuestion(String question) {

		this.question = question;

	}

	public List<String> getPropositions() {

		return propositions;

	}

	public void setPropositions(List<String> propositions) {

		this.propositions = propositions;

	}

	public String getRéponse() {

		return réponse;

	}

	public void setRéponse(String réponse) {

		this.réponse = réponse;

	}

	public String getAnecdote() {

		return anecdote;

	}

	public void setAnecdote(String anecdote) {

		this.anecdote = anecdote;

	}
}