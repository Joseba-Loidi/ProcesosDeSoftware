package es.deusto.spq.pojo;

public class AlquilerData {
protected String codPelicula;
protected String LoginUser;

public AlquilerData(String codPelicula, String loginUser) {
super();
this.codPelicula = codPelicula;
this.LoginUser = loginUser;
}

public AlquilerData() {

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


@Override
public String toString() {
return "Alquiler [codPelicula=" + codPelicula + ", LoginUser=" + LoginUser
+ "]";
}

}