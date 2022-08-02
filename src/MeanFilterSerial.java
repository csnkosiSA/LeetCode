import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeanFilterSerial{

    public static void main(String[] args){


        /*
        *the following lines of codes is for 
        *reading the image
        */ 
        
        BufferedImage image  = null;
        File f  = new File("data.jpg");
        try {

            image = ImageIO.read(f);

        } catch (IOException e) {

            System.out.println("No such image on the directory");
        }

        //to write image 
        int width =  image.getWidth();
        int height = image.getHeight();

        //f = new File("data/output.jpg");
        for(int y = 0 ; y<  height; y++ ){

            for(int x = 0; x<width; x++){

                int p = image.getRGB(x, y);
                int a = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;

                int avg = (r+g+b)/3;

                //replace RGB value with avg
                p = (a<<24) | (avg<<16) | (avg<<8) | avg;

                image.setRGB(x, y, p);

            }

        }
        //get pixel value

        f = new File("Output.jpg");
        try {
            ImageIO.write(image, "jpg", f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    
}