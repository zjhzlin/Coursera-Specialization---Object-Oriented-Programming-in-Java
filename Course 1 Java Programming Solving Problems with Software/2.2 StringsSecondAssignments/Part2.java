
/**
 * Part2: HowMany - Finding Multiple Occurrences
 * write a method to determine:
 * how many occurrences of a string appear in another string
 * 
 * @author Lynn Zhang
 * @version 2021-04-08 20:01 - 20:10
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
    
    public void testHowMany(){
        //String stringa = "GAA";
        //String stringb = "ATGAACGAATTGAATC";
        String stringa = "AA";
        String stringb = "ATAAAAaaAA";
        int count = howMany(stringa, stringb);
        System.out.println(count);
    }
}
