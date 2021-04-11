
/**
 * CaesarBreaker
 * 1. decrypts a message that was encrypted with one key, using statistical letter frequencies of English text. 
 * 2. decrypt a message that was encrypted with two keys
 * 
 * @author Lynn Zhang
 * @version 2021-04-11 08:55 - 10:00
 */

import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String encrypted) {
        int[] counts = new int[26];
        String encryptedUpper = encrypted.toUpperCase();    // change to all upper cases 
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encryptedUpper.charAt(i);   // check each character
            int idx = alphabet.indexOf(currChar);   // check the index the character in alphabet
            if (idx != -1) {   // if in alphabet
                counts[idx] += 1;
            }
            // if not in, continue the loop. 
        }
        return counts;
    }
    
    public int maxIndex(int[] values) {
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
    
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] counts = countLetters(encrypted);
        int indexOfMax = maxIndex(counts);    // this is e's encyrpted char position
        int dkey = 0;
        if (indexOfMax >= 4) {
            dkey = indexOfMax - 4;  // assuming this is e 
        }
        else {
            dkey = 26 - 4 + indexOfMax; 
        }
        
        return cc.encrypt(encrypted, 26-dkey);
    }
    
    public String halfOfString(String message, int start) {
        String halfString = "";
        for (int i = start; i < message.length(); i += 2) {
            halfString += message.charAt(i);
        }
        return halfString;  
    }
    
    public int getKey(String s) {
        // return the key
        int[] counts = countLetters(s);
        int indexOfMax = maxIndex(counts);    // this is e's encyrpted char position
        int dkey = indexOfMax - 4; 
        if (indexOfMax < 4) {
            dkey = 26 - 4 + indexOfMax;  // assuming this is e 
        }
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String firstS = halfOfString(encrypted, 0);
        System.out.println("first half string is: " + firstS);
        String secondS = halfOfString(encrypted, 1);
        System.out.println("second half string is: " + secondS);
        int key1 = getKey(firstS);
        int key2 = getKey(secondS);
        System.out.println("First key is " + key1);
        System.out.println("Second key is " + key2);
        String decryptedTwoKeys = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
        return decryptedTwoKeys;
    }
    
    public void tester(){
       FileResource fr = new FileResource();        
       String encrypted = fr.asString();  
        
        // test 1 - countLetters and maxIndex
        //int[] counts = countLetters(encrypted);
        //for (int i = 0; i < counts.length; i++) {
        //    System.out.println("letter index: " + i + " with count: " + counts[i]);
        //}
        //System.out.println("Max index is: " + maxIndex(counts));
        
        
       // test 2 - decrypt 1 key
       //String decrypted = decrypt(encrypted);
       //System.out.println("Decrypted message is: " + decrypted);
       
       // test 3 - half of string
       //String string = "Qbkm Zgis";
       //int start = 1;
       //System.out.println("Half of string starting from index " + start + " : " + halfOfString(string, start));
    
       // test 4 - decrypt 2 keys
       //String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
       String decrypted = decryptTwoKeys(encrypted);
       System.out.println("Decrypted message is: " + decrypted);
    }
    

}
