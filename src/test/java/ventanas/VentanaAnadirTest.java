package ventanas;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.junit.Test;
import org.mockito.Mock;

import es.deusto.spq.server.jdo.Genero;

public class VentanaAnadirTest {
	 private VentanaAnadir ventanaAnadir;
	
	@Mock
    private JTextField textCodigo;
    @Mock
    private JTextField textTitulo;
    @Mock
    private JTextField textMinutos;
    @Mock
    private JTextField textValoracion;
    @Mock
    private JComboBox<Genero> comboBox;
	
	@Test
	public void test() {
		try {
			VentanaAnadir VA = new VentanaAnadir();
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	
	
	
	
}
