package tagger.views;

import javafx.util.Pair;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TokenPanel extends JPanel {

    private BufferedImage image;
    private int radiusX = 0;
    private int radiusY = 0;

    TokenPanel(String tokenPath) {
        try {
            image = ImageIO.read(new File(tokenPath));
            radiusX = image.getWidth() / 2;
            radiusY = image.getHeight() / 2;
        } catch (IOException ex) {
            //
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, (getWidth() / 2) - radiusX, (getHeight() / 2) - radiusY, this);
    }

}