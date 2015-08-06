//
// Complete the given class to display the picture object vertically
// (rather than horizontally).
// You can find the pictures required in the bluej project
//

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.util.Scanner;

public class ListOfVerticalPictures
{
   public static void main(String[] args)
   {
      ArrayList<Picture> gallery = new ArrayList<Picture>();
      gallery.add(new Picture("resources//courseware//programs//ListOfPictures//degas1.jpg"));
      gallery.add(new Picture("resources//courseware//programs//ListOfPictures//gaugin1.jpg"));
      gallery.add(new Picture("resources//courseware//programs//ListOfPictures//monet1.jpg"));
      gallery.add(new Picture("resources//courseware//programs//ListOfPictures//monet2.jpg"));
      gallery.add(new Picture("resources//courseware//programs//ListOfPictures//renoir1.jpg"));

      // your code goes here
      // TODO Write the code to display the pictures vertically
     
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
        String file2 = "resources\\courseware\\program_images\\ListOfVerticalPictures.png";
         
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