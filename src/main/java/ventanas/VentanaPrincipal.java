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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JScrollPane scrollTabla;
	private Object[] columna = new Object[7];

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
		//panel_3.add(panelPeliculas, BorderLayout.CENTER);
		
		JPanel panelLista = new JPanel();
		panelLista.setBackground(Color.pink);
		//panel_3.add(panelLista, BorderLayout.CENTER);
		
		
		// PANEL FILTRO-----------------------------------------
		JPanel panelFiltro = new JPanel();
		panelFiltro.setBackground(Color.green);
		panel_3.add(panelFiltro, BorderLayout.CENTER);
		panelFiltro.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panelFiltro.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new GridLayout(2, 1));
		
		//panel filtro combobox
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(new GridLayout(1, 2));
		panel_5.add(panel_6);
		
		JLabel lblNewLabel = new JLabel("Filtrar por...");
		panel_6.add(lblNewLabel);
		String[] opciones = {" ", "Título", "Género", "Valoración"};
        
        // Creamos un JComboBox y le pasamos las opciones
		//JComboBox comboBox = new JComboBox<>();
       JComboBox<String> comboBox = new JComboBox<>(opciones);
		panel_6.add(comboBox);
		
		
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(218, 218, 218));
		panel_7.setLayout(new GridLayout(1, 2));
		panel_5.add(panel_7);
		
		//Panel titulo
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(218, 218, 218));
		panelTitulo.setLayout(new GridLayout(1, 2));
		//panel_7.add(panelTitulo);
		
		JLabel lblNewLabelTitulo = new JLabel("Nombre Película");
		panelTitulo.add(lblNewLabelTitulo);
		
		txtNombre = new JTextField();
		panelTitulo.add(txtNombre, BorderLayout.SOUTH);
		txtNombre.setColumns(10);
		
		//Panel vacío
		JPanel panelVacio = new JPanel();
		panelVacio.setBackground(new Color(218, 218, 218));
		panel_7.add(panelVacio);
		
		//Panel Genero
		JPanel panelGenero = new JPanel();
		panelGenero.setBackground(new Color(218, 218, 218));
		panelGenero.setLayout(new GridLayout(1, 2));
		//panel_7.add(panelTitulo);
		
		JLabel lblNewLabelGenero = new JLabel("Género");
		panelGenero.add(lblNewLabelGenero);
		String[] opcionesGenero = {"", "Terror", "Comedia", "Ciencia ficcion", "Accion", "Aventura", "Drama", "Musical"};      
        // Creamos un JComboBox y le pasamos las opciones
		 JComboBox<String> comboBoxGenero = new JComboBox<>(opcionesGenero);
       // JComboBox<String> comboBox = new JComboBox<>(opciones);
		panelGenero.add(comboBoxGenero);
		
		txtNombre = new JTextField();
		panelFiltro.add(txtNombre, BorderLayout.SOUTH);
		txtNombre.setColumns(10);
		
		
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) comboBox.getSelectedItem();
                if (opcionSeleccionada.equals(" ")) {
                	panelTitulo.setVisible(false);	
                    panelGenero.setVisible(false);
                    panelVacio.setVisible(true);
                    // Quitamos los paneles del contenedor original antes de agregarlos a panel_7
                    panel_7.removeAll();
                    panel_7.add(panelVacio);
                    panel_7.setVisible(true);
               
                }else if(opcionSeleccionada.equals("Título")) {
                	// Mostramos el panel de título y ocultamos el de género
                    panelTitulo.setVisible(true);	
                    panelGenero.setVisible(false);
                    panelVacio.setVisible(false);
                    // Quitamos los paneles del contenedor original antes de agregarlos a panel_7
                    panel_7.removeAll();
                    panel_7.add(panelTitulo);
                    panel_7.setVisible(true);
                	
                }else if(opcionSeleccionada.equals("Género")) {
                	// Mostramos el panel de género y ocultamos el de título
                    panelTitulo.setVisible(false);	
                    panelGenero.setVisible(true);
                    panelVacio.setVisible(false);
                    // Quitamos los paneles del contenedor original antes de agregarlos a panel_7
                    panel_7.removeAll();
                    panel_7.add(panelGenero);
                    panel_7.setVisible(true);
                	
                }else if(opcionSeleccionada.equals("Valoración")) {
                	
                }
            }
        });
		
		
		//Creamos la JTable
		modeloTabla = new DefaultTableModel();
		table = new JTable(modeloTabla);
		//Creamos las columnas
		modeloTabla.addColumn("Cod");
		modeloTabla.addColumn("Titulo");
		modeloTabla.addColumn("Minutos");
		modeloTabla.addColumn("Valoración");
		modeloTabla.addColumn("Género");

				
		// JSCROLLPANE Y A�ADIR LA TABLA
		scrollTabla = new JScrollPane(table);
		scrollTabla.setVisible(true);
		panelFiltro.add(scrollTabla, BorderLayout.CENTER);
					
		scrollTabla.getViewport().setBackground(new Color(204,204,204));
		
		
		
		
		
		
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
