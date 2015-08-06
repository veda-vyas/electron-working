// BlueJ project: lesson9/scene2

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.util.Scanner;

public class Scene2
{
    public static void main(String[] args)
    {
        // TODO: Change to an ArrayList<Drawable>

        ArrayList<Car> Cars = new ArrayList<Car>();
        Cars.add(new Car(100, 300));
        Cars.add(new Car(250, 300));
        Cars.add(new Car(250, 350));

        ArrayList<House> houses = new ArrayList<House>();
        Houses.add(new House(100, 150, 100, 100));
        Houses.add(new House(250, 100, 150, 150));

        ArrayList<Dog> dogs = new ArrayList<Dog>();
        Dogs.add(new Dog(200, 100, "resources//courseware//programs//scene2//Fido.jpg"));
        Dogs.add(new Dog(100, 400, "resources//courseware//programs//scene2//Rex.jpg"));
        Dogs.add(new Dog(200, 400, "resources//courseware//programs//scene2//Lucky.jpg"));

        TODO: Change to a single loop
        for (Car c : cars)
        {
            c.draw();
        }
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
        String file2 = "resources\\courseware\\program_images\\scene2.png";
         
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