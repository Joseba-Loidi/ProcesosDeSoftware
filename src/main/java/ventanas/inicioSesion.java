package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class inicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panelInicio;
	
	//REGISTRO
    private JPanel panelRegistro;
    private JTextField nombreTxt;
	private JTextField apellidoTxt;
    private JTextField contrTxt;
    private JTextField contr2Txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicioSesion frame = new inicioSesion();
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
	public inicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 478);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "name_843886139607100");
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setForeground(new Color(0, 64, 64));
		panel.add(panel_1);
		
		//poner la imagen
		ImageIcon imageIcon = new ImageIcon("img//ejemplo1.jpg");
		
		JLabel labelImagen = new JLabel(imageIcon);
		//labelImagen.setPreferredSize(new Dimension(panel_1.getWidth(), panel_1.getHeight()));
		// panel_1.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
		panel_1.add(labelImagen);
		labelImagen.setIcon(imageIcon);
		//ImageIcon imageIcon = new ImageIcon("/ProcesosDeSoftware/src/main/java/imgs/fondo_inicio2.jpg");
		panel_1.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(55, 105, 129, 14);
		panel_3.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(55, 133, 210, 27);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(55, 183, 129, 14);
		panel_3.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(55, 208, 210, 27);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setBounds(81, 266, 163, 27);
		panel_3.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("¿No tiene cuenta en DeustoPrime?");
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setBounds(10, 334, 203, 14);
		panel_3.add(lblNewLabel_3);
		
	
		
		JLabel lblNewLabel_4 = new JLabel("INICIO SESION");
		lblNewLabel_4.setBounds(66, 41, 338, 26);
		panel_3.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_4.setBackground(Color.DARK_GRAY);

		
		//PANEL REGISTRO
		JPanel panelRegistro = new JPanel();
		panelRegistro.setBackground(Color.DARK_GRAY);
		//panel_2.add(panelRegistro, BorderLayout.CENTER);
		panelRegistro.setLayout(null);
		panelRegistro.setVisible(false);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setForeground(Color.WHITE);
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelNombre.setBounds(55, 92, 129, 14);
		panelRegistro.add(labelNombre);
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(55, 117, 189, 25);
		panelRegistro.add(nombreTxt);
		nombreTxt.setColumns(10);
		
		JLabel apellidoLabel = new JLabel("Apellido");
		apellidoLabel.setForeground(Color.WHITE);
		apellidoLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		apellidoLabel.setBounds(55, 153, 129, 14);
		panelRegistro.add(apellidoLabel);
		
		apellidoTxt = new JTextField();
		apellidoTxt.setBounds(55, 178, 189, 23);
		panelRegistro.add(apellidoTxt);
		apellidoTxt.setColumns(10);
		
		JButton registroBoton = new JButton("Aceptar");
		registroBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(true);
				panelRegistro.setVisible(false);
				panel_2.add(panel_3, BorderLayout.CENTER);
				
			}
		});
		registroBoton.setForeground(Color.WHITE);
		registroBoton.setBackground(SystemColor.activeCaption);
		registroBoton.setBounds(81, 356, 163, 27);
		panelRegistro.add(registroBoton);
		
		JLabel registroLabel = new JLabel("REGISTRO");
		registroLabel.setBounds(66, 41, 338, 26);
		panelRegistro.add(registroLabel);
		registroLabel.setForeground(Color.LIGHT_GRAY);
		registroLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		registroLabel.setBackground(Color.DARK_GRAY);
		
		JLabel contrLabel = new JLabel("Contraseña");
		contrLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contrLabel.setForeground(Color.WHITE);
		contrLabel.setBounds(55, 212, 129, 14);
		panelRegistro.add(contrLabel);
		
		contrTxt = new JTextField();
		contrTxt.setBounds(55, 237, 189, 23);
		panelRegistro.add(contrTxt);
		contrTxt.setColumns(10);
		
		JLabel contr2Label = new JLabel("Repita contraseña");
		contr2Label.setForeground(Color.WHITE);
		contr2Label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contr2Label.setBounds(55, 271, 153, 25);
		panelRegistro.add(contr2Label);
		
		contr2Txt = new JTextField();
		contr2Txt.setBounds(55, 307, 189, 23);
		panelRegistro.add(contr2Txt);
		contr2Txt.setColumns(10);
		
		
		JButton btnNewButton_1 = new JButton("Registrarse");
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setBounds(196, 330, 89, 23);
		panel_3.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_3.setVisible(false);
				panelRegistro.setVisible(true);
				panel_2.add(panelRegistro, BorderLayout.CENTER);
				
			}
		});
	}
}
