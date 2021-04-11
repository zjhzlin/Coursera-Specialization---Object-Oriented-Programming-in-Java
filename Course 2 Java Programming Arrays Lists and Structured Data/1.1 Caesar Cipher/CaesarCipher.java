
/**
 * Caesar Cipher that works with all letters (both upper- and lower-case)
 * with 1 key and with 2 keys options
 * 
 * @author Lynn Zhang
 * @version 2021-04-11 06:52 - 07:23
 */
import edu.duke.*;

public class CaesarCipher {
    
    public String encrypt(String input, int key) {
        // encrypt the input using key according to Caesar Cipher rule
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedA = alphabet.substring(key) + alphabet.substring(0,key);
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
    
    public char encryptChar(char ch, int key) {
        // encrypt the input using key according to Caesar Cipher rule
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedA = alphabet.substring(key) + alphabet.substring(0,key);
                
        boolean flag = Character.isUpperCase(ch);  //flag = 0 lower, 1 upper
        char currCharUpper = Character.toUpperCase(ch);            
        int idx = alphabet.indexOf(currCharUpper);
        if (idx != -1){
            if (flag) {   //uppercase
                return shiftedA.charAt(idx);
            }
            else {
                return Character.toLowerCase(shiftedA.charAt(idx));
            }
        }
        
        return ch;
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (i%2 == 0) {     // use key 1
                char shiftedChar = encryptChar(currChar, key1);
                encrypted.setCharAt(i, shiftedChar);
            }
            else {              // use key 2
                char shiftedChar = encryptChar(currChar, key2);
                encrypted.setCharAt(i, shiftedChar);
            }
        }
        return encrypted.toString();
    }
    
    public void testCaesar() {
        // test 1
        String input = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        int key = 15;
        System.out.println(encrypt(input, key));
        
        // test 2 
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        //System.out.println("key is " + key + "\n" + encrypted);
        
        // test 3
        int key1 = 8;
        int key2 = 21;
        System.out.println(encryptTwoKeys(input, key1, key2));
        
    }

}
