package es.deusto.spq.server.server;


import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.client.Cliente;
import es.deusto.spq.pojo.AdminData;
import es.deusto.spq.pojo.AlquilerData;
import es.deusto.spq.pojo.PeliculaData;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.Resource;
import es.deusto.spq.server.jdo.Admin;
import es.deusto.spq.server.jdo.Alquiler;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;
import es.deusto.spq.server.jdo.User;


public class ResourceTest {
	
    private Resource resource;
    
    private User mockUser;

    @Mock
    private PersistenceManager persistenceManager;

    @Mock
    private Transaction transaction;
    @Mock
    private Query<User> query;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // initializing static mock object PersistenceManagerFactory
        try (MockedStatic<JDOHelper> jdoHelper = Mockito.mockStatic(JDOHelper.class)) {
            PersistenceManagerFactory pmf = mock(PersistenceManagerFactory.class);
            jdoHelper.when(() -> JDOHelper.getPersistenceManagerFactory("datanucleus.properties")).thenReturn(pmf);
            
            when(pmf.getPersistenceManager()).thenReturn(persistenceManager);
            when(persistenceManager.currentTransaction()).thenReturn(transaction);

            // instantiate tested object with mock dependencies
            resource = new Resource();
        }
        //creamos un usuario
     // Create a mock user for testing
        mockUser = mock(User.class);
        when(mockUser.getLogin()).thenReturn("username");
        when(mockUser.getPassword()).thenReturn("password");
        when(mockUser.getCorreo()).thenReturn("correo");
        
