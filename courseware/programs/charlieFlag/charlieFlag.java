import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

class Flag
{
    private Picture pic;
    private int width;
    private int height;

    public Color getColorAt(int x, int y)
    {
        // TODO: write code to determin if the pixel at x, y should be blue, red or white
    }

    public Flag(int width, int height)
    {
        this.width = width;
        this.height = height;
        pic = new Picture(width, height);
        pic.draw();
        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                Color c = getColorAt(x, y);
                pic.setColorAt(x, y, c);
            }
        }
    }
}
public class charlieFlag{
    public static void main(String[] args) {
        Flag chF = new Flag(200,100);
        Picture pict = new Picture();
        pict.snapshot();
        CheckOutput out = new CheckOutput();
        out.compareOutput();   
    }
}
class CheckOutput{
    public void compareOutput()
   {
        String file1 = "resources\\courseware\\program_images\\answer.png";
        String file2 = "resources\\courseware\\program_images\\charlieFlag.png";
         
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