package ventanas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.junit.Test;
import org.mockito.Mockito;

import es.deusto.spq.server.jdo.User;


public class VentanaPerfilTest{	
	User user;
	VentanaPrincipal ventanaPrincipal;
	
	@Test
	public void test() {
		try {
			VentanaPerfilTest VPerfil = new VentanaPerfilTest();
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
//	@Test
//    public void testVentanaPerfil() {
//        User user = new User("john_doe", "John Doe", "hola");
//
//        VentanaPerfil ventanaPerfil = new VentanaPerfil(user);
//
//        // Verifica que la ventana tenga el valor adecuado para setDefaultCloseOperation
//        assertEquals(WindowConstants.DISPOSE_ON_CLOSE, ventanaPerfil.getDefaultCloseOperation());
//
//        // Verifica que el tamaño de la ventana sea el esperado
//        assertEquals(259, ventanaPerfil.getWidth());
//        assertEquals(285, ventanaPerfil.getHeight());
//
//        // Verifica que el contentPane no sea nulo y tenga el color adecuado
//        assertNotNull(ventanaPerfil.getContentPane());
//        assertEquals(Color.DARK_GRAY, ventanaPerfil.getContentPane().getBackground());
//
//        // Verifica que el panel contenga el panel_1 y tenga el color adecuado
//        JPanel panel = (JPanel) ventanaPerfil.getContentPane().getComponent(0);
//        assertNotNull(panel);
//        assertEquals(Color.DARK_GRAY, panel.getBackground());
//
//        // Verifica que el panel_1 tenga un GridLayout con 5 filas y 1 columna
//        JPanel panel_1 = (JPanel) panel.getComponent(0);
//        assertNotNull(panel_1);
//        assertTrue(panel_1.getLayout() instanceof GridLayout);
//        GridLayout gridLayout = (GridLayout) panel_1.getLayout();
//        assertEquals(5, gridLayout.getRows());
//        assertEquals(1, gridLayout.getColumns());
//
//        // Verifica que se haya creado un JLabel con el nombre de usuario correcto
//        JLabel labelNombre = (JLabel) panel_1.getComponent(1);
//        assertNotNull(labelNombre);
//        assertEquals(user.getLogin(), labelNombre.getText());
//
//        // Verifica que se hayan creado dos botones
//        JButton eliminarCuentaButton = (JButton) panel_1.getComponent(2);
//        JButton cerrarSesionButton = (JButton) panel_1.getComponent(4);
//        assertNotNull(eliminarCuentaButton);
//        assertNotNull(cerrarSesionButton);
//
//        // Verifica que los botones tengan el texto y la fuente adecuada
//        assertEquals("Eliminar cuenta", eliminarCuentaButton.getText());
//        assertEquals("Cerrar Sesion", cerrarSesionButton.getText());
//        assertEquals(new Font("Arial", Font.BOLD, 14), eliminarCuentaButton.getFont());
//        assertEquals(new Font("Arial", Font.BOLD, 14), cerrarSesionButton.getFont());
//    }

//	@Test
//	public void testSetAndGetVp() {
//		// Crear una instancia mock de User
//        User user = Mockito.mock(User.class);
//
//        // Crear una instancia mock de VentanaPrincipal
//        VentanaPrincipal ventanaPrincipal = Mockito.mock(VentanaPrincipal.class);
//
//        // Crear una instancia de VentanaPerfil con el User mock
//        VentanaPerfil ventanaPerfil = new VentanaPerfil(user);
//
//        // Establecer la instancia de VentanaPrincipal mock en VentanaPerfil
//        ventanaPerfil.setVp(ventanaPrincipal);
//
//        // Obtener la instancia de VentanaPrincipal desde VentanaPerfil
//        VentanaPrincipal result = ventanaPerfil.getVp();
//
//        // Verificar si las instancias son iguales
//        assertEquals(ventanaPrincipal, result);
//    }
	}

