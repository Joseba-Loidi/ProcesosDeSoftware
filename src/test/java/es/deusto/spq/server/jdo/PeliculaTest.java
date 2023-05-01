package es.deusto.spq.server.jdo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PeliculaTest {

	private Pelicula pelicula1;
	private Pelicula pelicula2;
	private Pelicula pelicula3;
	
	@Before
	public void setUp() throws Exception {
		pelicula1 = new Pelicula("ahdj2","Cars", 120, 3, Genero.ACCION);
		pelicula2 = new Pelicula("jfks1","7 apellidos vascos", 180, 5, Genero.COMEDIA);
		pelicula3 = new Pelicula("laks5","Barbie", 150, 4, Genero.CIENCIA_FICCION);
		
	}
	
	@Test
	public void testGetCodigo() {
		assertEquals(pelicula1.getCodigo(), "ahdj2");
		assertEquals(pelicula2.getCodigo(), "jfks1");
		assertEquals(pelicula3.getCodigo(), "laks5");
	}
	
	@Test
	public void testGetTitulo() {
		assertEquals(pelicula1.getTitulo(), "Cars");
		assertEquals(pelicula2.getTitulo(), "7 apellidos vascos");
		assertEquals(pelicula3.getTitulo(), "Barbie");
	}
	
	@Test
	public void testGetMinutos() {
		assertEquals(pelicula1.getMinutos(), 120);
		assertEquals(pelicula2.getMinutos(), 180);
		assertEquals(pelicula3.getMinutos(), 150);
	}

	@Test
	public void testGetValoracion() {
		assertEquals(pelicula1.getValoracion(), 3);
		assertEquals(pelicula2.getValoracion(), 5);
		assertEquals(pelicula3.getValoracion(), 4);
	}
	@Test
	public void testGetGenero() {
		assertEquals(pelicula1.getGenero(), Genero.ACCION);
		assertEquals(pelicula2.getGenero(), Genero.COMEDIA);
		assertEquals(pelicula3.getGenero(), Genero.CIENCIA_FICCION);
	}
	@Test
	public void testNotNull() {
		assertNotNull(pelicula1);
		assertNotNull(pelicula2);
		assertNotNull(pelicula3);
	}
	
	
}
