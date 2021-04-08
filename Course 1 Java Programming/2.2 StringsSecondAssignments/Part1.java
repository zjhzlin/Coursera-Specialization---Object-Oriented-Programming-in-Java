
/**
 * Part1 - Finding Many Genes
 * A. Find a gene in a strand of DNA where the stop codon 
 * could be any of the three stop codons “TAA”, “TAG”, or “TGA”.
 * 
 * B. Find all the genes (where the stop codon could be any 
 * of the three stop codons) in a strand of DNA.
 * 
 * @author Lynn Zhang
 * @version 2021-04-08 08:50 - 10:10 
 */
public class Part1 {
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
    
    public void printAllGenes(String dna){
        String currDna = dna;
        System.out.println("Current dna: " + currDna);
        while(true){
            String gene = findGene(currDna);
            System.out.println("Current gene: " + gene);
            if (currDna == ""){
                System.out.println("No more gene found");
                break;
            }
            if (gene != ""){
                System.out.println("Gene is: " + gene);
                int nextIndex = currDna.indexOf(gene) + gene.length();
                System.out.println("next Index: " + nextIndex);
                currDna = currDna.substring(nextIndex);  
                System.out.println("Current dna: " + currDna);
            }
            else{
            // find next start codon
                System.out.println("no Gene found");
                if(currDna.indexOf("ATG") == -1){
                    break;
                }
                int nextIndex = currDna.indexOf("ATG") + 3;
                System.out.println("next Index: " + nextIndex);
                currDna = currDna.substring(nextIndex);
                System.out.println("Current dna: " + currDna);
            }
        }
    }
    
    public void testPrintAllGenes(){
        String dna = "aaATGaaatttTAAtttgggATGtttaaaTAGaaaATGttTGAaaATGaaaTGAat";
        System.out.println(dna);
        printAllGenes(dna);               
    }
    
    public void testFindGene(){
        String dna1 = "ATGAAAGGGTTTTAAGGG";   // valid stop TAA
        String dna2 = "AAAGGGTTTAAAGGG";    //no start ATG
        String dna3 = "aaATGAAAGGGTGATTTTAATTTTAG";   // 3 valid stops
        String dna4 = "aaagggttt";
        String dna5 = "TTATGAAAGGTAAGGG";    //no valid stop
        String dna6 = "AAATGCCCTAGCTAGATTAAGAAACC";  //2 valid stops  
        String[] dnas = {dna1, dna2, dna3, dna4, dna5, dna6};
        for (String i: dnas){
            System.out.println("Originial string is "+i);
            String gene = findGene(i);
            System.out.println("Gene is " + gene);
        }
        
    }
    
    public void testFindStopCodon (){
        //String dna = "ATGGGGTTTTAATTTGGG";
        String dna = "ATGgg";
        String stopCodon = "TAA";
        int startIndex = dna.indexOf("ATG");
        int stopIndex = findStopCodon(dna,startIndex,stopCodon);
        System.out.println("start index: " + startIndex);
        if(stopIndex == dna.length()){
            System.out.println("No stop codon found");
        }
        else{
            System.out.println("stop index: " + stopIndex);
    
        }

    }
}