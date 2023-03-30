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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class VentanaRegistro extends JFrame {

	private JPanel contentPane;
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
