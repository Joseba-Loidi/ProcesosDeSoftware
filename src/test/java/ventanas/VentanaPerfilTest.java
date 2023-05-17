package ventanas;

import static org.junit.Assert.assertEquals;

import javax.swing.JButton;

import org.junit.Test;
import org.mockito.Mockito;

import es.deusto.spq.server.jdo.User;


public class VentanaPerfilTest{	
	@Test
	public void test() {
		try {
			VentanaPerfilTest VPerfil = new VentanaPerfilTest();
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

//	@Test
//	public void testSetAndGetVp() {
//		// Crear una instancia de VentanaPerfil
//		VentanaPerfil ventanaPerfil = new VentanaPerfil(Mockito.mock(User.class));
//
//		// Crear una instancia de VentanaPrincipal
//		VentanaPrincipal ventanaPrincipal = Mockito.mock(VentanaPrincipal.class);
//
//		// Establecer la instancia de VentanaPrincipal en VentanaPerfil
//		ventanaPerfil.setVp(ventanaPrincipal);
//
//		// Obtener la instancia de VentanaPrincipal desde VentanaPerfil
//		VentanaPrincipal result = ventanaPerfil.getVp();
//
//		// Verificar si las instancias son iguales
//		assertEquals(ventanaPrincipal, result);
//	}

}
