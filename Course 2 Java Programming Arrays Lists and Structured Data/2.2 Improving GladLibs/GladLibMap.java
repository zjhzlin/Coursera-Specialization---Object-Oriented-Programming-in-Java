
/**
 * Write a description of GladLibMap here.
 * 
 * @author Lynn Zhang
 * @version 2021-04-14 05:47 - 06:30
 */

import edu.duke.*;
import java.util.*;

public class GladLibMap {	
    private HashMap<String, ArrayList<String>> myMap;
	
    private String[] category = {"adjective", "noun", "color", "country", "name", "animal", "timeframe"};
    private int[] idxUsed = new int[7];
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);		
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
    	initializeFromSource(source);
    	myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        //String[] category = {"adjective", "noun", "color", "country", "name", "animal", "timeframe"};
        for (int i = 0; i < category.length; i++) {
            ArrayList aList = readIt(source + "/" + category[i] + ".txt");
            myMap.put(category[i], aList);
            System.out.println("Map is " + category[i]);
        }	    				
    }
    
    private String randomFrom(ArrayList<String> source){
    	int index = myRandom.nextInt(source.size());
    	return source.get(index);
    }
    
    private String getSubstitute(String label) {
        for (int i = 0; i < category.length; i++) {
            if (label.equals(category[i])) {
                idxUsed[i] = 1;
                return randomFrom(myMap.get(category[i]));	            
            }
            if (label.equals("number")){
                return ""+myRandom.nextInt(50)+5;
    	}
            
        }		
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
    	int first = w.indexOf("<");
    	int last = w.indexOf(">",first);
    	if (first == -1 || last == -1){
    		return w;
    	}
    	String prefix = w.substring(0,first);
    	String suffix = w.substring(last+1);
    	String sub = getSubstitute(w.substring(first+1,last));
    	return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
    	int charsWritten = 0;
    	for(String w : s.split("\\s+")){
    		if (charsWritten + w.length() > lineWidth){
    			System.out.println();
    			charsWritten = 0;
    		}
    		System.out.print(w+" ");
    		charsWritten += w.length() + 1;
    	}
    }
    
    private String fromTemplate(String source){
    	String story = "";
    	if (source.startsWith("http")) {
    		URLResource resource = new URLResource(source);
    		for(String word : resource.words()){
    			story = story + processWord(word) + " ";
    		}
    	}
    	else {
    		FileResource resource = new FileResource(source);
    		for(String word : resource.words()){
    			story = story + processWord(word) + " ";
    		}
    	}
    	return story;
    }
    
    private ArrayList<String> readIt(String source){
    	ArrayList<String> list = new ArrayList<String>();
    	if (source.startsWith("http")) {
    		URLResource resource = new URLResource(source);
    		for(String line : resource.lines()){
    			list.add(line);
    		}
    	}
    	else {
    		FileResource resource = new FileResource(source);
    		for(String line : resource.lines()){
    			list.add(line);
    		}
    	}
    	return list;
    }
    
    public int totalWordsInMap() {
        // return the total number of words in all the arraylists in the hashmap
        int num = 0;
        for (String word: myMap.keySet()) {
           ArrayList aList = myMap.get(word);
           num = num + aList.size();
        }
        return num;
    }
    
    public int totalWordsConsidered() {
       // return the total num of words in the category ArraylISTS THAT WERE USED FOR A PARTICULAR gLADlIB
       int num = 0;
       for (int i = 0; i < idxUsed.length; i++) {
           if(idxUsed[i] != 0) {
                System.out.println(i);
                ArrayList aList = myMap.get(category[i]);
                num = num + aList.size();
           }
         
       }
       return num;
       }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        
        int num = totalWordsInMap();
        System.out.println();
        System.out.println("Total number to choose from: " + num);
        
        int numCon= totalWordsConsidered();
        System.out.println("Total number considered: " + numCon);
        
        
    }



}
