package es.deusto.spq.server;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.Transaction;
import javax.ws.rs.DELETE;
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
import es.deusto.spq.pojo.PeliculaData;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.jdo.Admin;
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

	public Resource() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}

	@POST
	@Path("/register")
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
	public Response registerAdmin(PeliculaData peliculaData) {
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
	
	@DELETE
    @Path("/{codigo}")
    public Response deleteFilm(@PathParam("codigo") String codigo) {
		try{
			System.out.println("HOliii");
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

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHello() {
		return Response.ok("Hello world!").build();
	}
}
