
/**
 * Part3
 * Write the void method processGenes that has one parameter sr, 
 * which is a StorageResource of strings. 
 * This method processes all the strings in sr to find out information 
 * about them. 
 * 
 * @author Lynn Zhang   
 * @version 2021-04-09 07:07 - 08:31
 * findStopCodon - logic wrong - no consideration of not finding the stopcodon - need to use while 
 * see part3B for correct answer
 */

import edu.duke.*;

public class Part3 {
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        //System.out.println(stopIndex);
        if(startIndex == -1 || stopIndex == -1){
            return dna.length();
        }
        int diff = stopIndex - startIndex;
        if (diff%3 == 0){
            return stopIndex;
        }
        else{
            return dna.length();
        }
    }
    
    public String findGene(String dna){
        String startCodon = "ATG";
        int startIndex = dna.indexOf(startCodon);
        // no start codon found
        if (startIndex == -1){
            return "";
        }
        //
        int stopIndexTAA = findStopCodon(dna, startIndex, "TAA");
        int stopIndexTAG = findStopCodon(dna, startIndex, "TAG");
        int stopIndexTGA = findStopCodon(dna, startIndex, "TGA");
        // find the minimum of the three
        int minStopIndex = Math.min(stopIndexTAA, Math.min(stopIndexTAG, stopIndexTGA));
        // if not found, return empty string
        if(minStopIndex == dna.length()){
            return "";
        }
        else{
            return dna.substring(startIndex, minStopIndex+3);
        }
    }
    
    public StorageResource getAllGenes(String dna){
        //create new storageresource
        StorageResource geneList = new StorageResource();
        String currDna = dna;
        int count = 0;
        System.out.println("Current dna: " + currDna);
        while(true){
            String gene = findGene(currDna);
            //System.out.println("Current gene: " + gene);
            if (currDna == ""){
                System.out.println("No more gene found");
                break;
            }
            if (gene != ""){
                System.out.println("Gene is: " + gene);
                int nextIndex = currDna.indexOf(gene) + gene.length();
                geneList.add(gene);
                currDna = currDna.substring(nextIndex);  
                count = count + 1;
            }
            else{
            // find next start codon
                System.out.println("no Gene found");
                if(currDna.indexOf("ATG") == -1){
                    break;
                }
                int nextIndex = currDna.indexOf("ATG") + 3;
                currDna = currDna.substring(nextIndex);
            }            
        }
        System.out.println("In total, there are " + count + " genes");
        return geneList;
    }
    
    
    public int howMany(String stringa, String stringb){
        //String currString = stringb;
        int startIndex = 0;   //start position in string b
        int count = 0;
        while(true){
            int indexA = stringb.indexOf(stringa, startIndex);
            if (indexA != -1){
                count = count + 1;
                startIndex = indexA + stringa.length();
            }
            else{
                break;
            }
        }
        return count;                
    }
    
    public double cgRatio(String dna){
        int howManyC = howMany("C",dna);
        int howManyG = howMany("G",dna);
        double howManyCG = howManyC + howManyG;
        double ratio = howManyCG/dna.length();
        return ratio;         
    }
    
    
    public void processGenes(StorageResource sr){
        int lenLongest = 0;
        int countLonger = 0;
        int countCG = 0;
        for(String gene: sr.data()){
            if (gene.length() > 60){               
                System.out.println("Longer than 60: " + gene);
                System.out.println("Number of strings: " + gene.length()); 
                countLonger = countLonger + 1;
            }
            double geneCG = cgRatio(gene);
            if (geneCG > 0.35){
                System.out.println("CG Ratio higher than 0.35: " + gene);
                System.out.println("No. of strings: " + gene.length());
                countCG = countCG + 1;
            }
            // find the string with longest length
            if (gene.length()>lenLongest){
                lenLongest = gene.length();
            }
        }
        System.out.println("Longest length is: " + lenLongest);
        System.out.println(countLonger + " genes longer than 90");
        System.out.println(countCG + " genes with more than 0.35 ratio");
    }
    
    public void testProcessGenes(){
        //StorageResource sr = new StorageResource();
        //String dna = "aaATGaaatttTAAtttgggATGTAGaaaATGttTGAaaATGaaaTGAatATGCCCGGGTAGcc";
        //String dna = "aaATGaaatttTAAtttgggATGTAGaaaATGtttTGAaaATGaaTGAaatATGCCCGGGTAGcc";
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        String dnaUpper = dna.toUpperCase();
        System.out.println(dna);
        StorageResource geneList = getAllGenes(dnaUpper); 
        processGenes(geneList);        
    }
}
