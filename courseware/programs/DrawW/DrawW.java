//
// Write the code to draw a symmetrical letter W with 4 line segments.  
// Start the upper left hand corner at (0,0) 
// The total width will be 40 and the height will be 30.
// The drawing should look like this:
// http://i.imgur.com/kIwzrNn.png
//
// Check out the video segment "Draw a Crate" for inspiration
// https://www.udacity.com/course/viewer#!/c-cs046/l-161445800/e-177304176/m-177304177
//    
//
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

public class DrawW
{
   public static void main(String[] args)
   {
       // TODO: Write the code to draw a symmetrical letter W with 4 line segments.
       Canvas.getInstance().snapshot();
       CheckOutput out = new CheckOutput();
       out.compareOutput();
   }
}

class CheckOutput{
    public void compareOutput()
   {
        String file1 = "resources\\courseware\\program_images\\answer.png";
        String file2 = "resources\\courseware\\program_images\\DrawW.png";
         
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