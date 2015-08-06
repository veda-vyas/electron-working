// BlueJ project: lesson7/gallery9

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.util.Scanner;

public class ListOfPictures9
{
    public static void main(String[] args)
    {
        ArrayList<Picture> gallery = new ArrayList<Picture>();
        gallery.add(new Picture("resources//courseware//programs//ListOfPictures9//degas1.jpg"));
        gallery.add(new Picture("resources//courseware//programs//ListOfPictures9//monet1.jpg"));
        gallery.add(new Picture("resources//courseware//programs//ListOfPictures9//monet2.jpg"));
        gallery.add(new Picture("resources//courseware//programs//ListOfPictures9//renoir1.jpg"));
        gallery.add(new Picture("resources//courseware//programs//ListOfPictures9//seurat1.jpg"));

        ArrayList<Picture> matches = new ArrayList<Picture>();
        for (Picture pic : gallery)
        {
            if (pic.getHeight() > pic.getWidth())
            {
                matches.add(pic);
            }
        }

        int rightmostX = 0;
        for (Picture pic : matches)
        {
            pic.translate(rightmostX + 10, 0);
            rightmostX = pic.getMaxX();
            pic.draw();
        }
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
        String file2 = "resources\\courseware\\program_images\\ListOfPictures9.png";
         
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