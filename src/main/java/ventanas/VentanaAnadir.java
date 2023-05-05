package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.client.Cliente;
import es.deusto.spq.server.jdo.Genero;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class VentanaAnadir extends JFrame{


	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textTitulo;
	private JTextField textMinutos;
	private JTextField textValoracion;

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
	public VentanaAnadir() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 439);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Añadir Pelicula");		
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setForeground(Color.WHITE);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		System.out.println("a");
		JComboBox<Genero> comboBox = new JComboBox<Genero>();
		for (Genero td : Genero.values()) {
			comboBox.addItem(td);
		}
		comboBox.setBounds(200, 263, 96, 22);
		panel_2.add(comboBox);
		
		
		JButton btnCancelar = new JButton("Cancelar");
		panel_1.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdmin Vadmin = new VentanaAdmin();
				Vadmin.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton btnAnadir = new JButton("Añadir");
		panel_1.add(btnAnadir);
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textCodigo.getText().isEmpty() && !textTitulo.getText().isEmpty() && !textMinutos.getText().isEmpty() && !textValoracion.getText().isEmpty()) {
					int minutos = Integer.parseInt(textMinutos.getText());
					int valoracion = Integer.parseInt(textValoracion.getText());
					Genero genero = (Genero) comboBox.getSelectedItem();
					boolean inicio = Cliente.addPelicula(textCodigo.getText(), textTitulo.getText(), minutos, valoracion, genero);
					if(inicio) {
						JOptionPane.showMessageDialog(null, "Pelicula añadida correctamente", "Añadir Pelicula", JOptionPane.INFORMATION_MESSAGE);

						
					}else {
						JOptionPane.showMessageDialog(null, "Error al añadir Pelicula", "Añadir Pelicula", JOptionPane.ERROR_MESSAGE);
						
					}
				}else {
					JOptionPane.showMessageDialog(null,  "Añada todos los campos", "Hay campos vacios", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(98, 40, 49, 14);
		panel_2.add(lblCodigo);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(200, 37, 96, 20);
		panel_2.add(textCodigo);
		textCodigo.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setBounds(98, 91, 49, 14);
		panel_2.add(lblTitulo);
		
		textTitulo = new JTextField();
		textTitulo.setBounds(200, 88, 96, 20);
		panel_2.add(textTitulo);
		textTitulo.setColumns(10);
		
		JLabel lblMinutos = new JLabel("Minutos");
		lblMinutos.setBounds(98, 149, 49, 14);
		panel_2.add(lblMinutos);
		
		textMinutos = new JTextField();
		textMinutos.setBounds(200, 146, 96, 20);
		panel_2.add(textMinutos);
		textMinutos.setColumns(10);
		
		JLabel lblValoracion = new JLabel("Valoración");
		lblValoracion.setBounds(98, 209, 71, 14);
		panel_2.add(lblValoracion);
		
		textValoracion = new JTextField();
		textValoracion.setBounds(200, 206, 96, 20);
		panel_2.add(textValoracion);
		textValoracion.setColumns(10);
		
		JLabel lblGenero = new JLabel("Género");
		lblGenero.setBounds(98, 267, 49, 14);
		panel_2.add(lblGenero);	
	}
}
