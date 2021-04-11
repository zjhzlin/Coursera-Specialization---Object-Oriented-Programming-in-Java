package perimeter_quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        // get points from shape
        // iterate over all points and update the result in count
        for (Point currPt: s.getPoints()){
            count = count + 1;
        }
        
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double length = getPerimeter(s);
        int count = getNumPoints(s);
        double avgLength = length/count;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        // start with largest side initialized as 0
        double largest = 0.0;
        Point prePt = s.getLastPoint();
        
        for (Point currPt: s.getPoints()){
            //find the distance from pre to cur
            double currDist = prePt.distance(currPt);
            if (currDist > largest){
                largest = currDist;
            }
            prePt = currPt;
        }
        
        return largest;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0.0;
        Point prePt = s.getLastPoint();
        
        for (Point currPt: s.getPoints()){
            //find the distance from pre to cur
            double currX = currPt.getX();
            if (currX > largestX){
                largestX = currX;
            }
            prePt = currPt;
        }
        
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double maxPerimeter = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (currPerimeter > maxPerimeter){
                maxPerimeter = currPerimeter;
            }
        }
        return maxPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here                   
        double maxPerimeter = 0.0;
        File maxFile = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (currPerimeter > maxPerimeter){
                maxPerimeter = currPerimeter;
                maxFile = f;
            }
        }
        return maxFile.getName();
        
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int count = getNumPoints(s);
        double avgLen = getAverageLength(s);
        double largest = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("point count =" + count);
        System.out.println("average length = "+ avgLen);
        System.out.println("Largest side = "+ largest);
        System.out.println("Largest X = "+ largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double maxPeri = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is " + maxPeri);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String maxFileName = getFileWithLargestPerimeter();
        System.out.println("Max File is " + maxFileName);
        
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
