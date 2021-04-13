
/**
 * WordFrequencies
 * to determine the word that occurs the most often in a file
 * use of ArrayList
 * 
 * @author Lynn Zhang   
 * @version 2021-04-13 06:10 - 06:27
 */

import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;      // arraylist of type string to store unique words from a file
    private ArrayList<Integer> myFreqs;      // arraylist of type int, kth position = kth word's frequency
    
    //constructer 
    public WordFrequencies() {
        // initialize
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique() {
        // clear the two private fields
        myWords.clear();
        myFreqs.clear();
        // select a file 
        FileResource fr = new FileResource();
        //iterate over every word in a file
        for (String word: fr.words()) {
            // put unique word in the file
            // make it to lowercase
            word = word.toLowerCase();
            // use index to find 
            int idx = myWords.indexOf(word);
            // if it is a new word, add and count + 1
            if (idx == -1) {
                myWords.add(word);
                myFreqs.add(1);
            }        
            // if it is in the list, count + 1
            else {
                int freq = myFreqs.get(idx);
                myFreqs.set(idx, freq+1);
            }
        }

    }
      
    public int findIndexOfMax() {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for (int idx = 0; idx < myFreqs.size(); idx++ ) {
            if (myFreqs.get(idx) > max) {
                max = myFreqs.get(idx);
                maxIndex = idx;
            }
        }
        return maxIndex;
    }
    
    
    
    public void tester() {
        findUnique();
        // print the number of unique words(how many) and those words with frequency
        System.out.println("Number of unique words: " + myWords.size());
        //for (int idx = 0; idx < myWords.size(); idx++ ) {
        //    System.out.println(myFreqs.get(idx) + " " + myWords.get(idx));
        //}
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " 
                            + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
}
