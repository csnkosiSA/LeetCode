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

        ArrayList<Integer> a  =  new ArrayList<Integer>();
        ArrayList<Integer> r  =  new ArrayList<Integer>();
        ArrayList<Integer> g  =  new ArrayList<Integer>();
        ArrayList<Integer> b  =  new ArrayList<Integer>();
        //f = new File("data/output.jpg");

        int counter = 0;
        int p = 0;
        for(int i = widthq-2 ; i<  width-1; i++ ){
            
            for(int j = 9; j<height-1; j++){

               for(int ki = 0; ki<widthq;ki++){
                   
                   for(int kj = 0; kj<widthq;kj++){

                        p = image.getRGB(i+ ki -(widthq-2), j+ kj-(widthq-2) );
                        a.add((p>>24)&0xff);
                        r.add((p>>16)&0xff);
                        g.add((p>>8)&0xff);     
                        b.add(p&0xff);
                       // System.out.println(r.get(counter));
                        counter++;
                   }  
                        
               }
               counter = 0;
               
               Collections.sort(r);
               Collections.sort(g);
               Collections.sort(b);
               int dar  = (widthq*widthq)/2;

               p = (a.get(dar)<<24) | (r.get(dar)<<16) | (g.get(dar)<<8) | b.get(dar);
               image.setRGB(i-(widthq-2), j-(widthq-2), p);

               a.clear();
               r.clear();
               g.clear();
               b.clear();
             
               
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
