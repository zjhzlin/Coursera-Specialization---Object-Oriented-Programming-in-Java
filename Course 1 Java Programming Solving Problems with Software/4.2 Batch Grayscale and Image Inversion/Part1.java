
/**
 * Batch Grayscale Project
 * 1. batch processes several images - gray scale 
 * 2. creates and saves new images (with new filenames) that are grayscale versions of each image.
 * 
 * @author Lynn Zhang
 * @version 2021-04-10 16:09 - 16:20
 */

import edu.duke.*;
import java.io.*;

public class Part1 {
    
    public ImageResource makeGray(ImageResource image){
        // make a blank one
        ImageResource grayImage = new ImageResource(image.getWidth(), image.getHeight());
        // for each pixel, calculate the gray scale and put in the grayImage
        for (Pixel pixel: grayImage.pixels()){
            Pixel inPixel = image.getPixel(pixel.getX(), pixel.getY());
            int avg = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return grayImage;
    }
    
    public void batchGrayscaleAndSave(){
        DirectoryResource dr = new DirectoryResource();
        // let user select multiple image files
        for(File f: dr.selectedFiles()){
            // for each one, create a grayscale version
            ImageResource image = new ImageResource(f);
            String fname = image.getFileName();            
            System.out.println(fname);
            // convert to grayscale
            ImageResource grayImage = makeGray(image);
        
            // save that image 
            String newName = "copy-" + fname;
            System.out.println(newName);
            grayImage.setFileName(newName);
            grayImage.draw();
            grayImage.save();
        }               
    }
    
}
