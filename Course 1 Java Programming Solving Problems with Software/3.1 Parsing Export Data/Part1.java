
/**
 * Parsing export data
 * 
 * @author Lynn ZHANG
 * @version 2021-04-10 08:40 - 09:10
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    public String countryInfo(CSVParser parser, String country){
        //method purpose: return the export info of the country
        //look in each row of the csvrecord and try to find the country        
        for (CSVRecord row: parser){
        //if found, get the export info and return country: export info
            String currCountry = row.get("Country"); 
            if(currCountry.equals(country)){
                String export = row.get("Exports");
                String exportCountry = country + ": " + export;
                return exportCountry;
            }
        }
        //if not found, return NOT FOUND
        return "NOT FOUND";
    
    }
    
    public boolean isInString(String stringa, String stringb){
        // check whether stringa is in stringb
        int index = stringb.indexOf(stringa);
        if(index==-1){
            return false;
        }
        else{
            return true;
        }
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        // look for each row export items
        for (CSVRecord row: parser){        
            String currExport = row.get("Exports");
            // look for item 1&2 in the string? - indexOf - helper code
            if(isInString(exportItem1, currExport) && 
                isInString(exportItem2, currExport)){
                // if both are in, printout the country
                    String currCountry = row.get("Country");
                    System.out.println(currCountry);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        //return the number of countries that export the exportItem
        int count = 0;
        for (CSVRecord row: parser){        
            String currExport = row.get("Exports");
            // look for item in the string - helper code
            if(isInString(exportItem, currExport)){
                // if in, count + 1
                    count += 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        //print the names of countries and their value (who's value string is longer than amount)
        for (CSVRecord row: parser){        
            String currValue = row.get("Value (dollars)");
            // compare the length of the value string
            if(currValue.length() > amount.length()){
                // if longer, print the country with the value
                    String currCountry = row.get("Country");
                    String toPrint = currCountry + " " + currValue;
                    System.out.println(toPrint);                   
            }
        }

    }
    
    public void tester(){
        String testfile = "exports/exportdata.csv";
        //String country = "Nauru";
        String exportItem1 = "cotton";
        String exportItem2 = "flowers";
        String exportItem = "cocoa";
        FileResource fr = new FileResource(testfile);
        CSVParser parser = fr.getCSVParser();
        // task1
        //String exportCountry = countryInfo(parser, country);
        //System.out.println(exportCountry);
        
        // task 2
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, exportItem1, exportItem2);
        
        // task 3        
        parser = fr.getCSVParser();
        int num = numberOfExporters(parser, exportItem);
        System.out.println("Number of exporters for " + exportItem + ": " + num);
               
        // task 4
        String amount = "$999,999,999,999";
        parser = fr.getCSVParser();
        bigExporters(parser, amount);
    }
}
