// BlueJ project: lesson6/nested

// The x- and y- positions of the rectangles should start at
// 0, WIDTH, 2 * WIDTH, and so on.
// The colors should start at 0, end up at 255, and grow in
// equal increments.
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.util.Scanner;

public class nested
{
    public static void main(String[] args)
    {
        final int WIDTH = 10;
        final int ROWS = 16;
        final int COLUMNS = 16;

        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLUMNS; j++)
            {
                int x = ...;
                int y = ...;
                Rectangle rect = new Rectangle(x, y,
                                               WIDTH - 1, WIDTH - 1);
                int blue =...;
                int green =...;
                Color fillColor = new Color(0, green, blue);
                rect.setColor(fillColor);
                rect.fill();
            }
        }
        Canvas.getInstance().snapshot();
        CheckOutput out = new CheckOutput();
        out.compareOutput();
    }
}

class CheckOutput{
    public void compareOutput()
   {
        String file1 = "resources\\courseware\\program_images\\answer.png";
        String file2 = "resources\\courseware\\program_images\\nested.png";
         
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