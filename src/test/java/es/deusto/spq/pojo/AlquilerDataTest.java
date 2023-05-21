package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.server.jdo.Alquiler;

public class AlquilerDataTest {
private AlquilerData alquiler;
	
	@Before
	public void setUp() {
		alquiler = new AlquilerData("ABC123", "user123");
	}
	
	@Test
	public void testGetCodPelicula() {
		assertEquals("ABC123", alquiler.getCodPelicula());
	}
	
	@Test
	public void testSetCodPelicula() {
		alquiler.setCodPelicula("XYZ789");
		assertEquals("XYZ789", alquiler.getCodPelicula());
	}
	
	@Test
	public void testGetLoginUser() {
		assertEquals("user123", alquiler.getLoginUser());
	}
	
	@Test
	public void testSetLoginUser() {
		alquiler.setLoginUser("user456");
		assertEquals("user456", alquiler.getLoginUser());
	}
	
	
	@Test
	public void testToString() {
	    String expectedString = "Alquiler [codPelicula=ABC123, LoginUser=user123]";
	    assertEquals(expectedString, alquiler.toString());
	}
	@Test
    public void testConstructorVacio() {
        AlquilerData alqui = new AlquilerData();
        assertNotNull(alqui);
        assertEquals(null, alqui.getCodPelicula());
        assertEquals(null, alqui.getLoginUser());
    }
	
}
