package es.deusto.spq.server.jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Alquiler {
	
	
    @PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)   
	protected long codAlquiler;
	protected String codPelicula;
	protected String LoginUser;
	
	public Alquiler(String codPelicula, String loginUser) {
		super();	
		this.codPelicula = codPelicula;
		this.LoginUser = loginUser;
	}

	public String getCodPelicula() {
		return codPelicula;
	}

	public void setCodPelicula(String codPelicula) {
		this.codPelicula = codPelicula;
	}

	public String getLoginUser() {
		return LoginUser;
	}

	public void setLoginUser(String loginUser) {
		LoginUser = loginUser;
	}

	public long getCodAlquiler() {
		return codAlquiler;
	}

	@Override
	public String toString() {
		return "Alquiler [codAlquiler=" + codAlquiler + ", codPelicula=" + codPelicula + ", LoginUser=" + LoginUser
				+ "]";
	}



	
	
}

