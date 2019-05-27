package com.quiz.demoQuiz.Serveur.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

import com.quiz.demoQuiz.entity.Question;
import com.quiz.demoQuiz.entity.Quizz;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;


public class ParserJson {

	public Quizz jsonparser(String filePath, String niveau) {

		JSONParser parser = new JSONParser();
		Quizz quizz = null;
		try {

			FileReader fileReader = new FileReader("./src/main/resources/json/"+filePath);
			BufferedReader bufferedReader =  new BufferedReader(fileReader);
			StringBuilder string =new StringBuilder();
			String line;
			while((line = bufferedReader.readLine()) != null) {
				string.append(line);
			}

			JSONObject jsonObject =new JSONObject(string.toString());
			String theme = (String) jsonObject.get("thème");
			JSONObject data = jsonObject.getJSONObject("quizz");

			quizz=new Quizz(theme,niveau);
			JSONArray array= data.getJSONArray(niveau);

			for(int i=0; i<array.length();i++) {
				JSONObject element=array.getJSONObject(i);
				int id=element.getInt("id");
				String reponse=element.getString("réponse");
				String question=element.getString("question");
				Question quest= new Question(id,reponse,  question);
				JSONArray propositionsArray =element.getJSONArray("propositions");
				Iterator<Object> propositions = propositionsArray.iterator();
				String anecdote = element.getString("anecdote");
				quest.setAnecdode(anecdote);
				quizz.add(quest);
				System.out.println(quest.toJson());
			}
		} catch (Exception e) { e.printStackTrace(); }
		return quizz;
	}


	public static void main(String[] args) {

		Quizz quizz= new ParserJson().jsonparser("animals.json","débutant");
	}
}
