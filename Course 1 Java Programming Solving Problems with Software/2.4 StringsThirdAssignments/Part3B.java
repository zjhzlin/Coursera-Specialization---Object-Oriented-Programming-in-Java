/**
 * Write a description of Part3B here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part3B {
    public int findStopCodon (String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        System.out.println("stop codon index "+ currIndex);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if (diff%3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return -1;
    }
    
    public String findGene(String dna, int where){
        String startCodon = "ATG";
        int startIndex = dna.indexOf(startCodon, where);
        System.out.println(startIndex);
        
        // no start codon found
        if (startIndex == -1){
            return "";
        }
        //
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        System.out.println(taaIndex +" " +  tagIndex + " " + tgaIndex);
        // find the minimum of the three
        int minIndex = 0;
        if (taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }
        else{
            minIndex = taaIndex;
        }
        if (minIndex == -1 ||
            (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;              
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    
    public StorageResource getAllGenes(String dna){
        //create new storageresource
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        int count = 0;
        while(true){
            //String gene = findGene(currDna);
            String currentGene = findGene(dna, startIndex);
            //System.out.println("Current gene: " + gene);
            if (currentGene.isEmpty()){
                System.out.println("No more gene found");
                break;
            }
            System.out.println(currentGene);
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) 
                         + currentGene.length();
            count = count + 1;                   
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
        int countTotalCTG = 0;
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
            countTotalCTG = countTotalCTG + countCTG(gene);
        }
        System.out.println("Longest length is: " + lenLongest);
        System.out.println(countLonger + " genes longer than 60");
        System.out.println(countCG + " genes with more than 0.35 ratio");
        System.out.println("In total CTG NUMBER: " + countTotalCTG);
    }
    
    public int countCTG(String dna){
        int count = howMany("CTG",dna);
        return count;        
    }
    
    public void testProcessGenes(){
        //StorageResource sr = new StorageResource();
        //String dna = "aaATGaaatttTAAtttgggATGTAGaaaATGttTGAaaATGaaaTGAatATGCCCGGGTAGcc";
        //String dna = "aaATGaaatttTAAtttgggATGTAGaaaATGtttTGAaaATGaaTGAaatATGCCCGGGTAGcc";
        //FileResource fr = new FileResource("brca1line.fa");
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        String dnaUpper = dna.toUpperCase();
        System.out.println(dna);
        StorageResource geneList = getAllGenes(dnaUpper); 
        processGenes(geneList);        
        System.out.println("CTG in DNA: " + countCTG(dnaUpper));
    }
}
