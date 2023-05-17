package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Alquiler {
    @PrimaryKey
    protected String codPelicula;
    @PrimaryKey
    protected String loginUser;

    public Alquiler(String codPelicula, String loginUser) {
        this.codPelicula = codPelicula;
        this.loginUser = loginUser;
    }
    
    public Alquiler() {
        
    }

    public String getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(String codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public String toString() {
        return "Alquiler [codPelicula=" + codPelicula + ", loginUser=" + loginUser + "]";
    }
}

