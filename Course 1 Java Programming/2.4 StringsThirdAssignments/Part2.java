
/**
 * Part2:
 * calculate CG ratio in dna
 * - int to double, and / caculation between int and double
 * 
 * @author Lynn Zhang   
 * @version 2021-04-09 06:48 - 07:04
 */
public class Part2 {
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
    
    public int countCTG(String dna){
        int count = howMany("CTG",dna);
        return count;        
    }
    
    public void testCountCTG(){
        String dna = "CTGAAACTGCG";
        System.out.println(countCTG(dna));
    }
    
  
    public void testCgRatio(){
        String dna = "ATGCCATAG";
        double ratio = cgRatio(dna);
        System.out.println(ratio);
    }
}
