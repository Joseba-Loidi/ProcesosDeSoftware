package ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.client.Cliente;
import es.deusto.spq.server.jdo.User;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPerfil extends JFrame {
	
	private VentanaPrincipal vp;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaPerfil frame = new VentanaPerfil(user));
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	
	/**
	 * Create the frame.
	 */
	public VentanaPerfil(User user) {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 259, 285);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(5,1));
		
		//poner la imagen
		ImageIcon imageIcon = new ImageIcon("src/main/resources/IconoUsuario1.png");
					
		JLabel labelImagen = new JLabel(imageIcon);
		panel_1.add(labelImagen);
		labelImagen.setIcon(imageIcon);
		//ImageIcon imageIcon = new ImageIcon("/ProcesosDeSoftware/src/main/java/imgs/fondo_inicio2.jpg");
		panel_1.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
		
		String nombreUsuario = user.getLogin() ;
		
		JLabel labelNombre = new JLabel(nombreUsuario);
		labelNombre.setForeground(Color.WHITE); 
		labelNombre.setFont(new Font("Arial", Font.BOLD, 14)); 
		labelNombre.setHorizontalAlignment(SwingConstants.CENTER); 
		labelNombre.setVerticalAlignment(SwingConstants.CENTER);

		panel_1.add(labelNombre);
		
		
		JButton eliminarCuenta = new JButton("Eliminar cuenta");
		eliminarCuenta.setBackground(Color.GRAY);
		eliminarCuenta.setBorderPainted(false); 
		eliminarCuenta.setContentAreaFilled(false); 
		eliminarCuenta.setOpaque(true); 
		eliminarCuenta.setForeground(Color.WHITE); 
		eliminarCuenta.setFont(new Font("Arial", Font.BOLD, 14)); 
		panel_1.add(eliminarCuenta);
		
		eliminarCuenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean cuentaBorrada = Cliente.deleteUser(user.getLogin());
				if (cuentaBorrada) {
					dispose();
					try {
						vp.dispose();
						VentanaInicioSesion vi = new VentanaInicioSesion();
						vi.setVisible(true);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}else {
					JOptionPane.showMessageDialog(null, "No se ha podido eliminar la cuenta", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		JLabel labelVacio = new JLabel(" ");
		panel_1.add(labelVacio);
		
		JButton cerrarSesion = new JButton("Cerrar Sesion");
		cerrarSesion.setBackground(Color.GRAY);
		cerrarSesion.setBorderPainted(false); 
		cerrarSesion.setContentAreaFilled(false); 
		cerrarSesion.setOpaque(true); 
		cerrarSesion.setForeground(Color.WHITE); 
		cerrarSesion.setFont(new Font("Arial", Font.BOLD, 14)); 
		panel_1.add(cerrarSesion);
		
		cerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				try {
					vp.dispose();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				VentanaInicioSesion vi = new VentanaInicioSesion();
				vi.setVisible(true);
				
			}
		});
		
	}

	public VentanaPrincipal getVp() {
		return vp;
	}

	public void setVp(VentanaPrincipal vp) {
		this.vp = vp;
	}

}
