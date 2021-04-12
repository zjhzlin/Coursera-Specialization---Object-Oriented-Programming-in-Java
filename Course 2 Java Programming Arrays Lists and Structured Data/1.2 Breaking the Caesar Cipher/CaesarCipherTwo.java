
/**
 * CaesarCipher Two
 * a proper CaesarCipher class with two keys in object oriented programming
 * - add a decrypt method to decrypt with the same key 
 * 
 * @Lynn Zhang
 * @version 2021-04-12 07:19 - 07:40
 */

public class CaesarCipherTwo {
    // private fields
    private String alphabet;
    private String shiftedA1;
    private String shiftedA2;
    private int mainKey1;
    private int mainKey2;
       
    // constructer 
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedA1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedA2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
      
    }
       
    public String encrypt(String input) {

        StringBuilder encrypted = new StringBuilder(input);
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (i%2 == 0) {     // use key 1
                char shiftedChar = encryptChar(currChar, shiftedA1);
                encrypted.setCharAt(i, shiftedChar);
            }
            else {              // use key 2
                char shiftedChar = encryptChar(currChar, shiftedA2);
                encrypted.setCharAt(i, shiftedChar);
            }
        }
        return encrypted.toString();
        
        
    }
    
    public String decrypt(String input) {
        //returns a string that is the encrypted string decrypted using the key 
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        return cc.encrypt(input);               
    }
    
    public char encryptChar(char ch, String shiftedA) {
        // encrypt the input using key according to Caesar Cipher rule             
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
    

}
