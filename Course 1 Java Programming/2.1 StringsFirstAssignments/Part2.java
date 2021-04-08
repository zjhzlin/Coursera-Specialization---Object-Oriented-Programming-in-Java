
/**
 * Write a description of Part2 here.
 * 
 * @author Lynn Zhang 
 * @version 2021-04-08 06:54 - 07:05
 */
import edu.duke.*;
import java.io.*;

public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String endCodon){
        //check dna uppercase or lowercase
        boolean b = Character.isLowerCase(dna.charAt(0));
        if(b){
            dna = dna.toUpperCase();
        }
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf(endCodon, startIndex+3);
        if (stopIndex == -1){
            return "";
        }
        int length = stopIndex - startIndex;
        if (length % 3 == 0){
            String gene = dna.substring(startIndex, stopIndex+3);            
            if(b){
                return gene.toLowerCase();
            }
            else{
                return gene;
            }
        }
        else {
            return "";
        }
    }
    
    public void testSimpleGene() {
        String dna1 = "ATGAAAGGGTTTTAAGGG";
        String dna2 = "AAAGGGTTTAAAGGG";
        String dna3 = "ATGAAAGGGTTTTTT";
        String dna4 = "aaagggttt";
        String dna5 = "ATGAAAGGTAAGGG";
        String dna6 = "atgaaagggttttaattt";
        String[] dnas = {dna1, dna2, dna3, dna4, dna5, dna6};
        String startCodon = "ATG";
        String endCodon = "TAA";
        for (String i: dnas){
            System.out.println("Originial string is "+i);
            String gene = findSimpleGene(i, startCodon, endCodon);
            System.out.println("Gene is " + gene);
        }
    }
}
