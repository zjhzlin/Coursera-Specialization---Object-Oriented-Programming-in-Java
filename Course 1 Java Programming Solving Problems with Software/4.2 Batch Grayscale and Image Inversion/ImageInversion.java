
/**
 * Write a description of ImageInversion here.
 * 1. create new images that are photographic negatives (or inverted images) of selected images 
 * 2. save these new images with filenames that are related to the original images, 
 *    such as adding “inverted-” in front of the old filename. 
 * 
 * @author Lynn Zhang
 * @version 2021-04-10 16:25 - 16:40
 */

import edu.duke.*;
import java.io.*;

public class ImageInversion {
    public ImageResource makeInversion(ImageResource image){
        //invert the image
        //create a blank image
        ImageResource imageInverted = new ImageResource(image.getWidth(), image.getHeight());
        //for each pixel, invert
        for (Pixel pixel: imageInverted.pixels()){
            Pixel inPixel = image.getPixel(pixel.getX(), pixel.getY());
            int invertedRed = 255 - inPixel.getRed();
            int invertedBlue = 255 - inPixel.getBlue();
            int invertedGreen = 255 - inPixel.getGreen();
            pixel.setRed(invertedRed);
            pixel.setBlue(invertedBlue);
            pixel.setGreen(invertedGreen);
        }
        return imageInverted;        
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            String name = image.getFileName();
            String newName = "inverted-" + name;
            ImageResource imageInverted = makeInversion(image);
            imageInverted.draw();
            imageInverted.setFileName(newName);
            imageInverted.save();           
        }
    
    }
    
}
