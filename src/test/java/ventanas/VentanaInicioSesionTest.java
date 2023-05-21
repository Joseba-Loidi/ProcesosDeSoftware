package ventanas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class VentanaInicioSesionTest {

	@Test
	public void test() {
		try {
			VentanaInicioSesion VI = new VentanaInicioSesion();
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	 @Test
	    public void testVentanaInicioSesion() {
	        VentanaInicioSesion ventana = new VentanaInicioSesion();

	        // Verificar que el título de la ventana es correcto
	      //  assertEquals("INICIO SESION", ventana.getTitle());

	        // Verificar que los campos de texto estén vacíos al inicio
	        assertEquals("", ventana.usuarioTxt.getText());
	        assertEquals("", ventana.contrLoginTxt.getText());
	        assertEquals("", ventana.nombreTxt.getText());
	        assertEquals("", ventana.emailTxt.getText());
	        assertEquals("", ventana.contrTxt.getText());
	        assertEquals("", ventana.contr2Txt.getText());
	    }
}
