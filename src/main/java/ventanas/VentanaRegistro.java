package ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.deusto.spq.client.ExampleClient;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class VentanaRegistro extends JFrame {
	
	protected static final Logger logger = LogManager.getLogger();

	private JPanel contentPane;
	private JTextField nombreTxt;
	private JTextField emailTxt;
    private JTextField contrTxt;
    private JTextField contr2Txt;
    private String hostname;
    private String port;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
//		if (args.length != 2) {
//			logger.info("Use: java Client.Client [host] [port]");
//			System.exit(0);
//		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					String hostname = args[0];
//					String port = args[1];
//					VentanaRegistro frame = new VentanaRegistro(hostname, port);
					VentanaRegistro frame = new VentanaRegistro();
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
//	public VentanaRegistro(String arg1, String arg2) {
//		this.hostname = arg1;
//		this.port = arg2;
	
	public VentanaRegistro() {
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
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		
		JPanel panelRegistro = new JPanel();
		panelRegistro.setBackground(Color.DARK_GRAY);
		panel_2.add(panelRegistro, BorderLayout.CENTER);
		panelRegistro.setLayout(null);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setForeground(Color.WHITE);
		labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelNombre.setBounds(55, 92, 129, 14);
		panelRegistro.add(labelNombre);
		
		nombreTxt = new JTextField();
		nombreTxt.setBounds(55, 117, 189, 25);
		panelRegistro.add(nombreTxt);
		nombreTxt.setColumns(10);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailLabel.setBounds(55, 153, 129, 14);
		panelRegistro.add(emailLabel);
		
		emailTxt = new JTextField();
		emailTxt.setBounds(55, 178, 189, 23);
		panelRegistro.add(emailTxt);
		emailTxt.setColumns(10);
		
		JButton registroBoton = new JButton("Aceptar");
		registroBoton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = nombreTxt.getText();
				String password = contrTxt.getText();
				String email = emailTxt.getText();
				

				System.out.println("LLega 1");
				ExampleClient instance = new ExampleClient(hostname, port);
				System.out.println("LLega 2");
			
				if (password.equals(contr2Txt.getText())) {
					instance.registerUser(usuario, password, email);
					JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "Registro", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);

				}
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

	}
	


}