        resource.setPersistenceManager(persistenceManager);
    }
    @Test
    public void testRegisterUser() {
        // prepare mock Persistence Manager to return User
        UserData userData = new UserData();
        userData.setLogin("test-login");
        userData.setPassword("passwd");
        userData.setCorreo("email");

        // simulate that 
        User user = spy(User.class);
        when(persistenceManager.getObjectById(User.class, userData.getLogin())).thenReturn(user);

        // call tested method
        Response response = resource.registerUser(userData);

        // check that the user is set by the code with the password
        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        verify(user).setPassword(passwordCaptor.capture());
        assertEquals("passwd", passwordCaptor.getValue());
        
        // check that the user is set by the code with the email
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(user).setCorreo(emailCaptor.capture());
        assertEquals("email", emailCaptor.getValue());

        // check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
    
    @Test
    public void testRegisterUserNotFound() {
        // prepare mock Persistence Manager to return User
        UserData userData = new UserData();
        userData.setLogin("test-login");
        userData.setPassword("passwd");
        userData.setCorreo("email");

        // simulate that the object is not found in the database
        when(persistenceManager.getObjectById(any(), anyString())).thenThrow(new JDOObjectNotFoundException());

        // prepare mock transaction behaviour
        when(transaction.isActive()).thenReturn(true);

        // call tested method
        Response response = resource.registerUser(userData);

        // check that the new user is stored in the database with the correct values
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(persistenceManager).makePersistent(userCaptor.capture());
        assertEquals("test-login", userCaptor.getValue().getLogin());
        assertEquals("passwd", userCaptor.getValue().getPassword());
        assertEquals("email", userCaptor.getValue().getCorreo());

        // check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
    @Test
    public void testRegisterAdmin() {
        // prepare mock Persistence Manager to return User
        AdminData adminData = new AdminData();
        adminData.setLogin("test-login");
        adminData.setPassword("passwd");
       

        // simulate that 
        Admin admin = spy(Admin.class);
        when(persistenceManager.getObjectById(Admin.class, adminData.getLogin())).thenReturn(admin);

        // call tested method
        Response response = resource.registerAdmin(adminData);

        // check that the user is set by the code with the password
        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        verify(admin).setPassword(passwordCaptor.capture());
        assertEquals("passwd", passwordCaptor.getValue());

        // check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
    @Test
    public void testRegisterAdminNotFound() {
        // prepare mock Persistence Manager to return User
        AdminData adminData = new AdminData();
        adminData.setLogin("adminL");
        adminData.setPassword("AdminP");

        // simulate that the object is not found in the database
        when(persistenceManager.getObjectById(any(), anyString())).thenThrow(new JDOObjectNotFoundException());

        // prepare mock transaction behaviour
        when(transaction.isActive()).thenReturn(true);

        // call tested method
        Response response = resource.registerAdmin(adminData);

        // check that the new user is stored in the database with the correct values
        ArgumentCaptor<Admin> adminCaptor = ArgumentCaptor.forClass(Admin.class);
        verify(persistenceManager).makePersistent(adminCaptor.capture());
        assertEquals("adminL", adminCaptor.getValue().getLogin());
        assertEquals("AdminP", adminCaptor.getValue().getPassword());

        // check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
    
    @Test
    public void testAddPelicula() {
        // prepare mock Persistence Manager to return Pelicula
        PeliculaData peliculaData = new PeliculaData();
        peliculaData.setCodigo("123456");
        peliculaData.setTitulo("Titanic");
        peliculaData.setMinutos(194);
        peliculaData.setValoracion(8);
        peliculaData.setGenero(Genero.DRAMA);

        // simulate that the object is not found in the database
        when(persistenceManager.getObjectById(any(), anyString())).thenThrow(new JDOObjectNotFoundException());

        // prepare mock transaction behaviour
        when(transaction.isActive()).thenReturn(true);

        // call tested method
        Response response = resource.addPelicula(peliculaData);

        // check that the new pelicula is stored in the database with the correct values
        ArgumentCaptor<Pelicula> peliculaCaptor = ArgumentCaptor.forClass(Pelicula.class);
        verify(persistenceManager).makePersistent(peliculaCaptor.capture());
        assertEquals("123456", peliculaCaptor.getValue().getCodigo());
        assertEquals("Titanic", peliculaCaptor.getValue().getTitulo());
        assertEquals(194, peliculaCaptor.getValue().getMinutos());
        assertEquals(8, peliculaCaptor.getValue().getValoracion());
        assertEquals(Genero.DRAMA, peliculaCaptor.getValue().getGenero());

        // check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
    @Test
    public void testAddPelicula_PeliculaExistente() {
        // prepare mock Persistence Manager to return existing Pelicula
        Pelicula peliculaExistente = new Pelicula("123456", "Anterior", 120, 7, Genero.ACCION);
        when(persistenceManager.getObjectById(Pelicula.class, "123456")).thenReturn(peliculaExistente);

        // prepare mock transaction behaviour
        when(transaction.isActive()).thenReturn(true);

        // prepare input PeliculaData
        PeliculaData peliculaData = new PeliculaData();
        peliculaData.setCodigo("123456");
        peliculaData.setTitulo("Titanic");
        peliculaData.setMinutos(194);
        peliculaData.setValoracion(8);
        peliculaData.setGenero(Genero.DRAMA);

        // call tested method
        Response response = resource.addPelicula(peliculaData);

        // verify that the existing pelicula is updated with the new values
        assertEquals("Titanic", peliculaExistente.getTitulo());
        assertEquals(194, peliculaExistente.getMinutos());
        assertEquals(8, peliculaExistente.getValoracion());
        assertEquals(Genero.DRAMA, peliculaExistente.getGenero());

        // check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
    
    @Test
    public void testCrearAlquiler() {
        // prepare mock Persistence Manager to return alquiler
        AlquilerData alquilerData = new AlquilerData();
        alquilerData.setCodPelicula("AABB");;
        alquilerData.setLoginUser("123");

        // simulate that the object is not found in the database
        when(persistenceManager.getObjectById(any(), anyString())).thenThrow(new JDOObjectNotFoundException());

        // prepare mock transaction behaviour
        when(transaction.isActive()).thenReturn(true);

        // call tested method
        Response response = resource.crearAlquiler(alquilerData);

        // check that the new pelicula is stored in the database with the correct values
        ArgumentCaptor<Alquiler> alquilerCaptor = ArgumentCaptor.forClass(Alquiler.class);
        verify(persistenceManager).makePersistent(alquilerCaptor.capture());
        assertEquals("AABB", alquilerCaptor.getValue().getCodPelicula());
        assertEquals("123", alquilerCaptor.getValue().getLoginUser());

        // check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
    
    
    @Test
    public void testDeleteFilm() {
        // prepare mock Persistence Manager to return Pelicula
        Pelicula pelicula = new Pelicula("1", "The Godfather", 175, 9, Genero.DRAMA);
        when(persistenceManager.getObjectById(Pelicula.class, "1")).thenReturn(pelicula);

        // prepare mock transaction behaviour
        when(transaction.isActive()).thenReturn(true);

        // call tested method
        Response response = resource.deleteFilm("1");

        // check that the pelicula is deleted from the database
        verify(persistenceManager).deletePersistent(pelicula);

        // check expected response
        assertEquals(Response.Status.OK, response.getStatusInfo());

        // simulate that the pelicula is not found in the database
        when(persistenceManager.getObjectById(Pelicula.class, "1")).thenThrow(new JDOObjectNotFoundException());

        // call tested method again
        response = resource.deleteFilm("1");

        // check expected response
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());
    }
    
    @Test
    public void testFiltrarValoracion() {
        // prepare mock Persistence Manager to return Pelicula instances
        ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
        peliculas.add(new Pelicula("COD-001", "The Matrix", 136, 8, Genero.ACCION));
        peliculas.add(new Pelicula("COD-002", "Pulp Fiction", 154, 9, Genero.ACCION));
        peliculas.add(new Pelicula("COD-003", "Forrest Gump", 142, 9, Genero.DRAMA));

        Extent<Pelicula> peliExtent = mock(Extent.class);
        when(persistenceManager.getExtent(Pelicula.class, true)).thenReturn(peliExtent);
        when(peliExtent.iterator()).thenReturn(peliculas.iterator());

        // call tested method
        java.util.List<Pelicula> result = resource.filtrarValoracion(9);

        // check expected result
        assertEquals(2, result.size());

        // check that the result contains the expected Pelicula instances
        Pelicula resultPeli1 = result.get(0);
        assertEquals("COD-002", resultPeli1.getCodigo());
        assertEquals("Pulp Fiction", resultPeli1.getTitulo());
        assertEquals(154, resultPeli1.getMinutos());
        assertEquals(9, resultPeli1.getValoracion());
        assertEquals(Genero.ACCION, resultPeli1.getGenero());

        Pelicula resultPeli2 = result.get(1);
        assertEquals("COD-003", resultPeli2.getCodigo());
        assertEquals("Forrest Gump", resultPeli2.getTitulo());
        assertEquals(142, resultPeli2.getMinutos());
        assertEquals(9, resultPeli2.getValoracion());
        assertEquals(Genero.DRAMA, resultPeli2.getGenero());
    }
    
    @Test
    public void testFiltrarGenero() {
        // prepare mock Persistence Manager to return Pelicula instances
        ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
        peliculas.add(new Pelicula("COD-001", "The Matrix", 136, 8, Genero.ACCION));
        peliculas.add(new Pelicula("COD-002", "Pulp Fiction", 154, 9, Genero.ACCION));
        peliculas.add(new Pelicula("COD-003", "Forrest Gump", 142, 9, Genero.DRAMA));

        Extent<Pelicula> peliExtent = mock(Extent.class);
        when(persistenceManager.getExtent(Pelicula.class, true)).thenReturn(peliExtent);
        when(peliExtent.iterator()).thenReturn(peliculas.iterator());

        // call tested method
        java.util.List<Pelicula> result = resource.filtrarGenero(Genero.DRAMA);

        // check expected result
        assertEquals(1, result.size());

        // check that the result contains the expected Pelicula instances
        Pelicula resultPeli1 = result.get(0);
        assertEquals("COD-003", resultPeli1.getCodigo());
        assertEquals("Forrest Gump", resultPeli1.getTitulo());
        assertEquals(142, resultPeli1.getMinutos());
        assertEquals(9, resultPeli1.getValoracion());
        assertEquals(Genero.DRAMA, resultPeli1.getGenero());

    }
    
    @Test
    public void testFiltrarGeneroError() {
        // prepare mock Persistence Manager to return Pelicula instances
        ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
        peliculas.add(new Pelicula("COD-001", "The Matrix", 136, 8, Genero.ACCION));
        peliculas.add(new Pelicula("COD-002", "Pulp Fiction", 154, 9, Genero.ACCION));
        peliculas.add(new Pelicula("COD-003", "Forrest Gump", 142, 9, Genero.DRAMA));

        Extent<Pelicula> peliExtent = mock(Extent.class);
        when(persistenceManager.getExtent(Pelicula.class, true)).thenReturn(peliExtent);
        when(peliExtent.iterator()).thenReturn(peliculas.iterator());

        // call tested method
        java.util.List<Pelicula> result = resource.filtrarGenero(Genero.COMEDIA);

        // check expected result
        assertEquals(0, result.size());
    }
    
    
    @Test
    public void testDeleteUser() {
        // prepare mock Persistence Manager to return User instance
        User user = new User("testUser", "testPassword", "testEmail");
        when(persistenceManager.getObjectById(User.class, "testUser")).thenReturn(user);

        // call tested method
        Response result = resource.deleteUser("testUser");

        // check that the user has been deleted
        verify(persistenceManager).deletePersistent(user);
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
    }
    

    

    
    @Test
    public void testBorrarAlquiler_WhenExceptionOccurs_ReturnsErrorResponse() {
        // Configura el PersistenceManager para lanzar una excepción al eliminar un alquiler
        doThrow(new RuntimeException("Simulated exception")).when(persistenceManager).deletePersistent(any(Alquiler.class));

        // Establece el PersistenceManager simulado en el recurso
        resource.setPersistenceManager(persistenceManager);

        // Llama al método deleteAlquiler con valores válidos de codPelicula y loginUser
        Response result = resource.borrarAlquiler("testCodPelicula", "testLoginUser");

        // Verifica que se devuelva una respuesta HTTP INTERNAL_SERVER_ERROR (código 500)
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), result.getStatus());
    }
    
    @Test
    public void testBorrarAlquiler() {
        // Mocking input parameters
        String codPelicula = "ABC123";
        String loginUser = "john.doe";

        // Mocking the PersistenceManager and Query objects
        Query queryMock = mock(Query.class);
        when(persistenceManager.newQuery(Alquiler.class, "codPelicula == :codPelicula && loginUser == :loginUser")).thenReturn(queryMock);

        // Mocking the transaction
        when(persistenceManager.currentTransaction()).thenReturn(transaction);

        // Mocking the query result
        when(queryMock.deletePersistentAll(codPelicula, loginUser)).thenReturn((long) 1);

        // Calling the method under test
        Response response = resource.borrarAlquiler(codPelicula, loginUser);

        // Verifying the behavior and assertions
        verify(transaction).begin();
        verify(transaction).commit();
        verify(queryMock).deletePersistentAll(codPelicula, loginUser);
        verify(transaction, never()).rollback();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }


    
    @Test
    public void testFiltrarNombre() throws Exception {
        // prepare mock Persistence Manager to return Pelicula instances
        Pelicula pelicula = new Pelicula("COD-001", "The_Matrix", 136, 8, Genero.ACCION);

        Query<?> query = mock(Query.class);
        when(persistenceManager.newQuery("SELECT FROM " + Pelicula.class.getName() + " WHERE titulo == \"" + pelicula.getTitulo() + "\"")).thenReturn(query);
       // when(query.setUnique(true)).thenReturn(query);
        when(query.execute()).thenReturn(pelicula);

        // call tested method
        Pelicula result = resource.filtrarNombre("The_Matrix");

        // check that the result contains the expected Pelicula instance
        assertEquals(pelicula.getCodigo(), result.getCodigo());
        assertEquals(pelicula.getTitulo(), result.getTitulo());
        assertEquals(pelicula.getMinutos(), result.getMinutos());
        assertEquals(pelicula.getValoracion(), result.getValoracion());
        assertEquals(pelicula.getGenero(), result.getGenero());
    }
    
    
    
    @Test
    public void testLogin() {
    	// Crear un objeto UserData simulado con los datos de inicio de sesión
        UserData userData = new UserData();
        userData.setLogin("username");
        userData.setPassword("password");
        userData.setCorreo("email");

        // Crear un objeto User simulado
        User mockUser = mock(User.class);
        when(mockUser.getLogin()).thenReturn("username");
        when(mockUser.getPassword()).thenReturn("password");
        when(mockUser.getCorreo()).thenReturn("email");

        // Configurar comportamiento simulado para el PersistenceManager
        when(persistenceManager.newQuery(anyString())).thenReturn(query);
      //  when(query.setUnique(true)).thenReturn(query);
        when(query.execute()).thenReturn(mockUser);

        // Llamar al método de login
        Response response = resource.login(userData);

        // Verificar que se haya llamado a los métodos necesarios
        verify(persistenceManager).newQuery(anyString());
        verify(query).setUnique(true);
        verify(query).execute();
        verify(transaction).commit();

        // Verificar el resultado de la respuesta
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void testLoginIncorrecto() {
    	// Crear un objeto UserData simulado con los datos de inicio de sesión incorrectos
        UserData userData = new UserData();
        userData.setLogin("username");
        userData.setPassword("passwordError");
        userData.setCorreo("email");

        // Configurar comportamiento simulado para el PersistenceManager
        when(persistenceManager.newQuery(anyString())).thenReturn(query);
       // when(query.setUnique(true)).thenReturn(query);
        when(query.execute()).thenReturn(null);
        
     // Configurar comportamiento simulado para el Transaction
        when(transaction.isActive()).thenReturn(true);

        // Llamar al método de login
        Response response = resource.login(userData);

        // Verificar que se haya llamado a los métodos necesarios
        verify(persistenceManager).newQuery(anyString());
        verify(query).setUnique(true);
        verify(query).execute();
        verify(transaction).rollback();

        // Verificar el resultado de la respuesta
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void testLoginAdmin() {
    	// Crear un objeto UserData simulado con los datos de inicio de sesión
        AdminData adminData = new AdminData();
        adminData.setLogin("username");
        adminData.setPassword("password");


        // Crear un objeto User simulado
        Admin mockUser = mock(Admin.class);
        when(mockUser.getLogin()).thenReturn("username");
        when(mockUser.getPassword()).thenReturn("password");

        // Configurar comportamiento simulado para el PersistenceManager
        when(persistenceManager.newQuery(anyString())).thenReturn(query);
      //  when(query.setUnique(true)).thenReturn(query);
        when(query.execute()).thenReturn(mockUser);

        // Llamar al método de login
        Response response = resource.loginAdmin(adminData);

        // Verificar que se haya llamado a los métodos necesarios
        verify(persistenceManager).newQuery(anyString());
        verify(query).setUnique(true);
        verify(query).execute();
        verify(transaction).commit();

        // Verificar el resultado de la respuesta
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void testLoginAdminIncorrecto() {
    	// Crear un objeto UserData simulado con los datos de inicio de sesión incorrectos
        AdminData adminData = new AdminData();
        adminData.setLogin("username");
        adminData.setPassword("passwordError");

        // Configurar comportamiento simulado para el PersistenceManager
        when(persistenceManager.newQuery(anyString())).thenReturn(query);
       // when(query.setUnique(true)).thenReturn(query);
        when(query.execute()).thenReturn(null);
        
     // Configurar comportamiento simulado para el Transaction
        when(transaction.isActive()).thenReturn(true);

        // Llamar al método de login
        Response response = resource.loginAdmin(adminData);

        // Verificar que se haya llamado a los métodos necesarios
        verify(persistenceManager).newQuery(anyString());
        verify(query).setUnique(true);
        verify(query).execute();
        verify(transaction).rollback();

        // Verificar el resultado de la respuesta
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void testObtenerPeliculas() {
        // Crear una lista de películas simulada
        List<Pelicula> peliculasSimuladas = new ArrayList<>();
        peliculasSimuladas.add(new Pelicula("Pelicula 1", null, 0, 0, null));
        peliculasSimuladas.add(new Pelicula("Pelicula 2", null, 0, 0, null));

        // Configurar comportamiento simulado para el PersistenceManager
        Extent<Pelicula> peliculaExtent = mock(Extent.class);
        when(persistenceManager.getExtent(Pelicula.class, true)).thenReturn(peliculaExtent);
        when(peliculaExtent.iterator()).thenReturn(peliculasSimuladas.iterator());

        // Llamar al método obtenerPeliculas
        List<Pelicula> peliculasObtenidas = resource.obtenerPeliculas();

        // Verificar que se haya llamado a los métodos necesarios
        verify(transaction).begin();
        verify(persistenceManager).getExtent(Pelicula.class, true);
        verify(peliculaExtent).iterator();
        verify(transaction).commit();

        // Verificar el resultado de las películas obtenidas
        assertEquals(peliculasSimuladas.size(), peliculasObtenidas.size());
        assertEquals(peliculasSimuladas.get(0), peliculasObtenidas.get(0));
        assertEquals(peliculasSimuladas.get(1), peliculasObtenidas.get(1));
    }
    
    @Test
    public void testObtenerUsuarios() {
        // Crear una lista de películas simulada
        List<User> usuariosSimulados = new ArrayList<>();
        usuariosSimulados.add(new User("user", "user", "user"));
        usuariosSimulados.add(new User("user1", "user1", "user1"));

        // Configurar comportamiento simulado para el PersistenceManager
        Extent<User> userExtent = mock(Extent.class);
        when(persistenceManager.getExtent(User.class, true)).thenReturn(userExtent);
        when(userExtent.iterator()).thenReturn(usuariosSimulados.iterator());

        // Llamar al método obtenerPeliculas
        List<User> usuariosObtenidos = resource.obtenerUsuarios();

        // Verificar que se haya llamado a los métodos necesarios
        verify(transaction).begin();
        verify(persistenceManager).getExtent(User.class, true);
        verify(userExtent).iterator();
        verify(transaction).commit();

        // Verificar el resultado de las películas obtenidas
        assertEquals(usuariosSimulados.size(), usuariosObtenidos.size());
        assertEquals(usuariosSimulados.get(0), usuariosObtenidos.get(0));
        assertEquals(usuariosSimulados.get(1), usuariosObtenidos.get(1));
    }
    
    
    @Test
    public void testGetLogin() {
        // Nombre de usuario simulado
        String nombreUsuario = "username";

        // Crear un objeto User simulado
        User mockUser = mock(User.class);
        when(mockUser.getLogin()).thenReturn(nombreUsuario);

        // Configurar comportamiento simulado para el PersistenceManager
        when(persistenceManager.newQuery(anyString())).thenReturn(query);
      //  when(query.setUnique(true)).thenReturn(query);
        when(query.execute()).thenReturn(mockUser);

        // Llamar al método getLogin
        User userObtenido = resource.getLogin(nombreUsuario);

        // Verificar que se haya llamado a los métodos necesarios
        verify(transaction).begin();
        verify(persistenceManager).newQuery(anyString());
        verify(query).setUnique(true);
        verify(query).execute();
        verify(transaction).commit();

        // Verificar el resultado del usuario obtenido
        assertEquals(mockUser, userObtenido);
    }
 
    @Test
    public void testFiltrarUsuario() {
        // prepare mock Persistence Manager to return Pelicula instances
        ArrayList<User> usuarios = new ArrayList<User>();
        User u1 = new  User("1", "pass", "email");
        User u2 = new  User("2", "pass", "email");
        User u3 = new  User("3", "pass", "email");
        Alquiler alquilerU1 = new Alquiler("1", "1");
        Pelicula peli = new Pelicula("1", "Peli", 0, 0, Genero.ACCION);
        usuarios.add(u1);
        usuarios.add(u2);
        usuarios.add(u3);

        Extent<User> peliExtent = mock(Extent.class);
        when(persistenceManager.getExtent(User.class, true)).thenReturn(peliExtent);
        when(peliExtent.iterator()).thenReturn(usuarios.iterator());

        // call tested method
        java.util.List<Pelicula> result = resource.filtrarUsuario("1");

        // check expected result
        assertEquals(0, result.size());

    }
    @Test
    public void testFiltrarUsuario_NoAlquileres() {
        // prepare mock Persistence Manager with no alquileres for the given user
        Extent<Alquiler> alquilerExtent = mock(Extent.class);
        when(persistenceManager.getExtent(Alquiler.class, true)).thenReturn(alquilerExtent);
        when(alquilerExtent.iterator()).thenReturn(Collections.emptyIterator());

        // call tested method
        List<Pelicula> result = resource.filtrarUsuario("1");

        // check expected result
        assertEquals(0, result.size());
    }

    @Test
    public void testFiltrarUsuario_ExceptionDuringTransaction() {
        // Simular una excepción al iniciar la transacción
        doThrow(new RuntimeException("Error starting transaction")).when(transaction).begin();

        // Llamar al método bajo prueba
        List<Pelicula> resultado = resource.filtrarUsuario("1");

        // Verificar el resultado esperado (lista vacía)
        assertEquals(0, resultado.size());
    }
    
//    @Test
//    public void testFiltrarUsuario_ReturnsListOfMovies() {
//        // Simula la existencia de alquileres relacionados con el usuario
//        Alquiler alquiler1 = new Alquiler("ABC123", "user123");
//        Alquiler alquiler2 = new Alquiler("XYZ789", "user123");
//        List<Alquiler> alquileres = new ArrayList<>();
//        alquileres.add(alquiler1);
//        alquileres.add(alquiler2);
//
//        // Simula la existencia de películas relacionadas con los alquileres
//        Pelicula pelicula1 = new Pelicula("ABC123", "Peli1", 0, 0, Genero.ACCION);
//        Pelicula pelicula2 = new Pelicula("XYZ789", "Peli2", 0, 0, Genero.DRAMA);
//        List<Pelicula> peliculas = new ArrayList<>();
//        peliculas.add(pelicula1);
//        peliculas.add(pelicula2);
//
//        // Configura el PersistenceManager simulado para devolver los alquileres
//        Extent<Alquiler> alquilerExtent = mock(Extent.class);
//        when(persistenceManager.getExtent(Alquiler.class, true)).thenReturn(alquilerExtent);
//        when(alquilerExtent.iterator()).thenReturn(alquileres.iterator());
//
//        // Configura el PersistenceManager simulado para devolver las películas
//        Extent<Pelicula> peliExtent = mock(Extent.class);
//        when(persistenceManager.getExtent(Pelicula.class, true)).thenReturn(peliExtent);
//        when(peliExtent.iterator()).thenReturn(peliculas.iterator());
//
//        // Llama al método filtrarUsuario con el usuario "user123"
//        List<Pelicula> result = resource.filtrarUsuario("user123");
//
//        // Verifica que la lista de películas devuelta contenga los elementos esperados
//        assertEquals(2, result.size());
//        assertTrue(result.contains(pelicula1));
//        assertTrue(result.contains(pelicula2));
//    }

    @Test
    public void testFiltrarUsuario_WhenNoMatchingUser_ReturnsEmptyList() {
        // Simula la ausencia de alquileres relacionados con el usuario
        List<Alquiler> alquileres = new ArrayList<>();

        // Configura el PersistenceManager simulado para devolver una lista vacía de alquileres
        Extent<Alquiler> alquilerExtent = mock(Extent.class);
        when(persistenceManager.getExtent(Alquiler.class, true)).thenReturn(alquilerExtent);
        when(alquilerExtent.iterator()).thenReturn(alquileres.iterator());

        // Llama al método filtrarUsuario con un usuario no existente
        List<Pelicula> result = resource.filtrarUsuario("nonexistentuser");

        // Verifica que la lista de películas devuelta esté vacía
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteUser_WhenUserExists_DeletesUserAndReturnsOkResponse() {
        // Simula la existencia de un usuario con el login "user123"
        User user = new User("user123", "password", "email");

        // Configura el PersistenceManager simulado para devolver el usuario
        when(persistenceManager.getObjectById(User.class, "user123")).thenReturn(user);

        // Llama al método deleteUser con el login "user123"
        Response response = resource.deleteUser("user123");

        // Verifica que el usuario haya sido eliminado correctamente
        verify(persistenceManager).deletePersistent(user);

        // Verifica que se haya devuelto una respuesta HTTP OK (código 200)
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testDeleteUser_WhenUserDoesNotExist_ReturnsErrorResponse() {
        // Configura el PersistenceManager simulado para lanzar una excepción al buscar el usuario
        when(persistenceManager.getObjectById(User.class, "nonexistentuser")).thenThrow(new JDOObjectNotFoundException());

        // Llama al método deleteUser con un login de usuario inexistente
        Response response = resource.deleteUser("nonexistentuser");

        // Verifica que se haya devuelto una respuesta HTTP INTERNAL_SERVER_ERROR (código 500)
        assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }



//    @Test
//    public void testSetPersistenceManager() {
//        // Create a mock PersistenceManager
//        PersistenceManager customPersistenceManager = mock(PersistenceManager.class);
//
//        // Call the method to set the custom PersistenceManager
//        resource.setPersistenceManager(customPersistenceManager);
//
//        // Verify that the custom PersistenceManager is set
//        assertEquals(customPersistenceManager, resource.getPersistenceManager());
//    }
//
//    @Test
//    public void testConstructor() {
//        // Verify that the PersistenceManagerFactory and PersistenceManager are obtained
//        assertEquals(persistenceManager, resource.getPersistenceManager());
//        assertEquals(transaction, resource.getTransaction());
//    }
//    
//    @Test
//    public void testGetTransaction() {
//        // Call the tested method
//        Transaction result = resource.getTransaction();
//
//        // Verify that the result is the mock transaction
//        assertEquals(transaction, result);
//    }

}
