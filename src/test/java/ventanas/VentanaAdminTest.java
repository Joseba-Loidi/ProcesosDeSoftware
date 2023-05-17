package ventanas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.junit.Test;
import org.mockito.Mockito;

import es.deusto.spq.client.Cliente;
import es.deusto.spq.server.jdo.Admin;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;
import junit.framework.Assert;

public class VentanaAdminTest {
	
	@Test
	public void test() {
		try {
			VentanaAdmin VAdmin = new VentanaAdmin();
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
//	@Test
//	public void testCargarTabla() {
//		// Crear una instancia de VentanaAdmin
//		VentanaAdmin ventanaAdmin = new VentanaAdmin();
//
//		// Crear un mock del cliente
//		Cliente clienteMock = Mockito.mock(Cliente.class);
//		
//		// Crear un objeto User simulado
//        Pelicula mockPeli = mock(Pelicula.class);
//        when(mockPeli.getCodigo()).thenReturn("COD-001");
//        when(mockPeli.getGenero()).thenReturn(Genero.ACCION);
//        when(mockPeli.getTitulo()).thenReturn("Pulp Fiction");
//        when(mockPeli.getMinutos()).thenReturn(159);
//        when(mockPeli.getValoracion()).thenReturn(9);
//
////		// Simular la llamada a obtenerPelis() y definir el comportamiento del mock
////		List<Pelicula> peliculas = new ArrayList<>();
////		peliculas.add(new Pelicula("COD-001", "The Matrix", 136, 8, Genero.ACCION));
////		peliculas.add(new Pelicula("COD-002", "Pulp Fiction", 154, 9, Genero.ACCION));
////		Mockito.when(Cliente.obtenerPelis()).thenReturn(peliculas);
//
//		// Establecer el cliente mock en la instancia de VentanaAdmin
////		ventanaAdmin.setCliente(clienteMock);
//
//		// Llamar al método cargarTabla()
//		ventanaAdmin.cargarTabla();
//
//		// Verificar si se agregaron las filas esperadas a la tabla
//		DefaultTableModel modeloTabla = ventanaAdmin.getModeloTabla();
//		Assert.assertEquals(2, modeloTabla.getRowCount());
//
//		// Verificar los valores de las filas agregadas
//		assertEquals("COD-001", modeloTabla.getValueAt(0, 0));
//		assertEquals("The Matrix", modeloTabla.getValueAt(0, 1));
//		assertEquals(136, modeloTabla.getValueAt(0, 2));
//		assertEquals(8, modeloTabla.getValueAt(0, 3));
//		assertEquals(Genero.ACCION, modeloTabla.getValueAt(0, 4));
//
//		assertEquals("COD-002", modeloTabla.getValueAt(1, 0));
//		assertEquals("Pulp Fiction", modeloTabla.getValueAt(1, 1));
//		assertEquals(154, modeloTabla.getValueAt(1, 2));
//		assertEquals(9, modeloTabla.getValueAt(1, 3));
//		assertEquals(Genero.ACCION, modeloTabla.getValueAt(1, 4));
//	}
	
//	@Test
//	public void testCargarTabla() {
//	    // Crear una instancia de VentanaAdmin
//	    VentanaAdmin ventanaAdmin = new VentanaAdmin();
//
//	    // Crear un mock del cliente
//	    Cliente clienteMock = Mockito.mock(Cliente.class);
//
//	    // Crear una lista simulada de películas
//	    List<Pelicula> peliculas = new ArrayList<>();
//	    peliculas.add(new Pelicula("COD-001", "The Matrix", 136, 8, Genero.ACCION));
//	    peliculas.add(new Pelicula("COD-002", "Pulp Fiction", 154, 9, Genero.ACCION));
//
//	    // Simular la llamada a obtenerPelis() y definir el comportamiento del mock
//	    Mockito.when(clienteMock.obtenerPelis()).thenReturn(peliculas);
//
//	    // Establecer el cliente mock en la instancia de VentanaAdmin
//	    //ventanaAdmin.setCliente(clienteMock);
//
//	    // Llamar al método cargarTabla()
//	    ventanaAdmin.cargarTabla();
//
//	    // Verificar si se agregaron las filas esperadas a la tabla
//	    DefaultTableModel modeloTabla = ventanaAdmin.getModeloTabla();
//	    assertEquals(2, modeloTabla.getRowCount());
//
//	    // Verificar los valores de las filas agregadas
//	    assertEquals("COD-001", modeloTabla.getValueAt(0, 0));
//	    assertEquals("The Matrix", modeloTabla.getValueAt(0, 1));
//	    assertEquals(136, modeloTabla.getValueAt(0, 2));
//	    assertEquals(8, modeloTabla.getValueAt(0, 3));
//	    assertEquals(Genero.ACCION, modeloTabla.getValueAt(0, 4));
//
//	    assertEquals("COD-002", modeloTabla.getValueAt(1, 0));
//	    assertEquals("Pulp Fiction", modeloTabla.getValueAt(1, 1));
//	    assertEquals(154, modeloTabla.getValueAt(1, 2));
//	    assertEquals(9, modeloTabla.getValueAt(1, 3));
//	    assertEquals(Genero.ACCION, modeloTabla.getValueAt(1, 4));
//	}
}
