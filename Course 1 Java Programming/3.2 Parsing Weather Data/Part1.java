
/**
 * Parsing weather data
 * to find the coldest day of the year and 
 * other interesting facts about the temperature and humidity in a day. 
 * 
 * @author Lynn Zhang
 * @version 2021-04-09
 *          2021-04-10 06:58 - 07:30 issues solved
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        //start with coldest so far as nothing
        CSVRecord coldestSoFar = null;
        //loop in all the rows
        for(CSVRecord currRow: parser){
            if(coldestSoFar == null){
                coldestSoFar = currRow;     
            }
            else{
                // get the temperature and compare
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currTemp!=-9999 && currTemp < coldestTemp){
                    coldestSoFar = currRow;
                }
            }
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest temp was " + coldest.get("TemperatureF") 
                            + " at " + coldest.get("DateUTC"));
    }
    
    public boolean isNumeric(String string){
        int intValue;
        if(string == null || string.equals("")){
            System.out.println("null string");
            return false;
        }
        try{
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e){
            System.out.println("Input cannot be parsed to Integer");
        }
        return false;
    }
    
    
    public CSVRecord getMinOfTwo(CSVRecord currRow, CSVRecord minSoFar, String type){
        if(minSoFar == null){
            minSoFar = currRow;     
        }
        else{
            // get the temperature and compare           
            String currString = currRow.get(type);
            System.out.println(currString);
            //if (currString!="N/A"){
            if (isNumeric(currString)){ 
                double currTemp = Double.parseDouble(currRow.get(type));
                double minTemp = Double.parseDouble(minSoFar.get(type));
                if (currTemp!=-9999 && currTemp < minTemp){
                    minSoFar = currRow;
                }
            }
        }
        return minSoFar;
    }
    
    public void printAllTemp(String filename){
        System.out.println("All the temperatures on the coldest day were: ");
        String f = "nc_weather/2014/" + filename;
        FileResource fr = new FileResource(f);
        CSVParser csv = fr.getCSVParser();
        for (CSVRecord row: csv){
            System.out.println(row.get("DateUTC") + ": " + row.get("TemperatureF"));
        }
    }
    
    
    public CSVRecord fileWithColdestTemperature(){
        //keep track of the coldest
        CSVRecord coldestSoFar = null;
        //select files
        DirectoryResource dr = new DirectoryResource();
        //read each file and get the coldest temp
        String fileColdest = "";
        for (File f: dr.selectedFiles()) {
        //compare with the coldest and update
            //System.out.println(f.getName());
            FileResource fr = new FileResource(f);
            CSVRecord currRow = coldestHourInFile(fr.getCSVParser());
            if(coldestSoFar == null){
                coldestSoFar = currRow; 
                fileColdest = "" + f.getName(); 
            }
            else{
                // get the temperature and compare
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currTemp!=-9999 && currTemp < coldestTemp){
                    coldestSoFar = currRow;
                    fileColdest = "" + f.getName(); 
                    //System.out.println(fileColdest);
                }
            }
        }                               
        //return the name of the file
        System.out.println("Coldest day was in file weather - " + fileColdest); 
        printAllTemp(fileColdest);
        return coldestSoFar;
    }
    
   
    
    public void TestFileWithColdestTemperature(){
        CSVRecord coldest = fileWithColdestTemperature();
        System.out.println("Coldest temp on that day was " + coldest.get("TemperatureF") 
                            + " at " + coldest.get("TimeEST"));
        
        
    }
    
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        //start with coldest so far as nothing
        CSVRecord lowestSoFar = null;
        //loop in all the rows
        String type = "Humidity";
        for(CSVRecord currRow: parser){
            lowestSoFar = getMinOfTwo(currRow, lowestSoFar, type);
        }
        return lowestSoFar;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-04-01.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);               
        System.out.println("Lowest Humidity was " + csv.get("Humidity") 
                            + " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        //keep track of the coldest
        CSVRecord lowestSoFar = null;
        //select files
        DirectoryResource dr = new DirectoryResource();
        //read each file and get the coldest temp
        String type = "Humidity";
        for (File f: dr.selectedFiles()) {
        //compare with the coldest and update
            FileResource fr = new FileResource(f);
            System.out.println(f.getName());
            CSVRecord currRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getMinOfTwo(currRow, lowestSoFar, type);
            System.out.println(lowestSoFar.get(type));
        }                               
        return lowestSoFar;
    }
          
    public void TestLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") 
                            + " at " + csv.get("DateUTC"));
        
    }
    
    
    public double averageTemperatureFile(CSVParser parser){
        double sum = 0;
        int count = 0;
        // read each row and add to the sum; also keep track of the count
        for (CSVRecord currRow: parser){
            double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
            if (currTemp != -9999){
                sum += currTemp;
                count += 1;
            }
        }
        // average = sum/count
        double average = sum/count;
        return average;
    }
    
    public void testAverageTemperatureFile(){
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-06-01.csv");
        CSVParser csv = fr.getCSVParser();
        
        double avgTemp = averageTemperatureFile(csv);
        System.out.println("Average temperature in file is " + avgTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0;
        int count = 0;

        // read each row and add to the sum; also keep track of the count
        for (CSVRecord currRow: parser){
            String currString = currRow.get("Humidity");
            if (currString != "N/A"){
                int currHumid = Integer.parseInt(currRow.get("Humidity"));
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));                
                if (currTemp != -9999 && currHumid > value){
                    sum += currTemp;
                    count += 1;
                }
            }                       
        }
        // average = sum/count
        
        if(count==0){
            return -1;
        }
        else{ 
            double average = sum/count;
            return average;
        }
        
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource("nc_weather/2014/weather-2014-03-30.csv");
        CSVParser csv = fr.getCSVParser();
        
        double avgTemp = averageTemperatureWithHighHumidityInFile(csv, 80);
        
        if(avgTemp==-1){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average temperature when high humidity is " + avgTemp);
        }
    }
    
}
