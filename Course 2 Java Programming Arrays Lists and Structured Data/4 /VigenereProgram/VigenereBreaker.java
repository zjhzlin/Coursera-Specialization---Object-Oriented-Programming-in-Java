import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {

    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        String sliced = "";
        for (int i = whichSlice; i < message.length(); i+=totalSlices) {
            sliced = sliced + message.charAt(i);
        }    
        return sliced;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        // for each slicedstring, get key and put in the key array. 
        for (int i = 0; i < klength; i++) {     // i -> which slice
            String sliced = sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            int k = cc.getKey(sliced);
            key[i] = k;
        }
        return key;
    }
    
    // task 2
    public HashSet<String> readDictionary(FileResource fr)  {
        HashSet<String> dict = new HashSet<String>();
        for (String line: fr.lines()) {
            line = line.toLowerCase();
            dict.add(line);
        }
        return dict;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        String[] splitM = message.split("\\W+");
        int count = 0;
        // make the word into lowercase
        for (int i = 0; i < splitM.length; i++) {
            String word = splitM[i].toLowerCase();
            if (dictionary.contains(word)) {
                count += 1;
            }
        }
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        // print out the key and return the decrypted message
        
        //key length to try;
        int start = 1;   
        int end = 100;
        char mostCommon = mostCommonCharIn(dictionary);
        // find the max length with the max count
        int max = 0;
        String decryption = "";
        int dklength = 0;
        for (int klength = start; klength <= end; klength++) {
            int[] key = tryKeyLength(encrypted, klength, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if (count > max) {
                max = count;
                decryption = decrypted;
                dklength = klength;
            }           
        }
        // print out what key is used
        int[] dkey = tryKeyLength(encrypted, dklength, mostCommon);
        System.out.println("keys with length" + dkey.length + ": ");
        for (int i = 0; i<dkey.length; i++) {
            System.out.print(dkey[i] + " ");
        }
        System.out.println("Total count: " + max);
        return decryption;
    }
    
         
    private char mostCharFromMap (HashMap<Character, Integer> map) {
        // return the char with max number in the HashMap
        int max = 0;
        char letterMax = 0;
        for(char letter: map.keySet()) {
            int count = map.get(letter);
            if (count > max) {
                max = count;
                letterMax = letter;
            }
        }
        return letterMax;
    }
     
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        // iterate all the words
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();        
        for (String word: dictionary) {
            // iterate all the letters of that word
            for (int i = 0; i < word.length(); i++){
                char letter = word.charAt(i);
                if (charMap.containsKey(letter)) {   //if letter is already in the map, count + 1
                    int count = charMap.get(letter);
                    charMap.put(letter, count+1);
                }
                else {  //else letter is not in, create a new one with count = 1
                    charMap.put(letter, 1);
                }
            }
        }        
        char letter = mostCharFromMap (charMap);
        return letter;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        // language: language -> HashSet <String, HashSet<String>>
        int max = 0;
        String bestLang = null;
        String decryption = null;
        for (String lang: languages.keySet()) {
            // break the encryption for each language;
            HashSet<String> dict = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, dict);
            int count = countWords(decrypted, dict);
            // who gives the best results
            if (count > max) {
                max = count;
                bestLang = lang;
                decryption = decrypted;
            }
        }
        // print out the decrypted message & the language you identified 
        System.out.println("decrypted message: " + decryption);
        System.out.println("Language used: " + bestLang);
    }
    
    public void tester(){    
        FileResource fr = new FileResource(); 
        HashSet<String> dict = readDictionary(fr);
        //String message = "what";
        //int num = countWords(message, dict);
        //System.out.println(num);
        
        char letter = mostCommonCharIn(dict);
        System.out.print("mostCommonCharIn: "+letter);
    }
    
    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("Original: " + input);
        
        // task 1: with known key length 
        //int klength = 4;              
        //int[] key = tryKeyLength(input, klength, 'e');        
        //VigenereCipher vc = new VigenereCipher(key);
        //String decrypted = vc.decrypt(input);
        //System.out.println("vigenere decrypted: " + decrypted.substring(0,189));
        
        // task 2: without known key length, but with known language
        //FileResource frDict = new FileResource(); 
        //HashSet<String> dict = readDictionary(frDict);
        //String decrypted = breakForLanguage(input, dict);
        //System.out.println("Vigenere decrypted: " + decrypted.substring(0,200));
    
        // for practice quiz:
        //int klength = 38;  
        //int[] key = tryKeyLength(input, klength, 'e');
        //VigenereCipher vc = new VigenereCipher(key);
        //String decrypted2 = vc.decrypt(input);
        //int count = countWords(decrypted2, dict);
        //System.out.println("With key length " + klength + "count is: " + count);
        
        // task 3: unknown language, unknown length
        // read many dictionaries
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        // read all dictionaries and save to hashmap
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            FileResource frDict = new FileResource(f); 
            HashSet<String> dict = readDictionary(frDict);
            String filename = f.getName();
            languages.put(filename, dict);           
            System.out.println("Language: " + filename + " done");
        }
        
        breakForAllLangs(input, languages);        
        //System.out.println("Vigenere decrypted: " + decrypted.substring(0,200));
    
    }
    
}
