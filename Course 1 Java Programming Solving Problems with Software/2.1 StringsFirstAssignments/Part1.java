
/**
 * Write a description of Part1 here.
 * 
 * @author Lynn ZAHANG
 * @version 2021-04-08 06:34 - 06:50
 */
import edu.duke.*;
import java.io.*;

public class Part1 {
    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1){
            return "";
        }
        int length = stopIndex - startIndex;
        if (length % 3 == 0){
            return dna.substring(startIndex, stopIndex+3);
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
        String dna6 = "AAATGCCCTAACTAGATTAAGAAACC";
        String[] dnas = {dna1, dna2, dna3, dna4, dna5, dna6};
        for (String i: dnas){
            System.out.println("Originial string is "+i);
            String gene = findSimpleGene(i);
            System.out.println("Gene is " + gene);
        }
    }
}
