package ru.nt202;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageToValues {
    public static void main(String[] args) throws IOException {

        BufferedImage image = ImageIO.read(
                new File(ImageToValues.class.getResource("/lab2/object.PNG").getPath()));

        int width = image.getWidth();
        int height = image.getHeight();

        System.out.println("width = " + width);
        System.out.println("height = " + height);


        int values[] = new int[width];

        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i += 1) {
                Color color = new Color(image.getRGB(j, i));
//                System.out.println(color.getGreen());
                if (color.getGreen() != 255) {
                    values[j] = height - i;
                }
            }
        }

        int minValue = height;
        int maxValue = 0;
        for (int i = 0; i < width; i++) {
            if (values[i] < minValue) {
                minValue = values[i];
            }
            if (values[i] > maxValue) {
                maxValue = values[i];
            }
        }

        System.out.println("minValue = " + minValue);
        System.out.println("maxValue = " + maxValue);


        int x0 = 400; // CHANGE
        int step = 5; // CHANGE
        double max = 0.315; // CHANGE
        double min = 0.065; // CHANGE
        for (int i = 0; i < width; i += step) {
            double value = min + ((values[i] - minValue) * max / maxValue);
            System.out.println(x0 + i + " " + value);
        }
    }
}
