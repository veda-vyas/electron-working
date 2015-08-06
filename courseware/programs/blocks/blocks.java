// BlueJ project: lesson7/blocks

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.util.Scanner;

public class Blocks
{
    public static void main(String[] args)
    {
        Picture pic = new Picture("eiffel-tower.jpg");
        int[][] pixels = pic.getGrayLevels();
        
        // TODO:
        // 1) Update the for loops below with the correct conditions
        // 2) Update the indices that we are using to access the 2D-array
        //    pixels, so we are using the correct elements from pixels
        
        for (int i = 0; ...; i = i + 2)
        {
            for (int j = 0; ...; ...)
            {
                int avg = (pixels[i][j] + pixels[...][...]
                    + pixels[...][...] + pixels[...][...]) / 4;
                pixels[...][...] = avg;
                pixels[...][...] = avg;
                pixels[...][...] = avg;
                pixels[...][...] = avg;                
            }
        }
        
        // You need not to change this below part
        pic.draw();
        Picture pic2 = new Picture(pixels);
        pic2.translate(pic.getWidth() + 10, 0);
        pic2.draw();
        Picture picture = new Picture();
        picture.snapshot();
        CheckOutput out = new CheckOutput();
        out.compareOutput();
    }
}
class CheckOutput{
    public void compareOutput()
   {
        String file1 = "resources\\courseware\\program_images\\answer.png";
        String file2 = "resources\\courseware\\program_images\\blocks.png";
         
        Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
        Image image2 = Toolkit.getDefaultToolkit().getImage(file2);
         
        try {
         
        PixelGrabber grab1 =new PixelGrabber(image1, 0, 0, -1, -1, false);
        PixelGrabber grab2 =new PixelGrabber(image2, 0, 0, -1, -1, false);
         
        int[] data1 = null;
         
        if (grab1.grabPixels()) {
        int width = grab1.getWidth();
        int height = grab1.getHeight();
        data1 = new int[width * height];
        data1 = (int[]) grab1.getPixels();
        }
         
        int[] data2 = null;
         
        if (grab2.grabPixels()) {
        int width = grab2.getWidth();
        int height = grab2.getHeight();
        data2 = new int[width * height];
        data2 = (int[]) grab2.getPixels();
        }
         
        System.out.println("Testcase Passed: " + java.util.Arrays.equals(data1, data2));
         
        } catch (InterruptedException e1) {
        e1.printStackTrace();
        }
 
   }
}