
/**
 * Write a description of WordLengths here.
 * to figure out the most common word length of words from a file. 
 * 
 * Learning notes: array
 * 
 * @author Lynn Zhang 
 * @version 2021-04-11 08:08 - 08:45
 */

import edu.duke.*;

public class WordLengths {
    
    public void countWordLengths(FileResource resource, int[] counts) {
        // count the number of words of each length 
        for (String word: resource.words()){
        // read word from resource
            System.out.println("Word is " + word);
            // non-letter first or last -> not into account of the length
            // check the length of the word
            boolean flag1 = Character.isLetter(word.charAt(0));    // 1: the first is letter
            boolean flag2 = Character.isLetter(word.charAt(word.length()-1));    // 1: last is letter
            int lengthWord = word.length();
            if (flag1 && flag2) {    //both are letters - no change
                lengthWord = word.length();
            }
            else if (flag1 || flag2) {     // one of them is not letter
                lengthWord -= 1;               
            }
            else {  // neither of them are letters 
                lengthWord -= 2;
            }
            System.out.println("Length word: "+ lengthWord);
            if (lengthWord < 0) {
                lengthWord = 0;
            }
            counts[lengthWord] += 1;     
        } 
        //System.out.println(counts);
        for (int i = 0; i < counts.length; i++) {
            System.out.println("Word length of " + i + " is: " + counts[i]);        
        }
        
        System.out.println("max index is " + indexOfMax(counts));
    }
    
    public int indexOfMax(int[] values) {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    public void testCountWordLength(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);               
    }
    
    
}
