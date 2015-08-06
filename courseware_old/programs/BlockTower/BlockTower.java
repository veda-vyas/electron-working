// Write the code to draw a tower of blocks that looks like the one in the link below:
// http://i.imgur.com/PvIGJkL.png
    
// Name the class BlockTower. Each block has a width of 40 and a height of 30. 
// There are three rows. The upper left-hand corner of the bottom row is at (20, 70)

// Create a custom color for the topmost color where red = 125, green=125, blue = 255. 
// For all the other colors, use predefined colors. 
// The colors from top to bottom are: custom, RED, PINK, BLUE, MAGENTA, CYAN


// HINT:
// The bottom left rectangle should have the attributes:
// x: 20
// y: 70
// width: 40
// height: 30
//
// You should set the color of the rectangle to blue by using the method:
// setColor(Color.somecolor); on the rectangle object that you created
//
// Don't forget to draw the rectangle by calling the fill() function




import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;


public class BlockTower
{
 public static void main(String[] args)
 {
        //Write your code here
         //bottom row
     Rectangle bottomLeft = new Rectangle(20, 70, 40, 30);
     Rectangle bottomMiddle = new Rectangle(60, 70, 40, 30);
     Rectangle bottomRight = new Rectangle(100, 70, 40, 30);
     
     bottomLeft.setColor(Color.BLUE);
     bottomMiddle.setColor(Color.MAGENTA);
     bottomRight.setColor(Color.CYAN);
     
     bottomLeft.fill();
     bottomMiddle.fill();
     bottomRight.fill();
     
     //middle row
     Rectangle middleLeft = new Rectangle(40, 40, 40, 30);
     Rectangle middleRight = new Rectangle(80, 40, 40, 30);
     
     middleLeft.setColor(Color.RED);
     middleRight.setColor(Color.PINK);
     
     middleLeft.fill();
     middleRight.fill();
     
     //top row
     Rectangle topmost = new Rectangle(60, 10, 40, 30);
     Color lol = new Color(125, 125, 255);
     topmost.setColor(lol);
     topmost.fill();
        Canvas.getInstance().snapshot();
    CheckOutput out = new CheckOutput();
    out.compareOutput(); 
 }
}
class CheckOutput{
    public void compareOutput()
   {
        String file1 = "resources\\courseware\\program_images\\answer.png";
        String file2 = "resources\\courseware\\program_images\\BlockTower.png";
         
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