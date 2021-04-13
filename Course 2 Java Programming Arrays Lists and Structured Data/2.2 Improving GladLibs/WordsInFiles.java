
/**
 * WWordsInFiles
 * to determine which words occur in the greatest number of files, 
 * and for each word, which files they occur in. 
 * 
 * use of HashMap and ArrayList
 * 
 * @author Lynn Zhang
 * @version 2021-04-13 09:02 - 09:50 
 */

import edu.duke.*;
import java.util.*;
import java.io.*;  //for File type

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map;
    
    public WordsInFiles() {
        map = new HashMap<String, ArrayList<String>>();
        
    }
    
    public void addWordsFromFile(File f) {
        String filename = f.getName();
        FileResource fr = new FileResource (f);
        // check if word is in the map
        for (String word: fr.words()) {
            if(!map.containsKey(word)) { //new word
                ArrayList<String> filelist = new ArrayList<String>();  
                filelist.add(filename);
                map.put(word, filelist);
            }
            else {
                ArrayList<String> filelist = map.get(word);
                filelist.add(filename);
                map.put(word, filelist);
            }
        }
    }
    
    public void buildWordFileMap() {
        // clear the map
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber() {
        // return the max num of files any word appears in
        return 0;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        // return an ArrayList of words that appear in exactly number files
        ArrayList<String> words = new ArrayList<String>();
        return words;
    }
    
    public void printFilesIn(String word) {
        // print the names of the files this word appears in
        
    }
    
    public void tester() {
        buildWordFileMap();
        // test buildWrodFileMap ok
        for (String word: map.keySet()) {
            System.out.println("word is " + word + ", appear in files: ");
            ArrayList<String> filelist = map.get(word);
            for (int i = 0; i < filelist.size(); i++) {
                System.out.println("file: " + filelist.get(i));
            }
        }
        
        int maxNumFile = maxNumber();
        System.out.println("Max number file is: " + maxNumFile);
    }
    
}
