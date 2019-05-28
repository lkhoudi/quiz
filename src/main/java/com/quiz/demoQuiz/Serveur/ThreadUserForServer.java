package com.quiz.demoQuiz.Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.quiz.demoQuiz.entity.EtatGame;
import com.quiz.demoQuiz.entity.Group;
import com.quiz.demoQuiz.entity.User;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreadUserForServer extends Thread{
	private Serveur serveur;
	private Socket socketUser;
	private BufferedReader reader;
	private PrintWriter writer;
	private User user;
	private Group groupe;
	private EtatGame etatOfUser=new EtatGame();
	
	public ThreadUserForServer(Socket clientServerSocket, Serveur serveur) {
		socketUser=clientServerSocket;
		this.serveur=serveur;
		try{
			reader=new BufferedReader(new InputStreamReader(socketUser.getInputStream()));
			writer= new PrintWriter(socketUser.getOutputStream(),true);
			String reponse=reader.readLine();
			treatMessage(reponse);
			writer.println("bonjour vous venez de vous connecter sur le port "+socketUser.getLocalPort());
		}catch(IOException e) {}
	}

	public void run() {

		int i=0;
		while(i<15){
			try{

				salutation(""+i);
				Thread.sleep(1000);
				i++;

			}
			catch(Exception e){}
			
		}
		writer.println("fin");
		try{
			socketUser.close();
		}
		catch(IOException e){}
		
	}
	
	/**
	 * 
	 */
	public void envoyerAllUsers() {
		sendMessage("users",serveur.getUsersJSON());
	}
	
	/**
	 * 
	 * @param user
	 */
	public void envoyerUser(User user) {
		sendMessage("user",user.toJson());
	}
	
	/**
	 * 
	 * @param message
	 */
	public void envoyer(String message) {
		writer.println(message);
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object object ) {
		if(!(object instanceof ThreadUserForServer)) {
			return false;
		}
		
		ThreadUserForServer threadUser= (ThreadUserForServer) object;
		
		return user.equals(threadUser.user);
	}
	
	/**
	 * 
	 */
	public void informerAutre() throws JSONException {
		sendMessage("users",serveur.getUsersJSONWithout(user));
		for(ThreadUserForServer threadUser : serveur.getListesUsersSocket()) {
			if(!threadUser.equals(this)) {
				threadUser.sendMessage("user",user.toJson());
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public boolean estUser(String username) {
		return user.getUsername().equals(username);
	}
	
	/**
	 * 
	 * @param info
	 */
	public void salutation(String info) throws JSONException {
		JSONObject message= new JSONObject();
		message.put("type", "salutation");
		JSONObject salutation = new JSONObject();
		salutation.put("username", user.getUsername());
		message.put("salutation", salutation);
		message.put("message",info);
		envoyer(message.toString());
	}
	
	public void sendMessage(String type, String message) throws JSONException {
		JSONObject object=new JSONObject();
		object.put("type",type);
		object.put("data", message);
		envoyer(object.toString());
	}

	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public boolean joindreUnGroupe(String data) throws JSONException {
		JSONObject object= new JSONObject(data);
		String labelGroupe=object.getString("label");
		return serveur.ajouterDansGroupe(labelGroupe,this);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public boolean creerGroupe(String data) throws JSONException {
		JSONObject object= new JSONObject(data);
		String label=object.getString("label");
		return serveur.creerGroupe(label,this);
	}
	
	/**
	 * 
	 * @param message
	 */
	public void treatMessage(String message) {
		if((message!=null)&&(!message.equals(""))){
			JSONObject object= new JSONObject(message);
			String type=object.getString("type");
			
			if(type.equals("reponse")) {
				if(etatOfUser.estEnCours()) {
					groupe.setResponse(this, object.getString("reponse"));
				}
			}
			else
			if(type.equals("creerGroupe")) {
				creerGroupe(object.getJSONObject("data").toString());
			}
			else
			if(type.equals("joindreGroupe")) {
				 joindreUnGroupe(object.getJSONObject("data").toString());
			}
			else
			if(type.equals("commencer")) {
				etatOfUser.setEtat("pret");
			}
			else
			if(type.equals("etatConnexion")) {
				
			}
			else
			if(type.equals("inscrire")) {
					inscrire(object.getJSONObject("data").toString());
			}
			else
			if(type.equals("connexion")) {
				
			}
			
			
		}
	}
	
	public void setEtatOfUser(String etat) {
		etatOfUser.setEtat(etat);
	}
	
	public boolean estPret() {
		return etatOfUser.estPret();
	}
	
	public void setGroup(Group group) {
		groupe=group;
	}
	
	public boolean hasGroup() {
		return groupe!=null;
	}
	
	
	public boolean connexion(String jsonString) throws JSONException {
		boolean test=false;
		JSONObject objectUser=new JSONObject(jsonString);
		String email=objectUser.getString("email");
		String password=objectUser.getString("password");
		//verification de la db
		//instanciation de user et serveur puis informerautre
		return test;
	}
	
	public void inscrire(String jsonString) throws JSONException {
		JSONObject objectUser=new JSONObject(jsonString);
		String username=objectUser.getString("username");
		user= new User(username);
		serveur.addUser(user);
		informerAutre();
		//inserer l'utilisateur dans la base de donn�es
	}
}
