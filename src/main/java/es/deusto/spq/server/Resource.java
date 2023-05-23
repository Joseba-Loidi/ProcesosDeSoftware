package es.deusto.spq.server;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;

import es.deusto.spq.pojo.AdminData;
import es.deusto.spq.pojo.AlquilerData;
import es.deusto.spq.pojo.PeliculaData;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.jdo.Admin;
import es.deusto.spq.server.jdo.Alquiler;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;
import es.deusto.spq.server.jdo.User;

import org.apache.logging.log4j.LogManager;

@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

	protected static final Logger logger = LogManager.getLogger();

	private int cont = 0;
	private PersistenceManager pm=null;
	private Transaction tx=null;
	
	private PersistenceManager persistenceManager;

	/**
     * Establece el administrador de persistencia.
     * @param persistenceManager El administrador de persistencia a establecer.
     */
	
    public void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }
    
    /**
     * Constructor de la clase Resource.
     * Crea un PersistenceManager y una Transaction.
     */

	public Resource() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}
	
	@POST
	@Path("/register")
	/**
     * Maneja una solicitud POST para registrar un usuario.
     * @param userData Los datos del usuario enviados en el cuerpo de la solicitud.
     * @return Una respuesta HTTP indicando el resultado de la operación.
     */
	public Response registerUser(UserData userData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the user already exits or not: '{}'", userData.getLogin());
			User user = null;
			try {
				user = pm.getObjectById(User.class, userData.getLogin());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("User: {}", user);
			if (user != null) {
				logger.info("Setting password user: {}", user);
				user.setPassword(userData.getPassword());
				logger.info("Setting email user: {}", user);
				user.setCorreo(userData.getCorreo());
				logger.info("Password and Email set user: {}", user);
			} else {
				logger.info("Creating user: {}", user);
				user = new User(userData.getLogin(), userData.getPassword(), userData.getCorreo());
				pm.makePersistent(user);					 
				logger.info("User created: {}", user);
			}
			tx.commit();
			return Response.ok().build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}
	
	///////////////////////////////////////
	@POST
	@Path("/adminRegister")
	/**
     * Maneja una solicitud POST para registrar un administrador.
     * @param adminData Los datos del administrador enviados en el cuerpo de la solicitud.
     * @return Una respuesta HTTP indicando el resultado de la operación.
     */
	public Response registerAdmin(AdminData adminData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the user already exits or not: '{}'", adminData.getLogin());
			Admin admin = null;
			try {
				admin = pm.getObjectById(Admin.class, adminData.getLogin());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("Admin: {}", admin);
			if (admin != null) {
				logger.info("Setting password admin: {}", admin);
				admin.setPassword(adminData.getPassword());
				logger.info("Password set admin: {}", admin);
			} else {
				logger.info("Creating admin: {}", admin);
				admin = new Admin(adminData.getLogin(), adminData.getPassword());
				pm.makePersistent(admin);					 
				logger.info("User created: {}", admin);
			}
			tx.commit();
			return Response.ok().build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}
	
	////////////AÑADIR PELICULA/////////////
	@POST
	@Path("/addPelicula")
	/**
     * Maneja una solicitud POST para agregar una película.
     * @param peliculaData Los datos de la película enviados en el cuerpo de la solicitud.
     * @return Una respuesta HTTP indicando el resultado de la operación.
     */
	public Response addPelicula(PeliculaData peliculaData) {
		try
		{
			tx.begin();
			logger.info("Checking whether the film already exits or not: '{}'", peliculaData.getCodigo());
			Pelicula peli = null;
			try {
				peli = pm.getObjectById(Pelicula.class, peliculaData.getCodigo());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("Pelicula: {}", peli);
			if (peli != null) {
				logger.info("Setting titulo pelicula: {}", peli);
				peli.setTitulo(peliculaData.getTitulo());
				logger.info("titulo set pelicula: {}", peli);
				logger.info("Setting minutos pelicula: {}", peli);
				peli.setMinutos(peliculaData.getMinutos());
				logger.info("Minutos set pelicula: {}", peli);
				logger.info("Setting valoracion pelicula: {}", peli);
				peli.setValoracion(peliculaData.getValoracion());
				logger.info("Valoracion set pelicula: {}", peli);
				logger.info("Setting genero pelicula: {}", peli);
				peli.setGenero(peliculaData.getGenero());
				logger.info("Genero set pelicula: {}", peli);
			} else {
				logger.info("Creating film: {}", peli);
				peli = new Pelicula(peliculaData.getCodigo(), peliculaData.getTitulo(), peliculaData.getMinutos(), peliculaData.getValoracion(), peliculaData.getGenero());
				pm.makePersistent(peli);
				logger.info("Film created: {}", peli);
			}
			tx.commit();
			return Response.ok().build();
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}

		}
	}
	
	/**
     * Maneja una solicitud DELETE para eliminar una película.
     * @param codigo El código de la película proporcionado en la URL.
     * @return Una respuesta HTTP indicando el resultado de la operación.
     */
	
	@DELETE
    @Path("/{codigo}")
    public Response deleteFilm(@PathParam("codigo") String codigo) {
		try{
			tx.begin();
			logger.info("Checking whether the film already exits or not: '{}'", codigo);
			Pelicula peli = null;
			try {
				peli = pm.getObjectById(Pelicula.class, codigo);
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("Pelicula a eliminar: {}", peli);
			if (peli == null) {
				return Response.status(Response.Status.NOT_FOUND).build();
			} else {
				logger.info("Deleting user {} ...", peli);
				pm.deletePersistent(peli);
				tx.commit();
			    return Response.status(Response.Status.OK).build();
			}
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}

		}
    }
	/**
     * Obtiene una lista de todas las películas.
     * @return Una lista de objetos Pelicula que representa todas las películas almacenadas.
     */
	
	@POST
	@Path("/getPeliculas")
	public List<Pelicula> obtenerPeliculas() {
		List<Pelicula> peliculas = new ArrayList<>();
		try{
			tx.begin();
			logger.info("Creating query ...");
			Extent<Pelicula> peliExtent = pm.getExtent(Pelicula.class, true);	
			for (Pelicula peli : peliExtent) {
				peliculas.add(peli);
				logger.info("Film retrieved: {}", peli);
			}			
			tx.commit();	
		} catch (Exception ex) {
			System.out.println("  $ Error querying all films: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return peliculas;
	}

	/**
     * Obtiene una lista de todos los usuarios.
     * @return Una lista de objetos User que representa todos los usuarios almacenados.
     */
	
	@POST
	@Path("/getUsuarios")
	public List<User> obtenerUsuarios() {
		List<User> usuarios = new ArrayList<>();
		try{
			tx.begin();
			logger.info("Creating query ...");
			Extent<User> usuExtent = pm.getExtent(User.class, true);	
			for (User usu : usuExtent) {
				usuarios.add(usu);
				logger.info("User retrieved: {}", usu);
			}			
			tx.commit();	
		} catch (Exception ex) {
			System.out.println("  $ Error querying all films: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return usuarios;
	}
	
	/**
     * Realiza el inicio de sesión de un usuario.
     * @param userData Objeto UserData que contiene los datos de inicio de sesión del usuario.
     * @return Una respuesta que indica si el inicio de sesión fue exitoso o no.
     */
	
	@POST
	@Path("/login")
	public Response login(UserData userData) {
		User user = null;
		try{
			tx.begin();
			logger.info("Creating query ...");
			
			try (Query<?> q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == \"" + userData.getLogin() + "\" &&  password == \"" + userData.getPassword() + "\"")) {
				q.setUnique(true);
				user = (User)q.execute();				
				logger.info("User retrieved: {}", user);
				
			} catch (Exception e) {
				e.printStackTrace();
				 tx.rollback();
				 logger.error("Error while logging in: {}", e.getMessage());
				 return Response.status(Status.INTERNAL_SERVER_ERROR).entity("An error occurred while logging in").build();
			}
			tx.commit();
			
		}	finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}

		}
		
		if (user != null) {
			  logger.info("El usuario existe: {}", user);   
			//return Response.status(Status.OK).entity("Login details supplied are correct").build();
			return Response.ok().build();
			
		} else {
			 return Response.status(Status.UNAUTHORIZED).entity("Invalid login credentials").build();
		}
		
		
	}
	
	/**
     * Realiza el inicio de sesión de un administrador.
     * @param adminData Objeto AdminData que contiene los datos de inicio de sesión del administrador.
     * @return Una respuesta que indica si el inicio de sesión fue exitoso o no.
     */
	
	@POST
	@Path("/loginAdmin")
	public Response loginAdmin(AdminData adminData) {
		Admin admin = null;
		try{
			tx.begin();
			logger.info("Creating query ...");
			
			try (Query<?> q = pm.newQuery("SELECT FROM " + Admin.class.getName() + " WHERE login == \"" + adminData.getLogin() + "\" &&  password == \"" + adminData.getPassword() + "\"")) {
				q.setUnique(true);
				admin = (Admin)q.execute();				
				logger.info("User retrieved: {}", admin);
				
			} catch (Exception e) {
				e.printStackTrace();
				 tx.rollback();
				 logger.error("Error while logging in: {}", e.getMessage());
				 return Response.status(Status.INTERNAL_SERVER_ERROR).entity("An error occurred while logging in").build();
			}
			tx.commit();
			
		}	finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}

		}
		
		if (admin != null) {
			  logger.info("El usuario existe: {}", admin);   
			//return Response.status(Status.OK).entity("Login details supplied are correct").build();
			return Response.ok().build();
			
		} else {
			 return Response.status(Status.UNAUTHORIZED).entity("Invalid login credentials").build();
		}
		
		
	}
	
	/**
     * Filtra una película por su nombre.
     * @param nombre El nombre de la película a filtrar.
     * @return La película que coincide con el nombre especificado.
     */
	
	@POST
	@Path("/filtrarNombre")
	public Pelicula filtrarNombre(String nombre) {
		Pelicula pelicula = null;
		//List<Pelicula> peliculas = new ArrayList<>();
		try{
			tx.begin();
			logger.info("Filtrar Pelicula por nombre ...");
			try (Query<?> q = pm.newQuery("SELECT FROM " + Pelicula.class.getName() + " WHERE titulo == \"" + nombre + "\"")) {
				q.setUnique(true);
				pelicula = (Pelicula)q.execute();				
				logger.info("Pelicula retrieved: {}", pelicula);
				
			} catch (Exception e) {
				e.printStackTrace();
				 tx.rollback();
				 logger.error("Error while logging in: {}", e.getMessage());
				// return Response.status(Status.INTERNAL_SERVER_ERROR).entity("An error occurred while logging in").build();
			}
			
			tx.commit();	
		} catch (Exception ex) {
			System.out.println("  $ Error querying all films: " + ex.getMessage());
		} finally {
			if (tx.isActive())
			{
				tx.rollback();
			}
		}
		return pelicula;
	}
	
	@POST
	@Path("/getLogin")
	public User getLogin(String nombre) {
		User u = null;
		try{
			tx.begin();
			logger.info("Buscando Usuario ...");
			try (Query<?> q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == \"" + nombre + "\"")) {
				q.setUnique(true);
				u = (User)q.execute();				
				logger.info("User retrieved: {}", u);
				
			} catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
				logger.error("Error while logging in: {}", e.getMessage());
				
			}
			
			tx.commit();	
		} catch (Exception ex) {
			System.out.println("  $ Error querying all films: " + ex.getMessage());
		} finally {
			if (tx.isActive())
			{
				tx.rollback();
			}
		}
		return u;
	}
	
	/**
	 * Filtra películas por género.
	 * @param genero El género por el que se desea filtrar las películas.
	 * @return Una lista de películas que pertenecen al género especificado.
	 */
	
	@POST
	@Path("/filtrarGenero")
	public List<Pelicula> filtrarGenero(Genero genero) {
		List<Pelicula> peliculas = new ArrayList<>();
		try{
			tx.begin();
			logger.info("Filtrar Pelicula por Genero ...");
			Extent<Pelicula> peliExtent = pm.getExtent(Pelicula.class, true);	
			for (Pelicula peli : peliExtent) {
				if(peli.getGenero().equals(genero)) {
					peliculas.add(peli);
					logger.info("Film retrieved: {}", peli); 
				}
			}
			
			tx.commit();	
		} catch (Exception ex) {
			System.out.println("  $ Error querying all films: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return peliculas;
	}
	/**
	 * Filtra películas por valoración.
	 * @param valoracion La valoración por la que se desea filtrar las películas.
	 * @return Una lista de películas que tienen la valoración especificada.
	 */
	
	@POST
	@Path("/filtrarValoracion")
	public List<Pelicula> filtrarValoracion(int valoracion) {
		List<Pelicula> peliculas = new ArrayList<>();
		try{
			tx.begin();
			logger.info("Filtrar Pelicula por Valoracion ...");
			Extent<Pelicula> peliExtent = pm.getExtent(Pelicula.class, true);	
			for (Pelicula peli : peliExtent) {
				if(peli.getValoracion()==(valoracion)) {
					peliculas.add(peli);
					logger.info("Film retrieved: {}", peli); 
				}
			}
			
			tx.commit();	
		} catch (Exception ex) {
			System.out.println("  $ Error querying all films: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return peliculas;
	}
	
	/**
	 * Filtra películas por usuario.
	 * @param user El nombre de usuario por el que se desea filtrar las películas.
	 * @return Una lista de películas que han sido alquiladas por el usuario especificado.
	 */
	@POST
	@Path("/filtrarUsuario")
	public List<Pelicula> filtrarUsuario(String user) {
		logger.info("Vamos a empezar el filtro usuario");
		
		List<Pelicula> peliculas = new ArrayList<>();
		List<String> codigosPeliculas = new ArrayList<>();
		
		
		try{
			tx.begin();
			logger.info("Filtrar Pelicula por Usuario ...");
			Extent<Pelicula> peliExtent = pm.getExtent(Pelicula.class, true);
			Extent<Alquiler> alquilerExtent = pm.getExtent(Alquiler.class, true);
			for (Alquiler alquiler : alquilerExtent) {
				if (alquiler.getLoginUser().equals(user)) {
					codigosPeliculas.add(alquiler.getCodPelicula());
				}
			}
			
			for (String c : codigosPeliculas) {
				for (Pelicula pelicula : peliExtent) {
					if (c.equals(pelicula.getCodigo())) {
						peliculas.add(pelicula);
						logger.info("Film retrieved: {}", pelicula);
					}
				}
			}
			
			tx.commit();	
		} catch (Exception ex) {
			System.out.println("  $ Error querying all films: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		return peliculas;
	}
	
	/**
	 * Elimina un usuario.
	 * @param login El nombre de usuario del usuario que se desea eliminar.
	 * @return Una respuesta indicando el resultado de la eliminación.
	 */
	
	@POST
	@Path("/deleteUser")
	public Response deleteUser(String login) {
		
		try {
		    tx.begin();
		    User user = pm.getObjectById(User.class, login);
		    pm.deletePersistent(user);
		    tx.commit();
		} catch (Exception e) {
		    e.printStackTrace();
		    tx.rollback();
		    return Response.status(Status.INTERNAL_SERVER_ERROR).entity("An error occurred while deleting user").build();
		}
		return Response.ok().build();
			
	}
	
	/**
	 * Elimina un alquiler.
	 * @param codPelicula El código de la película asociada al alquiler que se desea eliminar.
	 * @param loginUser El nombre de usuario asociado al alquiler que se desea eliminar.
	 * @return Una respuesta indicando el resultado de la eliminación.
	 */
	
	@DELETE
	@Path("/borrarAlquiler/{codPelicula}/{loginUser}")
	public Response borrarAlquiler(@PathParam("codPelicula") String codPelicula, @PathParam("loginUser") String loginUser) {
	    try {
	        tx.begin();
	        
	        Query query = pm.newQuery(Alquiler.class, "codPelicula == :codPelicula && loginUser == :loginUser");
	        query.deletePersistentAll(codPelicula, loginUser);
	        
	        tx.commit();
	        return Response.ok().build();
	    } catch (Exception e) {
	        if (tx.isActive()) {
	            tx.rollback();
	        }
	        logger.error("Error deleting alquiler: {}", e.getMessage());
	        return Response.serverError().build();
	    }
	}

	
	/**
	 * Crea un nuevo alquiler.
	 * @param alquilerData Los datos del alquiler que se desea crear.
	 * @return Una respuesta indicando el resultado de la creación del alquiler.
	 */
	@POST
    @Path("/crearAlquiler")
    public Response crearAlquiler(AlquilerData alquilerData) {
		try {
            tx.begin();
            logger.info("Checking whether the film already exists or not: '{}'", alquilerData.getCodPelicula());
            logger.info("Checking whether the user already exists or not: '{}'", alquilerData.getLoginUser());
            
            Alquiler alquiler = new Alquiler(alquilerData.getCodPelicula(), alquilerData.getLoginUser());
            pm.makePersistent(alquiler);
            logger.info("Alquiler created: {}", alquiler);
            
            tx.commit();
            return Response.ok().build();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            logger.error("Error creating alquiler: {}", e.getMessage());
            return Response.serverError().build();
        }    
	}
	

//	@POST
//	@Path("/crearAlquiler")
//	public Response crearAlquiler(AlquilerData alquilerData) {
//	   try {
//	       tx.begin();
//	       logger.info("Checking whether the alquiler already exists or not: '{}'", alquilerData.getCodPelicula() + alquilerData.getLoginUser());
//	       Alquiler alquiler = null;
//	       try {
//	           Query query = pm.newQuery(Alquiler.class);
//	           query.setFilter("codPelicula == pelicula && loginUser == usuario");
//	           query.declareParameters("String pelicula, String usuario");
//	           List<Alquiler> resultados = (List<Alquiler>) query.execute(alquilerData.getCodPelicula(), alquilerData.getLoginUser());
//	           if (resultados.size() > 0) {
//	               alquiler = resultados.get(0);
//	           }
//	       } catch (Exception e) {
//	           logger.info("Exception launched: {}", e.getMessage());
//	       }
//	       logger.info("Alquiler: {}", alquiler);
//	       if (alquiler != null) {
//	           logger.info("Updating alquiler: {}", alquiler);
//	           alquiler.setLoginUser(alquilerData.getLoginUser());
//	           alquiler.setCodPelicula(alquilerData.getCodPelicula());
//	           logger.info("Alquiler updated: {}", alquiler);
//	       } else {
//	           logger.info("Creating alquiler: {}", alquiler);
//	           alquiler = new Alquiler(alquilerData.getCodPelicula(), alquilerData.getLoginUser());
//	           pm.makePersistent(alquiler);
//	           logger.info("Alquiler created: {}", alquiler);
//	       }
//	       tx.commit();
//	       return Response.ok().build();
//	   } finally {
//	       if (tx.isActive()) {
//	           tx.rollback();
//	       }
//	   }
//	}

	
}
