package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Utils {

    public static ImageIcon resize(BufferedImage originalImage, int height, int width) {
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizeImage = new BufferedImage(width, height, type);
        Graphics2D g = resizeImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return new ImageIcon(resizeImage);
    }

    public static BufferedImage getImage(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }

    public static ImageIcon imageIcon(String filePath, int height, int width) throws IOException {
        BufferedImage orig = getImage(filePath);
        return resize(orig, height, width);
    }
}
