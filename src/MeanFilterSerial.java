import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class MeanFilterSerial{

    public static void main(String[] args){
        




        /*
        *the following lines of codes is for 
        *reading the image
        */ 

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter <inputImageName> <outputImageName> <windowWidth>");

        String[] data =  input.nextLine().split(" ");

        String inputImg = data[0];
        String outputImg = data[1];
        int widthq = Integer.parseInt(data[2]);


        
        
        BufferedImage image  = null;
        File f  = new File(inputImg);
        try {

            image = ImageIO.read(f);

        } catch (IOException e) {

            System.out.println("No such image on the directory");
        }

        //to write image 
        int width =  image.getWidth();
        int height = image.getHeight();

        int a  =  0;
        int r = 0;
        int g = 0;
        int b =0;
        //f = new File("data/output.jpg");

        int counter = 0;
        int p = 0;
        for(int i = widthq-2 ; i<  width-1; i++ ){
            
            for(int j = widthq-2; j<height-1; j++){
                
               for(int ki = 0; ki<widthq;ki++){
                   
                   for(int kj = 0; kj<widthq;kj++){

                        p = image.getRGB(i+ ki -(widthq-2), j+ kj-(widthq-2) );
                        a =  a + ((p>>24)&0xff);
                        r = r + ((p>>16)&0xff);
                        g = g+ ((p>>8)&0xff);     
                        b = b+ (p&0xff);
                       // System.out.println(r.get(counter));
                        counter++;
                   }  
                        
               }
              
               
               
               int dar  = ((widthq*widthq)/2);

               p = (a<<24) | (r/counter<<16) | (g/counter<<8) | b/counter;
               image.setRGB(i-(widthq-2), j-(widthq-2), p);

              counter =0;
              a = 0;
              r=0;
              g=0;
              b=0;
             
               
            }

        }
        //get pixel value

        f = new File(outputImg);
        try {
            ImageIO.write(image, "jpg", f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    
}
