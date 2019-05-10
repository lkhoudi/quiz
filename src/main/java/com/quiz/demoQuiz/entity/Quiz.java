package com.quiz.demoQuiz.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;

import com.google.gson.*;

public class Quiz {
	
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		File jsonFile = Paths.get("quiz/src/main/resources/json/animaux.json").toFile();
		JsonObject jsonObject = gson.fromJson(new FileReader(jsonFile), JsonObject.class);
		
		String theme = jsonObject.get("thème").getAsString();
		Integer id = jsonObject.get("id").getAsInt();
		String question = jsonObject.get("question").getAsString();
		JsonArray propositionsArray = jsonObject.getAsJsonArray("propositions");
		String reponse = jsonObject.get("réponse").getAsString();
		String anecdote = jsonObject.get("anecdote").getAsString();

		System.out.println("Thème : " + theme);
		System.out.println("Id : " + id);
		System.out.println("Question : " + question);
		for (JsonElement proposition : propositionsArray) {
		    System.out.println("proposition : " + proposition.getAsString());
		}
		System.out.println("Réponse : " + reponse);
		System.out.println("anecdote  : " + anecdote);
	}
}
