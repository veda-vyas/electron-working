// BlueJ project: lesson2/crate2
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

public class TopSecret
{
    public static void main(String[] args)
    {
        Rectangle frontFace = new Rectangle(20, 30 ,100, 40);
        Line leftLine = new Line(20, 30, 50, 10);
        Line topLine = new Line(50, 10, 150, 10);
        Line middleLine = new Line(120, 30, 150, 10);
        Line rightLine = new Line(150, 10, 150, 50);
        Line bottomLine = new Line(120, 70, 150, 50);

        frontFace.draw();
        leftLine.draw();
        topLine.draw();
        middleLine.draw();
        rightLine.draw();
        bottomLine.draw();
        
        // TODO: Write TOP SECRET on the front face of the box.
        // The top left corner of the front face should be the
        // same as the top left corner of the text.
        Canvas.getInstance().snapshot();
        CheckOutput out = new CheckOutput();
        out.compareOutput();
    }
}
class CheckOutput{
    public void compareOutput()
   {
        String file1 = "resources\\courseware\\program_images\\answer.png";
        String file2 = "resources\\courseware\\program_images\\TopSecret.png";
         
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