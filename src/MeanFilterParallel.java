import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import javax.imageio.ImageIO;

public class MeanFilterParallel extends RecursiveAction {
    int lo; // arguments
    int hi;
    String[] arr;
    static final int SEQUENTIAL_CUTOFF = 50000;
    int widthq;
    int le;
    static long num2;

    public MeanFilterParallel(String[] a, int l, int h, int widthq) {

        lo = l;
        hi = h;
        arr = a;
        this.widthq = widthq;
        le = h;

    }

    @Override
    protected void compute() {

        BufferedImage image = null;
        File f = new File("image2.jpg");

        try {

            image = ImageIO.read(f);

        } catch (IOException e) {

            System.out.println("No such image on the directory");
        }

        int width = image.getWidth();
        int height = image.getHeight();

        int p = 0;
        int a = 0;
        int r = 0;
        int g = 0;
        int b = 0;
        if (le < SEQUENTIAL_CUTOFF) {
            int count = 0;

            int k = 0;
            while (lo < hi) {

                int nn = 0;
                int nn2 = 0;

                String[] wor = arr[count].split(" ");
                nn = Integer.parseInt(wor[0]);
                nn2 = Integer.parseInt(wor[1]);
                // System.out.println(coordinates[k]);
                for (int ki = 0; ki < widthq; ki++) {
                    for (int kj = 0; kj < widthq; kj++) {

                        try {
                            p = image.getRGB(nn + ki, nn2 + kj);
                            a = a + ((p >> 24) & 0xff);
                            r = r + ((p >> 16) & 0xff);
                            g = g + ((p >> 8) & 0xff);
                            b = b + (p & 0xff);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            continue;
                        }

                    }
                }
                try {
                    int dar = ((widthq * widthq));
                    // System.out.println(r);
                    p = (a / dar << 24) | (r / dar << 16) | (g / dar << 8) | b / dar;
                    image.setRGB(nn, nn2, p);
                    a = 0;
                    r = 0;
                    g = 0;
                    b = 0;
                }

                catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }

                count++;
                lo++;
                k++;

            }
            long num2 = System.currentTimeMillis();

            f = new File("image3.jpg");
            try

            {
                ImageIO.write(image, "jpg", f);
            } catch (IOException e) {

                e.printStackTrace();
            }
            // long num2 = System.currentTimeMillis();
        }

        else {

            int split = le / 2;
            MeanFilterParallel left = new MeanFilterParallel(arr, lo, split, widthq);
            MeanFilterParallel right = new MeanFilterParallel(arr, split + lo, le - split, widthq);
            left.fork(); // this

            right.compute();
            left.join();
            // is very
            // important.

        }

        num2 = System.currentTimeMillis();
    }

    public static void main(String[] args) {

        int widthq = 33;

        BufferedImage image = null;
        File f = new File("image2.jpg");

        try {

            image = ImageIO.read(f);

        } catch (IOException e) {

            System.out.println("No such image on the directory");
        }
        int width = image.getWidth();
        int height = image.getHeight();

        String[] coordinates = new String[width * height];

        int counter = 0;
        long num1 = System.currentTimeMillis();
        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {

                coordinates[counter] = i + " " + j;
                // System.out.println(coordinates[counter]);
                counter++;

            }

        }
        MeanFilterParallel mf = new MeanFilterParallel(coordinates, 0, coordinates.length, widthq);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(mf);

        System.out.println("Took about " + (num2 - num1) / (float) 1000 + " seconds");

        System.out.println("Done");
    }
}