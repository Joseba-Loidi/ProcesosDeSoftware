package ventanas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;

public class ProgressWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel progressPanel;
    private JProgressBar progressBar;
    private Timer timer;
    private JLabel imageLabel;
    private ImageIcon[] images;
    private Random random;

    public ProgressWindow() {
        super("Ventana de Progreso");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel();
        mainPanel.add(imageLabel, BorderLayout.CENTER);

        progressPanel = new JPanel(new BorderLayout());
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressPanel.add(progressBar, BorderLayout.CENTER);
        mainPanel.add(progressPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        setSize(400, 300);
        setLocationRelativeTo(null);

        images = new ImageIcon[12]; // Cambiar numero según numero de fotos!!!!
        for (int i = 0; i < images.length; i++) {
            String imagePath = "img/imagen" + (i + 1) + ".jpg";
            images[i] = resizeImageIcon(imagePath, getWidth(), getHeight());
        }

        random = new Random();

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (progressBar.getValue() < 100) {
                    int progress = progressBar.getValue() + 10;
                    progressBar.setValue(progress);
                    int randomIndex = random.nextInt(images.length);
                    imageLabel.setIcon(images[randomIndex]);
                } else {
                    timer.stop();
                    dispose();
                }
            }
        });

        timer.start();
    }

    private ImageIcon resizeImageIcon(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProgressWindow progressWindow = new ProgressWindow();
                progressWindow.setVisible(true);
            }
        });
    }
}



