package es.deusto.spq.server.jdo;

import java.util.Set;


import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@PersistenceCapable
public class User {
	@PrimaryKey
	String login=null;
	String password=null;
	String correo=null;
	
	@Persistent(mappedBy="user", dependentElement="true")
	@Join
	Set<Message> messages = new HashSet<>();
	
	@Join
	List<Pelicula> peliculas = new ArrayList<>();
	
	public User(String login, String password, String correo) {
		this.login = login;
		this.password = password;
		this.correo = correo;
	}
	


	public void addMessage(Message message) {
		messages.add(message);
	}

	public void removeMessage(Message message) {
		messages.remove(message);
	}
	public void alquilarPelicula(Pelicula pelicula) {
		peliculas.add(pelicula);
	}

	public void devolverPelicula(Pelicula pelicula) {
		peliculas.remove(pelicula);
	}

	public String getLogin() {
		return this.login;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	 public Set<Message> getMessages() {return this.messages;}
	 
	 
	 
	 public List<Pelicula> getPeliculas() {
		return peliculas;
	}
	 
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String toString() {
		StringBuilder messagesStr = new StringBuilder();
		for (Message message: this.messages) {
			messagesStr.append(message.toString() + " - ");
		}
		StringBuilder peliculasStr = new StringBuilder();  
        for (Pelicula pelicula : peliculas) {
        	peliculasStr.append(pelicula.toString() + " - ");
		}
        return "User: login --> " + this.login + ", password -->  " + this.password + ", Correo: "+ this.correo +  ", messages --> [" + messagesStr + "]"+ ", peliculas --> [" + peliculasStr + "]" ;
    }
}
