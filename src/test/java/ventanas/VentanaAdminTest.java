package ventanas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
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
	
	@Test
    public void testVentanaAdmin() { //NO CAMBIA NADA
        // Crear los objetos necesarios para el test
        VentanaAdmin ventanaAdmin = new VentanaAdmin();
        DefaultTableModel modeloTabla = mock(DefaultTableModel.class);
        JTable table = mock(JTable.class);
        JScrollPane scrollTabla = mock(JScrollPane.class);
        DefaultTableModel modeloTablaUsuario = mock(DefaultTableModel.class);
        JTable tableUsuario = mock(JTable.class);
        JScrollPane scrollTablaUsuario = mock(JScrollPane.class);

        // Verificar la configuración de la ventana
        assertEquals(JFrame.EXIT_ON_CLOSE, ventanaAdmin.getDefaultCloseOperation());
        assertEquals(700, ventanaAdmin.getWidth());
        assertEquals(478, ventanaAdmin.getHeight());

        // Verificar la configuración del contentPane
        JPanel contentPane = (JPanel) ventanaAdmin.getContentPane();
        assertNotNull(contentPane);
        assertEquals(Color.DARK_GRAY, contentPane.getBackground());
        assertEquals(EmptyBorder.class, contentPane.getBorder().getClass());

        // Verificar la estructura del contentPane
        assertEquals(BorderLayout.class, contentPane.getLayout().getClass());
        assertNotNull(contentPane.getComponent(0));

        // Verificar la configuración del panel
        JPanel panel = (JPanel) contentPane.getComponent(0);
        assertNotNull(panel);
        assertEquals(Color.DARK_GRAY, panel.getBackground());
        assertEquals(BorderLayout.class, panel.getLayout().getClass());

        // Verificar la estructura del panel
        assertNotNull(panel.getComponent(0));
        assertNotNull(panel.getComponent(1));

     // Verificar la configuración del panelPelis
        JPanel panelPelis = (JPanel) panel.getComponent(0);
        assertNotNull(panelPelis);
        assertEquals(Color.BLUE, panelPelis.getBackground());
        assertEquals(FlowLayout.class, panelPelis.getLayout().getClass());
        
        

        // Verificar la configuración de la tabla
        assertNotNull(table);
        //verify(panelPelis).add(scrollTabla, BorderLayout.CENTER);
       // verify(scrollTabla).setViewportView(table);

        // Verificar la configuración del panelUsu
        JPanel panelUsu = (JPanel) panel.getComponent(1);
        assertNotNull(panelUsu);
       // assertEquals(Color.MAGENTA, panelUsu.getBackground());
        assertNotNull(tableUsuario);
//        verify(panelUsu).add(scrollTablaUsuario, BorderLayout.CENTER);
//        verify(scrollTablaUsuario).setViewportView(tableUsuario);

        // Verificar la configuración de los botones
        JPanel panelBtn = (JPanel) panel.getComponent(2);
        assertNotNull(panelBtn);
        //assertEquals(Color.DARK_GRAY, panelBtn.getBackground());
        assertEquals(GridLayout.class, panelBtn.getLayout().getClass());
        JButton anadir = (JButton) panelBtn.getComponent(0);
        JButton eliminar = (JButton) panelBtn.getComponent(1);
        assertNotNull(anadir);
        assertNotNull(eliminar);
        assertTrue(anadir.getActionListeners().length > 0);
        assertTrue(eliminar.getActionListeners().length > 0);

        // Verificar la configuración de los botones Peliculas y Usuarios
//        JPanel panel_1 = (JPanel) panel.getComponent(3);
//        assertNotNull(panel_1);
//        assertEquals(Color.BLACK, panel_1.getBackground());
//        assertEquals(GridLayout.class, panel_1.getLayout().getClass());
//        JButton peliculas = (JButton) panel_1.getComponent(0);
//        JButton usuarios = (JButton) panel_1.getComponent(1);
//        assertNotNull(peliculas);
//        assertNotNull(usuarios);
        
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
