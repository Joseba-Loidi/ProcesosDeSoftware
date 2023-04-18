package ventanas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 478);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, "name_177794447374600");
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(1, 3)); //1 fila, 3 columnas
		

		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(55, 183, 129, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DeustoPrime");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2.setBounds(55, 183, 129, 14);
		panel_1.add(lblNewLabel_2);
		
		

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(1, 2)); 
		
		
		//poner la imagen
		ImageIcon imageIcon = new ImageIcon("img//IconoUsuario1.png");
				
		JLabel labelImagen = new JLabel(imageIcon);
		//labelImagen.setPreferredSize(new Dimension(panel_1.getWidth(), panel_1.getHeight()));
		// panel_1.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
		panel_2.add(labelImagen);
		labelImagen.setIcon(imageIcon);
		//ImageIcon imageIcon = new ImageIcon("/ProcesosDeSoftware/src/main/java/imgs/fondo_inicio2.jpg");
		panel_2.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));

		
		JButton bienvenido = new JButton("Usuario");
		bienvenido.setBackground(Color.DARK_GRAY);
		bienvenido.setBorderPainted(false); 
		bienvenido.setContentAreaFilled(false); 
		bienvenido.setOpaque(true); 
		bienvenido.setForeground(Color.WHITE); 
		bienvenido.setFont(new Font("Arial", Font.BOLD, 14)); 
		panel_2.add(bienvenido)
;		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GRAY);
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new GridLayout(1, 5));
		
		//PANELES
		JPanel panelFavorito = new JPanel();
		panelFavorito.setBackground(Color.CYAN);
		//panel_3.add(panelFavorito, BorderLayout.CENTER);		
		
		JPanel panelPeliculas = new JPanel();
		panelPeliculas.setBackground(Color.RED);
		panel_3.add(panelPeliculas, BorderLayout.CENTER);
		
		JPanel panelLista = new JPanel();
		panelLista.setBackground(Color.pink);
		//panel_3.add(panelLista, BorderLayout.CENTER);
		
		JPanel panelFiltro = new JPanel();
		panelFiltro.setBackground(Color.green);
		//panel_3.add(panelFiltro, BorderLayout.CENTER);
		
		
		//BOTON PARA VISUALIZAR PANEL
		
		JButton botonPeliculas = new JButton("Peliculas");
		botonPeliculas.setBackground(Color.GRAY);
		botonPeliculas.setBorderPainted(false); // Quita el borde del botón
		botonPeliculas.setContentAreaFilled(false); // Quita el color de fondo del botón
		botonPeliculas.setOpaque(true); // Hace que el botón sea opaco para que se pueda ver el texto
		botonPeliculas.setForeground(Color.WHITE); // Establece el color del texto
		botonPeliculas.setFont(new Font("Arial", Font.BOLD, 14)); // Establece la fuente y el tamaño del texto
		panel_4.add(botonPeliculas);
		botonPeliculas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//panel_3.getParent().setVisible(false);
				panelFavorito.setVisible(false);
				//panelPeliculas.setVisible(false);
				panelLista.setVisible(false);
				panelFiltro.setVisible(false);
				
				panel_3.add(panelPeliculas, BorderLayout.CENTER);
				panelPeliculas.setVisible(true);
				
			}
		});
		
		
		JButton botonLista = new JButton("Mi lista");
		botonLista.setBackground(Color.GRAY);
		botonLista.setBorderPainted(false); 
		botonLista.setContentAreaFilled(false); 
		botonLista.setOpaque(true); 
		botonLista.setForeground(Color.WHITE); 
		botonLista.setFont(new Font("Arial", Font.BOLD, 14)); 
		panel_4.add(botonLista);
		botonLista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//panel_3.getParent().setVisible(false);
				panelFavorito.setVisible(false);
				panelPeliculas.setVisible(false);
				//panelLista.setVisible(false);
				panelFiltro.setVisible(false);
				
				panel_3.add(panelLista, BorderLayout.CENTER);
				panelLista.setVisible(true);
				
			}
		});
		
		JButton Favoritos = new JButton("Favoritos");
		Favoritos.setBackground(Color.GRAY);
		Favoritos.setBorderPainted(false); // Quita el borde del botón
		Favoritos.setContentAreaFilled(false); // Quita el color de fondo del botón
		Favoritos.setOpaque(true); // Hace que el botón sea opaco para que se pueda ver el texto
		Favoritos.setForeground(Color.WHITE); // Establece el color del texto
		Favoritos.setFont(new Font("Arial", Font.BOLD, 14)); // Establece la fuente y el tamaño del texto
		panel_4.add(Favoritos);
		Favoritos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//panel_3.getParent().setVisible(false);
				//panelFavorito.setVisible(false);
				panelPeliculas.setVisible(false);
				panelLista.setVisible(false);
				panelFiltro.setVisible(false);
				
				panel_3.add(panelFavorito, BorderLayout.CENTER);
				panelFavorito.setVisible(true);
				
			}
		});
		
		JButton botonFiltrar = new JButton("Filtro");
		botonFiltrar.setBackground(Color.GRAY);
		botonFiltrar.setBorderPainted(false); 
		botonFiltrar.setContentAreaFilled(false); 
		botonFiltrar.setOpaque(true); 
		botonFiltrar.setForeground(Color.WHITE); 
		botonFiltrar.setFont(new Font("Arial", Font.BOLD, 14)); 
		panel_4.add(botonFiltrar);
		botonFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//panel_3.getParent().setVisible(false);
				panelFavorito.setVisible(false);
				panelPeliculas.setVisible(false);
				panelLista.setVisible(false);
				//panelFiltro.setVisible(false);
				
				panel_3.add(panelFiltro, BorderLayout.CENTER);
				panelFiltro.setVisible(true);
				
			}
		});		
		
		
		
		
		
	}

}
