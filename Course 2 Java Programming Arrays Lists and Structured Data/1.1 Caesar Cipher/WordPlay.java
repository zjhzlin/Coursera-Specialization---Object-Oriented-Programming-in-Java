
/**
 * WordPlay:
 * to transform words from a file into another form, such as replacing vowels with an asterix.
 * 
 * @author Lynn Zhang   
 * @version 2021-04-11 06:06 - 06:40
 */
public class WordPlay {
    
    public boolean isVowel (char ch) {
        //return true if ch is a vowel, and false otherwise
        String vowels = "aeiouAEIOU";
        int idx = vowels.indexOf(ch);
        if (idx == -1){
            return false;
        }
        else{
            return true;
        }               
    }
    
    public String replaceVowels (String phrase, char ch) {
        // return a string that is the phrase with all(upper and lower) the vowels replaced by ch
    
        // find the vowels and replace it. need to use StringBuilder
        StringBuilder phraseB = new StringBuilder(phrase);
        for (int i = 0; i < phraseB.length(); i++) {
            // check whether it is a vowel
            char currChar = phraseB.charAt(i);
            if (isVowel(currChar)) {
                phraseB.setCharAt(i, ch);
            }
        }
        return phraseB.toString();               
    }
    
    
    public String emphasize (String phrase, char ch) {
        // replace the ch in phrase according to rules 
        StringBuilder phraseB = new StringBuilder(phrase);
        StringBuilder phraseLower = new StringBuilder(phrase.toLowerCase());
        char chLower = Character.toLowerCase(ch);
        for (int i = 0; i < phraseB.length(); i++) {
            char currChar = phraseLower.charAt(i);
            if (currChar == ch) {
                if (i%2 == 0) {    //even index, odd location
                    phraseB.setCharAt(i, '*');
                }
                else {      // odd index, even location
                    phraseB.setCharAt(i, '+');
                }
            }
        }
        return phraseB.toString();
    }
    
    public void tester(){
        // test 1
        char ch = 'a';
        System.out.println(isVowel(ch));
        
        // test 2
        String phrase = "Hello World aABC";
        ch = '*';
        System.out.println(replaceVowels(phrase, ch));
        
        // test 3
        phrase = "Mary Bella Abracadabra";
        ch = 'a';
        System.out.println(emphasize(phrase, ch));
    }

}
