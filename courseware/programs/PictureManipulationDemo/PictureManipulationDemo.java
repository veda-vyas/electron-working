//In this exercise we are going to manipulate images with the Picture class. We are
//studying 2D arrays so we will work with the 2D int array that is returned by
//Picture's getGraylevels method. This method is dicussed at the end of lesson 7.3.
//Each element of the array coresponds to a pixel in the image. The color has been
//transformed from an RGB color to a gray scale value.

//The Picture class also has a constructor that takes a 2D int array as a parameter.
//You will use that, too.

//Your task is to complete the two methods in the PictureUtil. Notice these are static methods.
//They do not act on instance variables of the PictureUtil class. In fact, the class has no
//instance data.

//Here is a description of the methods

//private static Picture grayAndFlipLeftToRight( Picture pic) :
//- This method converts the image into a 2D array of gray scale values and then flips it
//left to right. That is, an object that was facing left will now be facing right.
//The elements on the left most column will be swapped with those in the right-most column
//and so on. Manipulate the elements in the array so that the first column is swapped with
//the last column and the second column is swapped with the next to the last and so on.
//Do not make a second array.
//Then call the Picture constructor that takes a 2D int array and return the picture.

//private static Picture grayAndRotate90( Picture pic):
//- This method converts the image into a 2D array of gray scale values and then rotates it
//90 degrees clockwise (lays it on its right side). To do this, create a new array where
//the number of columns in the original array becomes the number of rows in the new array
//and the number of rows in the original becomes the number of colums. Then copy the elements
//from the first array into the proper place in the second array so that the image is
//rotated 90 degrees clockwise. The first row will become the last column
//[0][0] -> [0][numberOfColums - 1]

//There is a starter project for this problem that contains the graphics classes you will
//need, the tester, and the image used by the tester

//For the draft: In PictureUtil, implement GrayAndFlipLeftToRight to return a version of the
//original picture in gray scale

/**
 * Methods to manipulate the Picture class
 */
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.util.Scanner;

class PictureUtil
{
    /**
     * Gets a version of the given Picture in gray scale and flipped left to right
     * @param pic the Picture to convert to gray scale and flip
     * @return a version of the original Picture in gray scale and flipped
     * left to right.
     */
    public static Picture grayAndFlipLeftToRight( Picture pic)
    {
        // TODO get a gray scale version
        // TODO flip it left to right
        // TODO create and return the new Picture
        int [][] grayLevels = pic.getGrayLevels();
        for(int y = 0; y < pic.getHeight(); y++)
        {
            for(int x = 0; x < pic.getWidth() / 2; x++)
            {
                int xPix = pic.getWidth() - 1 - x;
                int temp = grayLevels[y][x];
                grayLevels[y][x] = grayLevels[y][xPix];
                grayLevels[y][xPix] = temp;
            }
        }
        Picture newPic = new Picture(grayLevels);
        return newPic;
    }

    /**
     * Gets a version of the given Picture in gray scale and rotated 90 degrees clockwise
     * @param pic the Picture to convert to gray scale and rotate 90 degrees clockwise
     * @return a version of the original Picture in gray scale and rotated 90 degrees clockwise
     */
    public static Picture grayAndRotate90( Picture pic)
    {
        // TODO get a gray scale version
        // TODO make a new array where the first row of the original becomes the last
        // column of the new array
        int [][] grayLevels = pic.getGrayLevels();
        int [][] temp = new int[grayLevels[0].length][grayLevels.length];
        for(int y = 0; y < pic.getWidth(); y++)
        {
            for(int x = 0; x < pic.getHeight(); x++)
            {
                temp[y][pic.getHeight()-1-x] = grayLevels[x][y];
            }
        }
        Picture newPic = new Picture(temp);
        return newPic; //just so draft will compile. You will change it in the final
    }
}
public class PictureManipulationDemo
{
    public static void main(String[] args)
    {
        final int SPACER = 10;
        Picture pic = new Picture("resources//courseware//programs//PictureManipulationDemo//renoir1.jpg");
        pic.draw();
        Picture flipped = PictureUtil.grayAndFlipLeftToRight(pic);
        flipped.translate(pic.getWidth() + SPACER, 0);
        flipped.draw();
        Picture rotated =  PictureUtil.grayAndRotate90(pic);
        rotated.translate(2 * pic.getWidth() + 2 * SPACER, 0);
        rotated.draw();
        Canvas.getInstance().snapshot();
        CheckOutput out = new CheckOutput();
        out.compareOutput();
    }
}
class CheckOutput{
    public void compareOutput()
   {
        String file1 = "resources\\courseware\\program_images\\answer.png";
        String file2 = "resources\\courseware\\program_images\\PictureManipulationDemo.png";
         
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