package com.quiz.demoQuiz.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"id",
	"question",
	"propositions",
	"réponse",
	"anecdote"
})
public class Question {

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("question")
	private String question;
	
	@JsonProperty("propositions")
	private List<String> propositions = null;
	
	@JsonProperty("réponse")
	private String reponse;
	
	@JsonProperty("anecdote")
	private String anecdote;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonProperty("question")
	public String getQuestion() {
		return question;
	}

	@JsonProperty("question")
	public void setQuestion(String question) {
		this.question = question;
	}

	@JsonProperty("propositions")
	public List<String> getPropositions() {
		return propositions;
	}

	@JsonProperty("propositions")
	public void setPropositions(List<String> propositions) {
		this.propositions = propositions;
	}

	@JsonProperty("réponse")
	public String getReponse() {
		return rPonse;
	}

	@JsonProperty("réponse")
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	@JsonProperty("anecdote")
	public String getAnecdote() {
		return anecdote;
	}

	@JsonProperty("anecdote")
	public void setAnecdote(String anecdote) {
		this.anecdote = anecdote;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}