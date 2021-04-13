
/**
 * Write a description of CharactersInPlay here.
 * 
 * to determine the characters in one of Shakespeare’s plays that have the most speaking parts
 * 
 * @Lynn Zhang
 * @2021-04-13 06:33 - 06:50 
 */
import java.util.ArrayList;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        int idx = names.indexOf(person);
        if (idx == -1) {    //not found. add new
            names.add(person);
            counts.add(1);
        }
        else {
            int count = counts.get(idx);
            counts.set(idx, count + 1);
        }        
    }
    
    public void findAllCharacters() {
        names.clear();
        counts.clear();
        
        FileResource fr = new FileResource();
        for (String line: fr.lines()) {
            int idx = line.indexOf(".");
            if (idx != -1) {            //find the period
                // get all before idx
                String name = line.substring(0, idx);
                update(name);
            }
        }
    }
    
    public int findIndexOfMax() {
        int max = counts.get(0);
        int maxIndex = 0;
        for (int idx = 0; idx < counts.size(); idx++ ) {
            if (counts.get(idx) > max) {
                max = counts.get(idx);
                maxIndex = idx;
            }
        }
        return maxIndex;
    }
        
    public void tester() {
        findAllCharacters();
        // print out the names with more than 10 counts
        int threshold = 10;
        System.out.println("Threshold is: " + threshold);
        for (int i = 0; i < names.size(); i++) {
            if (counts.get(i) > threshold) {
                System.out.println(names.get(i) + " " + counts.get(i));
            }
        }
        
        // print out the names with freq between num1 and num2
        int num1 = 10;
        int num2 = 15;       
        System.out.println("num1 is: " + num1 + " num2 is: " + num2);
        charactersWithNumParts(num1, num2);
        
        // print out the name with max count
        int maxIndex = findIndexOfMax();
        System.out.println("speaker speaks the most: " + names.get(maxIndex) + " with count " + counts.get(maxIndex));
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < names.size(); i++) {
            if (counts.get(i) >= num1 && counts.get(i) <= num2) {
                System.out.println(names.get(i) + " " + counts.get(i));
            }
        }
    }
    
    
}

