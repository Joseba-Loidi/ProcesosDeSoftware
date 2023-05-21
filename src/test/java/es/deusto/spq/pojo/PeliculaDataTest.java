package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;

public class PeliculaDataTest {

	private PeliculaData peliculaData;
	
	@Before
	public void setUp() throws Exception {
		peliculaData = new PeliculaData("P001", "The Godfather", 175, 9, Genero.DRAMA);
	}

//	@Test
//	public void testConstructor() {
//		assertNotNull(peliculaData);
//		assertEquals("P001", peliculaData.getCodigo());
//		assertEquals("The Godfather", peliculaData.getTitulo());
//		assertEquals(175, peliculaData.getMinutos());
//		assertEquals(9, peliculaData.getValoracion());
//		assertEquals(Genero.DRAMA, peliculaData.getGenero());
//	}
	
	@Test
	public void testSettersAndGetters() {
		peliculaData.setCodigo("P002");
		peliculaData.setTitulo("The Shawshank Redemption");
		peliculaData.setMinutos(142);
		peliculaData.setValoracion(10);
		peliculaData.setGenero(Genero.AVENTURA);
		
		assertEquals("P002", peliculaData.getCodigo());
		assertEquals("The Shawshank Redemption", peliculaData.getTitulo());
		assertEquals(142, peliculaData.getMinutos());
		assertEquals(10, peliculaData.getValoracion());
		assertEquals(Genero.AVENTURA, peliculaData.getGenero());
	}
	
	@Test
	public void testToPelicula() {
		Pelicula pelicula = PeliculaData.toPelicula(peliculaData);
		
		assertEquals(peliculaData.getCodigo(), pelicula.getCodigo());
		assertEquals(peliculaData.getTitulo(), pelicula.getTitulo());
		assertEquals(peliculaData.getMinutos(), pelicula.getMinutos());
		assertEquals(peliculaData.getValoracion(), pelicula.getValoracion());
		assertEquals(peliculaData.getGenero().name(), pelicula.getGenero().name());
	}
	
    @Test
    public void testConstructorVacio() {
        PeliculaData pelicula = new PeliculaData();

        assertNull(pelicula.getCodigo());
        assertNull(pelicula.getTitulo());
        assertNull(pelicula.getGenero());
        assertEquals(0, pelicula.getMinutos());
        assertEquals(0, pelicula.getValoracion());
    }
    @Test
	  public void testConstructorP() {
	    PeliculaData pelicula = new PeliculaData("001", "El Padrino", 175, 9, Genero.DRAMA);
	    assertEquals("001", pelicula.getCodigo());
	    assertEquals("El Padrino", pelicula.getTitulo());
	    assertEquals(175, pelicula.getMinutos());
	    assertEquals(9, pelicula.getValoracion());
	    assertEquals(Genero.DRAMA, pelicula.getGenero());
	  }
    @Test
	  public void testToString() {
	    PeliculaData pelicula = new PeliculaData("003", "Interestellar", 169, 9, Genero.CIENCIA_FICCION);
	    String expectedString = "PeliculaData [codigo=003, titulo=Interestellar, genero=CIENCIA_FICCION, minutos=169, valoracion=9]";
	    assertEquals(expectedString, pelicula.toString());
	  }
}
