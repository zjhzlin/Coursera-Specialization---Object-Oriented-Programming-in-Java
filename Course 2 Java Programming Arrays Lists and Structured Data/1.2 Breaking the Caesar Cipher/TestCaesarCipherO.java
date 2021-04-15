
/**
 * Write a description of TestCaesarCipherO here.
 * test the CaesarCipherO class
 * 
 * @Lynn Zhang  
 * @2021-04-12
 */

import edu.duke.*;

public class TestCaesarCipherO {
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
    
    public void breakCaesarCipher(String input) {
        //returns a string that is the encrypted string decrypted using the key 
        int[] counts = countLetters(input);
        int indexOfMax = maxIndex(counts);    // this is e's encyrpted char position
        int dkey = 0;
        if (indexOfMax >= 4) {
            dkey = indexOfMax - 4;  // assuming this is e 
        }
        else {
            dkey = 26 - 4 + indexOfMax; 
        }
        CaesarCipherO cc = new CaesarCipherO(26-dkey);
        System.out.println("Decrypted: " + cc.encrypt(input) + "with key = " + dkey);   
    }
    
    public void SimpleTests() {
       FileResource fr = new FileResource();        
       String message = fr.asString();  
       //String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
       System.out.println("Message is: " + message);
       
       // encrypt
       int key = 2;
       CaesarCipherO cc = new CaesarCipherO(key);
       String encrypted = cc.encrypt(message);
       System.out.println("Encrypted message with key" + key + " is: " + encrypted);
       
       // decrypt
       String decrypted = cc.decrypt(encrypted);
       System.out.println("Decrypted message is: " + decrypted);
       
       //breakCaesarCipher(encrypted);
                   
    }
    
}
