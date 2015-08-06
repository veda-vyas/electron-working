// BlueJ project: lesson9/scene4
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.util.Scanner;

public class Scene4
{
    public static void main(String[] args)
    {
        ArrayList<Drawable> elements = new ArrayList<Drawable>();
        Car car1 = new Car(100, 300);
        elements.add(car1);
        elements.add(new Car(250, 300));
        elements.add(new Car(250, 350));

        elements.add(new House(100, 150, 100, 100));
        elements.add(new House(250, 100, 150, 150));

        Dog dog1 = new Dog(200, 100, "resources//courseware//programs//scene4//Fido.jpg");
        elements.add(dog1);
        elements.add(new Dog(100, 400, "resources//courseware//programs//scene4//Rex.jpg"));
        elements.add(new Dog(200, 400, "resources//courseware//programs//scene4//Lucky.jpg"));

        for (Drawable d : elements)
        {
            d.draw();
        }

        // TODO: Also move Fido

        for (int i = 1; i <= 10; i++)
        {
            car1.move(1);
            Canvas.snapshot();
        }
        Canvas.getInstance().snapshot();
        CheckOutput out = new CheckOutput();
        out.compareOutput();
    }
}

// BlueJ project: lesson9/scene4
/*
   TODO: Dogs should be moveable.
   They move one pixel downwards every second.
*/

class Dog implements Drawable
{
    private Picture pic;
    private Text name;

    public Dog(int x, int y, String file)
    {
        pic = new Picture(file);
        pic.translate(x, y);
        name = new Text(x, y + pic.getHeight(),
                        file.substring(file.indexOf("4//")+3, file.indexOf(".")));
    }

    public void draw()
    {
        pic.draw();
        name.draw();
    }
}

class CheckOutput{
    public void compareOutput()
   {
        String file1 = "resources\\courseware\\program_images\\answer.png";
        String file2 = "resources\\courseware\\program_images\\scene4.png";
         
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