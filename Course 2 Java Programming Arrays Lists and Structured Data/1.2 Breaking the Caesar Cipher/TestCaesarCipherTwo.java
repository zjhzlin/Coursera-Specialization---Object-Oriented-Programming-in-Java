
/**
 * TestCaesarCipherTwo.
 * 
 * @Lynn Zhang
 * @2021-04-12
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    
    
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
    
    
    public String halfOfString(String message, int start) {
        String halfString = "";
        for (int i = start; i < message.length(); i += 2) {
            halfString += message.charAt(i);
        }
        return halfString;  
    }
    
    public void breakCaesarCipher (String input) {
        
        String firstS = halfOfString(input, 0);
        System.out.println("first half string is: " + firstS);
        String secondS = halfOfString(input, 1);
        System.out.println("second half string is: " + secondS);
        int key1 = getKey(firstS);
        int key2 = getKey(secondS);
        System.out.println("First key is " + key1);
        System.out.println("Second key is " + key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(26-key1, 26-key2);        
        String decryptedTwoKeys = cc.encrypt(input);
        System.out.println("Decrypted: " + decryptedTwoKeys);
      
    }
    
    public void SimpleTests() {
       FileResource fr = new FileResource();        
       //String message = fr.asString();  
       //String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
       //System.out.println("Message is: " + message);
       
       // encrypt
       //int key1 = 14;
       //int key2 = 24;
       //CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
       //String encrypted = cc.encrypt(message);
       //String encrypted = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
       //System.out.println("Encrypted message is with keys" + key1 + ", " + key2 + " : " + encrypted);
              
       // decrypt
       //String decrypted = cc.decrypt(encrypted);
       //System.out.println("Decrypted message is: " + decrypted);
       
       //String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
       String encrypted = fr.asString();
       System.out.println("Encrypted message is: " + encrypted);
              
       breakCaesarCipher(encrypted);
                   
    }
    
}
