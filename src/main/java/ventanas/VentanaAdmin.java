package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import es.deusto.spq.client.Cliente;
import es.deusto.spq.server.jdo.Genero;
import es.deusto.spq.server.jdo.Pelicula;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JTextField;

public class VentanaAdmin extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JScrollPane scrollTabla;
	private Object[] columna = new Object[7];
	private String codigo;
	private String titulo;
	private int minutos ;
	private int valoracion;
	private Genero genero;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin frame = new VentanaAdmin();
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
	public VentanaAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 478);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Peliculas");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.WHITE);
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		
		
		
		
		//Creamos la JTable
		modeloTabla = new DefaultTableModel();
		table = new JTable(modeloTabla);
	//Creamos las columnas
		modeloTabla.addColumn("Cod");
		modeloTabla.addColumn("Titulo");
		modeloTabla.addColumn("Minutos");
		modeloTabla.addColumn("Valoración");
		modeloTabla.addColumn("Género");
		
		cargarTabla();
		
		// JSCROLLPANE Y A�ADIR LA TABLA
		scrollTabla = new JScrollPane(table);
		scrollTabla.setVisible(true);
		panel.add(scrollTabla, BorderLayout.CENTER);
		
	
		scrollTabla.getViewport().setBackground(new Color(204,204,204));
		
		
		
		JPanel panelBtn = new JPanel();
		panelBtn.setBackground(Color.DARK_GRAY);
		panelBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel.add(panelBtn, BorderLayout.SOUTH);
		
		JButton anadir = new JButton("Añadir");
		anadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAnadir Vanadir = new VentanaAnadir();
				setVisible(false);
				Vanadir.setVisible(true);
			}
		});
		
		anadir.setForeground(Color.WHITE);
		anadir.setBackground(SystemColor.activeCaption);
		anadir.setBounds(81, 266, 163, 27);
		panelBtn.add(anadir);
		
		
		
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
				int selectedRowIndex = table.getSelectedRow();
				
				codigo = modeloTabla.getValueAt(selectedRowIndex, 0).toString();
				titulo = modeloTabla.getValueAt(selectedRowIndex, 1).toString();
				String min = modeloTabla.getValueAt(selectedRowIndex, 2).toString();
				minutos = Integer.parseInt(min);
				String val = modeloTabla.getValueAt(selectedRowIndex, 3).toString();
				valoracion = Integer.parseInt(val);
				String gen = modeloTabla.getValueAt(selectedRowIndex, 4).toString();
				genero = Genero.valueOf(gen);
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	        
	        
	    });
		
		JButton eliminar = new JButton("Eliminar");
		eliminar.setForeground(Color.WHITE);
		eliminar.setBackground(SystemColor.activeCaption);
		eliminar.setBounds(81, 266, 163, 27);
		panelBtn.add(eliminar);
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					boolean inicio = Cliente.eliminarPelicula(codigo, titulo, minutos, valoracion, genero);
					if(inicio) {
						JOptionPane.showMessageDialog(null, "Pelicula eliminada correctamente", "Añadir Pelicula", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						VentanaAdmin va = new VentanaAdmin();
						va.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Error al eliminar la Pelicula", "Añadir Pelicula", JOptionPane.ERROR_MESSAGE);
						
					}
				
			}
		});
		
		
		setVisible(true);
	}

	public void cargarTabla() {
		table.removeAll();
		try {
			List<Pelicula> listaPelis = Cliente.obtenerPelis();
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
	
	
}
