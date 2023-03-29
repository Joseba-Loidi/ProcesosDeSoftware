package es.deusto.spq.pojo;

public class UserData {

    private String login;
    private String password;
    private String correo;

    public UserData() {
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

    public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "UserData [login=" + login + ", password=" + password + ", correo=" + correo + "]";
	}
}