
/**
 * Write a description of Part4 here.
 * Finding web links
 * 
 * @author Lynn Zhang 
 * @version 2021-04-08 07:30 - 07:53
 */

import edu.duke.*;

public class Part4 {
    
    public void findWebLinks(){    
        String link = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        URLResource ur = new URLResource(link);
        String target = "youtube.com";
        String quotation = "\"";
        for (String word: ur.words()){
            // consider lower and upper case
            String lower_word = word.toLowerCase();         
            int index = lower_word.indexOf(target);
            if (index!=-1){
                // print the original word 
                System.out.println(word);
                //find the quotation marks, only need the content within quotation marks
                int qstartIndex = word.lastIndexOf(quotation, index);
                int qendIndex = word.indexOf(quotation, qstartIndex+1);
                System.out.println(word.substring(qstartIndex+1,qendIndex));
            }

        }
    }
}
