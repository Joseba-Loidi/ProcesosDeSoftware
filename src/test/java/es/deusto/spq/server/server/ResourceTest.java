package es.deusto.spq.server.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;

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

import es.deusto.spq.pojo.AdminData;
import es.deusto.spq.pojo.PeliculaData;
import es.deusto.spq.pojo.UserData;
import es.deusto.spq.server.Resource;
import es.deusto.spq.server.jdo.Admin;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;
import es.deusto.spq.server.jdo.User;


public class ResourceTest {
	
    private Resource resource;

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
    
    
    
//    @Test
//    public void testFiltrarNombre() {
//        // prepare mock Persistence Manager to return Pelicula instances
//        ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
//        peliculas.add(new Pelicula("COD-001", "The_Matrix", 136, 8, Genero.ACCION));
//        peliculas.add(new Pelicula("COD-002", "Pulp Fiction", 154, 9, Genero.ACCION));
//        peliculas.add(new Pelicula("COD-003", "Forrest Gump", 142, 9, Genero.DRAMA));
//
//        Extent<Pelicula> peliExtent = mock(Extent.class);
//        when(persistenceManager.getExtent(Pelicula.class, true)).thenReturn(peliExtent);
//        when(peliExtent.iterator()).thenReturn(peliculas.iterator());
//
//        // call tested method
//        Pelicula result = resource.filtrarNombre("The_Matrix");
//
//
//        // check that the result contains the expected Pelicula instances
//        
//        assertEquals("COD-001", result.getCodigo());
//        assertEquals("The_Matrix", result.getTitulo());
//        assertEquals(136, result.getMinutos());
//        assertEquals(8, result.getValoracion());
//        assertEquals(Genero.ACCION, result.getGenero());
//
//    }
    
    @Test
    public void testLoginAdmin() {
        // Crea un objeto UserData con datos de usuario válidos
        AdminData adminData = new AdminData("admin1", "admin1");
        
        // Crea un usuario mock
        Admin adminMock = mock(Admin.class);
        
        // Configura el comportamiento del usuario mock
        when(adminMock.getLogin()).thenReturn("admin1");
        when(adminMock.getPassword()).thenReturn("admin1");
        
        // Crea un objeto Query mock
        Query<?> queryMock = mock(Query.class);
        
        // Configura el comportamiento del Query mock
//        when(queryMock.setUnique(true)).thenReturn(queryMock);
        when(queryMock.execute()).thenReturn(adminMock);
        
        // Crea un objeto PersistenceManager mock
        PersistenceManager pmMock = mock(PersistenceManager.class);
        
        // Configura el comportamiento del PersistenceManager mock
        when(pmMock.newQuery(anyString())).thenReturn(queryMock);
        
        // Crea un objeto Transaction mock
        Transaction txMock = mock(Transaction.class);
        
        // Crea un objeto Resource con los mocks creados
        Resource resource = new Resource();
        
        // Llama al método login con el objeto UserData creado
        Response response = resource.loginAdmin(adminData);

        // Comprueba que la respuesta es OK
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }
    
//    @Test
//    public void testLoginAdmin() {
//        // Crea un objeto AdminData con datos de administrador válidos
//        AdminData adminData = new AdminData("admin", "password");
//
//        // Prepara los mocks
//        Admin admin = new Admin("admin", "password");
//        when(persistenceManager.newQuery(Admin.class, "login == :login && password == :password")).thenReturn(query);
//        when(query.setParameters(adminData.getLogin(), adminData.getPassword())).thenReturn(query);
//        when(query.executeUnique()).thenReturn(admin);
//
//        // Llama al método loginAdmin con el objeto AdminData creado
//        Response response = resource.loginAdmin(adminData);
//
//        // Comprueba que la respuesta es OK
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//
//        // Comprueba que se han llamado a los métodos correspondientes del PersistenceManager y el Query
//        verify(persistenceManager).newQuery(Admin.class, "login == :login && password == :password");
//        verify(query).setParameters(adminData.getLogin(), adminData.getPassword());
//        verify(query).executeUnique();
//    }
    
    
//    @Test
//    public void testLoginAdmin() {
//        // Crea un objeto AdminData con datos de administrador válidos
//        AdminData adminData = new AdminData("admin", "password");
//
//        // Llama al método loginAdmin con el objeto AdminData creado
//        Response response = resource.loginAdmin(adminData);
//
//        // Comprueba que la respuesta es OK
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//    }
//
//    
//    @Test
//    public void testLogin() {
//        // Crea un objeto UserData con datos de usuario válidos
//        UserData userData = new UserData("usuario", "password", "email");
//
//        // Agrega el usuario a la base de datos
//        resource.registerUser(userData);
//
//        // Llama al método login con el objeto UserData creado
//        Response response = resource.login(userData);
//
//        // Comprueba que la respuesta es OK
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//    }
    @Test
    public void testLogin() {
        // Crea un objeto UserData con datos de usuario válidos
        UserData userData = new UserData("paula", "paula", "paula.asua1@opendeusto.es");
        
        // Crea un usuario mock
        User userMock = mock(User.class);
        
        // Configura el comportamiento del usuario mock
        when(userMock.getLogin()).thenReturn("paula");
        when(userMock.getPassword()).thenReturn("paula");
        
        // Crea un objeto Query mock
        Query<?> queryMock = mock(Query.class);
        
        // Configura el comportamiento del Query mock
//        when(queryMock.setUnique(true)).thenReturn(queryMock);
        when(queryMock.execute()).thenReturn(userMock);
        
        // Crea un objeto PersistenceManager mock
        PersistenceManager pmMock = mock(PersistenceManager.class);
        
        // Configura el comportamiento del PersistenceManager mock
        when(pmMock.newQuery(anyString())).thenReturn(queryMock);
        
        // Crea un objeto Transaction mock
        Transaction txMock = mock(Transaction.class);
        
        // Crea un objeto Resource con los mocks creados
        Resource resource = new Resource();
        
        // Llama al método login con el objeto UserData creado
        Response response = resource.login(userData);

        // Comprueba que la respuesta es OK
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }
    
//    @Test
//    public void testFiltrarNombre() {
//        // Crea un objeto Pelicula con un título válido
//        String titulo = "Matrix";
//        Pelicula pelicula = new Pelicula();
//        pelicula.setTitulo(titulo);
//
//        // Inserta la Pelicula en la base de datos
//        persistenceManager.makePersistent(pelicula);
//
//        // Llama al método filtrarNombre con el título de la película creada
//        Pelicula peliculaFiltrada = resource.filtrarNombre(titulo);
//
//        // Comprueba que la Pelicula devuelta no es null y que tiene el título correcto
//        assertNotNull(peliculaFiltrada);
//        assertEquals(titulo, peliculaFiltrada.getTitulo());
//    }


}
