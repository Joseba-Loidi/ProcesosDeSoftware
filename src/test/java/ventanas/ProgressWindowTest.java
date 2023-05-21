package ventanas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import org.junit.Test;

public class ProgressWindowTest {
	
	@Test
	public void test() {
		try {
			ProgressWindow pw = new ProgressWindow();
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}
	
	 @Test
	    public void testProgressWindow() {
	        ProgressWindow progressWindow = new ProgressWindow();

	        // Verifica que el título de la ventana sea el esperado
	        assertEquals("Ventana de Progreso", progressWindow.getTitle());

	        // Verifica que el cierre de la ventana esté configurado correctamente
	        assertEquals(WindowConstants.DO_NOTHING_ON_CLOSE, progressWindow.getDefaultCloseOperation());

	        // Verifica el tamaño y la ubicación de la ventana
	        assertEquals(400, progressWindow.getWidth());
	        assertEquals(300, progressWindow.getHeight());
	       // assertEquals(null, progressWindow.getLocation());

	        // Verifica la estructura del mainPanel y del progressPanel
	        JPanel mainPanel = (JPanel) progressWindow.getContentPane().getComponent(0);
	        assertNotNull(mainPanel);
	        assertEquals(BorderLayout.class, mainPanel.getLayout().getClass());

	        JPanel progressPanel = (JPanel) mainPanel.getComponent(1);
	        assertNotNull(progressPanel);
	        assertEquals(BorderLayout.class, progressPanel.getLayout().getClass());

	        // Verifica la existencia y configuración de la progressBar
	        JProgressBar progressBar = (JProgressBar) progressPanel.getComponent(0);
	        assertNotNull(progressBar);
	        assertEquals(0, progressBar.getMinimum());
	        assertEquals(100, progressBar.getMaximum());
	        assertTrue(progressBar.isStringPainted());

	        // Verifica la existencia del imageLabel
	        JLabel imageLabel = (JLabel) mainPanel.getComponent(0);
	        assertNotNull(imageLabel);

	        // Verifica que se haya creado un Timer y esté en ejecución
//	        Timer timer = progressWindow.getTimer();
//	        assertNotNull(timer);
//	        assertTrue(timer.isRunning());
	    }

}
