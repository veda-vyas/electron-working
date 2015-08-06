// BlueJ project: lesson9/scene1
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.util.Scanner;

public class Scene1
{
    public static void main(String[] args)
    {
        ArrayList<House> houses = new ArrayList<House>();
        houses.add(new House(100, 150, 100, 100));
        houses.add(new House(250, 100, 150, 150));

        ArrayList<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog(200, 100, "resources//courseware//programs//Scene1//Fido.jpg"));
        dogs.add(new Dog(100, 400, "resources//courseware//programs//Scene1//Rex.jpg"));
        dogs.add(new Dog(200, 400, "resources//courseware//programs//Scene1//Lucky.jpg"));

        // Add cars at (100, 300), (250, 300), (250, 350)

        for (House h : houses)
        {
            h.draw();
        }
        for (Dog d : dogs)
        {
            d.draw();
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
        String file2 = "resources\\courseware\\program_images\\scene1.png";
         
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