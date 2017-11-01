package ru.nt202;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToValues {
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File(ImageToValues.class.getResource("/r6625.png").getPath()));
        for (int j = 0; j < image.getWidth(); j += 5) {
            for (int i = 0; i < image.getHeight(); i += 1) {
                Color color = new Color(image.getRGB(j, i));
                if (color.getGreen() != 255) {
                    System.out.println(j + 400 + " " + (image.getHeight() - i));
//                    System.out.println(image.getRGB(j, i));
                    break;
                }
            }
        }

    }
}
