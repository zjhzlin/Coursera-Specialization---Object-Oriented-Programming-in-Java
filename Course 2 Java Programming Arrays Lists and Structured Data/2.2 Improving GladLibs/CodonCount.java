
/**
 * CodonCount 
 * to find out how many times each codon occurs in a strand of DNA based on reading frames.
 * 
 * use of HashMap
 * 
 * @author Lynn Zhang
 * @version 2021-04-13 07:43 - 08:43
 */

import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String,Integer> map;
    
    public CodonCount() {
        map = new HashMap<String, Integer>();       
    }
    
    public void buildCodonMap(int start, String dna) {
        map.clear();            // call this method several times, need to be empty before building it
        for (int idx = start; idx < dna.length()-3; idx+=3) {        
            String codon = dna.substring(idx, idx+3);
            codon = codon.toUpperCase();
            if (!map.containsKey(codon)) {   //no such codon, add
                map.put(codon,1);
            }
            else{
                map.put(codon, map.get(codon)+1);
            }           
        }
        
    }
    
    
    public String getMostCommonCodon() {
        // loop in map - cannot use index
        int max = 0;
        String mostCodon = "";
        for (String c: map.keySet()) {
            if (max < map.get(c)) {
                max = map.get(c);
                mostCodon = c;
            }           
        }
        return mostCodon;       
    }
    
    public void printCodonCounts(int start, int end) {
        // prints all the codons with counts between start and end
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are: ");
        for (String c: map.keySet()) {
            if ( map.get(c) >= start && map.get(c) <= end ) {
                int count = map.get(c);
                System.out.println(c + " " + count);
            }           
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        //String dna = "CGTTCAAGTTCAA";
        dna = dna.toUpperCase().trim();
        int pIdxStart = 7;
        int pIdxEnd = 7;
        System.out.println("DNA is: " + dna);
        System.out.println("Length is: " + dna.length());
        for (int start = 0; start < 3; start++) {
            buildCodonMap(start, dna);
            String mostCodon = getMostCommonCodon(); 
            System.out.println("Reading frame starting with " + start + " result in " + map.keySet().size() + " unique codons");
            System.out.println("and most common codon is " + mostCodon + " with count " + map.get(mostCodon));
            printCodonCounts(pIdxStart, pIdxEnd);
        }
        
    }
    
}
