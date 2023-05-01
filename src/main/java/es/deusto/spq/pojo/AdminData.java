package es.deusto.spq.pojo;

public class AdminData {
    private String login;
    private String password;
    
    

    public AdminData(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public AdminData() {
        // required by serialization
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "[login=" + login + ", password=" + password + "]";
    }

}
