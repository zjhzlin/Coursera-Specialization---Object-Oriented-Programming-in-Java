
/**
 * Write a description of Part3 here.
 * strings, index 
 * 
 * @author Lynn Zhang 
 * @version 2021-04-08 07:05 - 07:30
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int count = 2;
        boolean find = false;
        int firstIndex = stringb.indexOf(stringa);
        if (firstIndex != -1){
            find = true;
        }
        int length_a = stringa.length();
        int secondIndex = stringb.indexOf(stringa, firstIndex + length_a);
        if (secondIndex == -1){
            find = false;
        }
        return find;
    }
    
    public String lastPart(String stringa, String stringb){
        int lengtha = stringa.length();
        int lengthb = stringb.length();
        int firstIndex = stringb.indexOf(stringa);
        if (firstIndex == -1){
            return stringb;
        }
        else{
            return stringb.substring(firstIndex+lengtha);
        }
    }
    
    public void testing(){
        String stringa = "by";
        String stringb = "A story by Ab Long";
        boolean b = twoOccurrences(stringa, stringb);
        System.out.println(b);
        // 
        String stringc = "an";
        String stringd = "banana";
        String laststring = lastPart(stringc, stringd);
        System.out.println("The part of the string after " + stringc + " in " + stringd + " is " + laststring);
    }
}
