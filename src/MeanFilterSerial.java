import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.RecursiveAction;

import javax.imageio.ImageIO;

public class MeanFilterSerial {


    public static void main(String[] args) {

        /*
         * the following lines of codes is for
         * reading the image
         */

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter <inputImageName> <outputImageName> <windowWidth>");

        String[] data = input.nextLine().split(" ");

        String inputImg = data[0];
        String outputImg = data[1];
        int widthq = Integer.parseInt(data[2]);

        BufferedImage image = null;
        File f = new File(inputImg);
        try {

            image = ImageIO.read(f);

        } catch (IOException e) {

            System.out.println("No such image on the directory");
        }

        // to write image
        int width = image.getWidth();
        int height = image.getHeight();

        int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        // f = new File("data/output.jpg");

        int counter = 0;
        int p = 0;
        long num1 = System.currentTimeMillis();
        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {

                for (int ki = 0; ki < widthq; ki++) {

                    for (int kj = 0; kj < widthq; kj++) {
                        try {
                            p = image.getRGB(i + ki, j + kj);
                            a = a + ((p >> 24) & 0xff);
                            r = r + ((p >> 16) & 0xff);
                            g = g + ((p >> 8) & 0xff);
                            b = b + (p & 0xff);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue;
                        }
                        // System.out.println(r.get(counter));
                        counter++;
                    }

                }

                int dar = ((widthq * widthq) / 2);

                p = (a << 24) | (r / counter << 16) | (g / counter << 8) | b / counter;
                try {
                    image.setRGB(i, j, p);

                } catch (IndexOutOfBoundsException e) {
                    continue;
                }

                counter = 0;
                a = 0;
                r = 0;
                g = 0;
                b = 0;

            }

        }
        long num2 = System.currentTimeMillis();
        // get pixel value

        f = new File(outputImg);
        try {
            ImageIO.write(image, "jpg", f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Took about " + (num2 - num1) / (float) 1000 + " seconds");
        System.out.println("Done");
    }

}
