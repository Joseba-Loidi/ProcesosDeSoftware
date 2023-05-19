package es.deusto.spq.server.jdo;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Admin {
	@PrimaryKey
	String login=null;
	String password=null;
	
	
	
	public Admin(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Admin() {
		this.login = "";
		this.password = "";
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

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Admin [login=" + login + ", password=" + password + "]";
	}
	
	 
}
