
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class tester {
    
    public void testerCaesarCipher(){
        // testing CaesarCipher
        //String filename = "/VigenereTestData/titus-small.txt";
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("Original: " + input);
        int key = 5;
        CaesarCipher cc = new CaesarCipher(key);
        String encrypted = cc.encrypt(input);
        System.out.println("Encrypt: "+ encrypted);
        System.out.println("Decrypt: " + cc.decrypt(encrypted));                
    }     
    
    public void testerCaesarCracker(){
        // testing CaesarCipher
        //String filename = "/VigenereTestData/titus-small.txt";
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("Original: " + input);
        
        // testing CaesarCracker - English      
        //CaesarCracker ccC = new CaesarCracker();
        //System.out.println("Cracker: " + ccC.decrypt(input));
        
        // Portuguese
        CaesarCracker ccPor = new CaesarCracker('a');
        System.out.println("Cracker: " + ccPor.decrypt(input));
    }    
    
    public void testerVigenereCipher(){        
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("Original: " + input);
        
        int[] key = {17, 14, 12, 4};  //using "rome"
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println("Encrypted: " + vc.encrypt(input));
        System.out.println("Decrypted: " + vc.decrypt(vc.encrypt(input)));
    }
    
    public void testerVigenereBreaker(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        System.out.println("Original: " + input);
        
        VigenereBreaker vb = new VigenereBreaker();
        //System.out.println(vb.sliceString("abcdefghijklm",4,5));
        
        int[] key = vb.tryKeyLength(input, 4, 'e');
        System.out.print("keys: ");
        for (int i = 0; i<key.length; i++) {
            System.out.print(key[i] + " ");
        }
    }
    
}
