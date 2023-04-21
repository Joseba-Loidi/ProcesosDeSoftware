package ventanas;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import es.deusto.spq.client.Cliente;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
		//---------------- PANEL FAVORITO-----------------------------------------
		JPanel panelFavorito = new JPanel();
		panelFavorito.setBackground(Color.CYAN);
		//panel_3.add(panelFavorito, BorderLayout.CENTER);	
		
		//---------------- PANEL PELICULAS-----------------------------------------
		
		JPanel panelPeliculas = new JPanel();
		panelPeliculas.setBackground(Color.RED);
		//panel_3.add(panelPeliculas, BorderLayout.CENTER);
		
		//---------------- PANEL LISTA-----------------------------------------
		
		JPanel panelLista = new JPanel();
		panelLista.setBackground(Color.pink);
		//panel_3.add(panelLista, BorderLayout.CENTER);
		
		
		//---------------- PANEL FILTRO-----------------------------------------
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
		//String[] opcionesGenero = {Genero.val};      
        // Creamos un JComboBox y le pasamos las opciones
		 JComboBox<Genero> comboBoxGenero = new JComboBox<>(Genero.values());
       // JComboBox<String> comboBox = new JComboBox<>(opciones);
		panelGenero.add(comboBoxGenero);
		
		txtNombre = new JTextField();
		panelFiltro.add(txtNombre, BorderLayout.SOUTH);
		txtNombre.setColumns(10);
		
		//Panel valoración
		JPanel panelValoración = new JPanel();
		panelValoración.setBackground(new Color(218, 218, 218));
		panelValoración.setLayout(new GridLayout(1, 2));
		//panel_7.add(panelTitulo);
		
		JLabel lblValoracion = new JLabel("Valoración");
		panelValoración.add(lblValoracion);
		
	
		JPanel panelRadio = new JPanel();
		panelRadio.setBackground(new Color(218, 218, 218));
		panelRadio.setLayout(new GridLayout(1, 5));
		
		// Creamos los JRadioButton
		JRadioButton radio1 = new JRadioButton("1");
		JRadioButton radio2 = new JRadioButton("2");
		JRadioButton radio3 = new JRadioButton("3");
		JRadioButton radio4 = new JRadioButton("4");
		JRadioButton radio5 = new JRadioButton("5");

        // Creamos un grupo para los JRadioButton
		ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radio1);
        buttonGroup.add(radio2);
        buttonGroup.add(radio3);
        buttonGroup.add(radio4);
        buttonGroup.add(radio5);

        // Añadimos los JRadioButton a la ventana
        panelValoración.add(radio1);
        panelValoración.add(radio2);
        panelValoración.add(radio3);
        panelValoración.add(radio4);
        panelValoración.add(radio5);
        
        panelGenero.add(panelValoración);
		
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) comboBox.getSelectedItem();
                if (opcionSeleccionada.equals(" ")) {
                	panelTitulo.setVisible(false);	
                    panelGenero.setVisible(false);
                    panelVacio.setVisible(true);
                    panelValoración.setVisible(false);
                    // Quitamos los paneles del contenedor original antes de agregarlos a panel_7
                    panel_7.removeAll();
                    panel_7.add(panelVacio);
                    panel_7.setVisible(true);
               
                }else if(opcionSeleccionada.equals("Título")) {
                	// Mostramos el panel de título y ocultamos el de género
                    panelTitulo.setVisible(true);	
                    panelGenero.setVisible(false);
                    panelVacio.setVisible(false);
                    panelValoración.setVisible(false);
                    // Quitamos los paneles del contenedor original antes de agregarlos a panel_7
                    panel_7.removeAll();
                    panel_7.add(panelTitulo);
                    panel_7.setVisible(true);
                	
                }else if(opcionSeleccionada.equals("Género")) {
                	// Mostramos el panel de género y ocultamos el de título
                    panelTitulo.setVisible(false);	
                    panelGenero.setVisible(true);
                    panelVacio.setVisible(false);
                    panelValoración.setVisible(false);
                    // Quitamos los paneles del contenedor original antes de agregarlos a panel_7
                    panel_7.removeAll();
                    panel_7.add(panelGenero);
                    panel_7.setVisible(true);
                	
                }else if(opcionSeleccionada.equals("Valoración")) {
                	// Mostramos el panel de género y ocultamos el de título
                    panelTitulo.setVisible(false);	
                    panelGenero.setVisible(false);
                    panelVacio.setVisible(false);
                    panelValoración.setVisible(true);
                    // Quitamos los paneles del contenedor original antes de agregarlos a panel_7
                    panel_7.removeAll();
                    panel_7.add(panelValoración);
                    panel_7.setVisible(true);
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
		
		
		JPanel panelBotonFiltro = new JPanel();
		panelFiltro.add(panelBotonFiltro, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(1, 1));
		
		JButton filtrarBoton = new JButton("Filtrar");
		panelBotonFiltro.add(filtrarBoton);
		panelBotonFiltro.add(filtrarBoton);
		
		filtrarBoton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(panelTitulo.isVisible()) { 
					table.removeAll();
					String nombre = txtNombre.getText();
					cargarTablaNombre(nombre);
					repaint();
				} else if (panelGenero.isVisible()){
					Genero genero = (Genero) comboBoxGenero.getSelectedItem();
					cargarTablaGenero(genero);

				}
				
			}
		});
		
		//--------------------------------------------------------------
		
		
		
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
	
	
	//METODOS PARA VISUALIZAR FILTRADO EN LA TABLA
	public void cargarTablaNombre(String nombre) {
		modeloTabla.setRowCount(0);
		try {
			List<Pelicula> listaPelis = Cliente.filtrarNombre(nombre);
			for (Pelicula pelicula : listaPelis) {
				System.out.println(pelicula.toString());
				columna[0] = pelicula.getCodigo();
				columna[1] = pelicula.getTitulo();
				columna[2] = pelicula.getMinutos();
				columna[3] = pelicula.getValoracion();
				columna[4] = pelicula.getGenero();
				modeloTabla.addRow(columna);// agregamos una fila a nuestro modelo de tabla
			}
		
		} catch (Exception e) {
			System.out.println("No se puede rellenar la tabla");
			e.printStackTrace();
		}
		
	}
		public void cargarTablaGenero(Genero genero) {
			 modeloTabla.setRowCount(0);
			try {
				List<Pelicula> listaPelis = Cliente.filtrarGenero(genero);
				for (Pelicula pelicula : listaPelis) {
					System.out.println(pelicula.toString());
					columna[0] = pelicula.getCodigo();
					columna[1] = pelicula.getTitulo();
					columna[2] = pelicula.getMinutos();
					columna[3] = pelicula.getValoracion();
					columna[4] = pelicula.getGenero();
					modeloTabla.addRow(columna);// agregamos una fila a nuestro modelo de tabla
				}
			repaint();
			} catch (Exception e) {
				System.out.println("No se puede rellenar la tabla");
				e.printStackTrace();
			}

}
}
