
/**
 * CaesarCipherO:
 * 
 * a proper CaesarCipher class in object oriented programming
 * - add a decrypt method to decrypt with the same key 
 *  
 * @Lynn Zhang
 * @version 2021-04-12 06:56 - 07:19
 */

import edu.duke.*;

public class CaesarCipherO {
    // private fields
    private String alphabet;
    private String shiftedA;
    private int mainKey;
    
    // constructer 
    public CaesarCipherO(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedA = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }
       
    public String encrypt(String input) {
        // encrypt the input using key according to Caesar Cipher rule
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean flag = Character.isUpperCase(currChar);  //flag = 0 lower, 1 upper
            char currCharUpper = Character.toUpperCase(currChar);            
            int idx = alphabet.indexOf(currCharUpper);
            if (idx != -1){
                if (flag) {   //uppercase
                    encrypted.setCharAt(i, shiftedA.charAt(idx));
                }
                else {
                    encrypted.setCharAt(i, Character.toLowerCase(shiftedA.charAt(idx)));
                }
            }
        }
        return encrypted.toString();
    }
    
    public int[] countLetters(String encrypted) {
        int[] counts = new int[26];
        String encryptedUpper = encrypted.toUpperCase();    // change to all upper cases 
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
    
    public String decrypt(String input) {
        //returns a string that is the encrypted string decrypted using the key 
        CaesarCipherO cc = new CaesarCipherO(26-mainKey);
        return cc.encrypt(input);               
    }
    
 

}
