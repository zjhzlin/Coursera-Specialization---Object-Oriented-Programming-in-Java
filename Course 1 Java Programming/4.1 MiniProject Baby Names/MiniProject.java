
/**
 * MiniProject: Baby names 
 * 
 * @author Lynn Zhang
 * @version 2021-04-10 09:50 - 10:50; 15:20 - 15:50 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class MiniProject {
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int totalGNames = 0;
        int totalBNames = 0;
        for (CSVRecord row: fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(row.get(2));
            totalBirths += numBorn;
            if (row.get(1).equals("F")){
            // girl name
                totalGirls += numBorn;
                totalGNames += 1;
            }
            else{
                totalBoys += numBorn;
                totalBNames += 1;
            }
        }
        int totalNames = totalGNames + totalBNames;
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total girl births = " + totalGirls);
        System.out.println("Total boy births = " + totalBoys);
        System.out.println("Total girl names = " + totalGNames);
        System.out.println("Total boy names = " + totalBNames);
        System.out.println("Total names = " + totalNames);
    }
    
    public int getRank(int year, String name, String gender){
        String filename = "us_babynames/us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        int rank = 0;
        //search for the gender and then the name
        for (CSVRecord row: fr.getCSVParser(false)){
            if(row.get(1).equals(gender)){
                rank += 1;
                if (row.get(0).equals(name)){
                    return rank;
                }               
            }
        }
        return -1;
        
    }
    
    public String getName(int year, int rank, String gender){
        //return the name of the person at that rank
        //String filename = "us_babynames/us_babynames_test/yob"+year+"short.csv";   //for testing
        String filename = "us_babynames/us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        int currRank = 0;
        for (CSVRecord row: fr.getCSVParser(false)){
            if(row.get(1).equals(gender)){
                currRank += 1;
                if (currRank == rank){
                    return row.get(0);
                }               
            }
        }
        // if the rank does not exist, then NO NAME is returned
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        // what name would have been named if they were born in a different year, based on the same popularity
        
        // determine the rank of name in the year
        int rank = getRank(year, name, gender);
        // then print the name in new year that has the same rank 
        String newName = getName(newYear, rank, gender);
        // print the result
        if (gender.equals("F")){
            System.out.println(name + " born in " + year + " would be " + newName + 
                                " if she was born in " + newYear + ".");
        }
        else{
            System.out.println(name + " born in " + year + " would be " + newName + 
                                " if he was born in " + newYear + ".");  
        }
    }
    
    public int yearOfHighestRank(String name, String gender){                
        DirectoryResource dr = new DirectoryResource();
        int year;
        int currRank = 0;
        int minRank = 999999999;
        //int count = 0;
        int minYear = 0;
        // for each file get the rank, compare the rank with the current rank, 
        // if rank smaller, the update that year
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            // get year
            String filename = f.getName();
            int year_index = filename.indexOf("b");
            String yearString = filename.substring(year_index+1,year_index+5);
            year = Integer.parseInt(yearString);
            System.out.println(year);
            // get rank in that year
            currRank = getRank(year,name,gender);           
            // if such rank is found, compare with the minrank and update
            if(currRank != -1 && currRank < minRank){
                minRank = currRank;
                minYear = year;     
            }          
        }
        
        // if rank = -1 , return -1
        if(minYear == 0){
            return -1;
        }
        return minYear;
    }
    
    public double getAverageRank(String name, String gender){
        //average rank of the name and gender over the selected files
        DirectoryResource dr = new DirectoryResource();
        int year;
        int count = 0;
        int currRank = 0;
        double sumRank = 0;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            // get year
            String filename = f.getName();
            int year_index = filename.indexOf("b");
            String yearString = filename.substring(year_index+1,year_index+5);
            year = Integer.parseInt(yearString);
            System.out.println(year);
            // get rank in that year
            currRank = getRank(year,name,gender);    
            if (currRank != -1){
                sumRank += currRank;
                count += 1;
            }                       
        }
        
        // return -1 if the name is not ranked in any of the selected files
        if (count == 0){
            return -1;
        }
        else{
            double average = sumRank/count;
            return average;
        }
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        // return the total num of births of whose names with the same gender,
        // and same year who are ranked higher than name. 
        //String filename = "us_babynames/us_babynames_test/yob"+year+"short.csv";
        String filename = "us_babynames/us_babynames_by_year/yob"+year+".csv";
        FileResource fr = new FileResource(filename);
        
        int targetRank = getRank(year,name,gender);
        if (targetRank == -1){
            return -1;
        }
        
        // find the ones that is smaller than the targetRank
        
        int currRank = 0;
        int numBirth = 0;
        int currBirth = 0;
        for (CSVRecord row: fr.getCSVParser(false)){
            if(row.get(1).equals(gender)){
                currRank += 1;
                if (currRank < targetRank){
                   System.out.println(row.get(0));
                   currBirth = Integer.parseInt(row.get(2));
                   numBirth += currBirth;                                     
                }   
                else{
                    break;
                }
            }
        }
        return numBirth;                       
    }
    
    public void tester(){
        String filename = "us_babynames/us_babynames_by_year/yob1905.csv";
        FileResource fr = new FileResource(filename);
        // task 1
        //totalBirths(fr);
        
        // task 2
        int year = 1982;
        String name = "Frank";
        String gender = "M";
        //int rank = getRank(year, name, gender);
        //System.out.println("Rank of "+ name + " in gender "+ gender + " in year " + year + " is: " + rank);
    
        // task 3
        //int rankSearch = 450;
        gender = "M";
        //String nameRank = getName(year, rankSearch, gender);
        //System.out.println("Name is " + nameRank + " with rank " + rankSearch + " in year " + year + " in gender "+ gender);
        
        // task 4
        name = "Owen";
        year = 1974;
        int newYear = 2014;
        gender = "M";    
        //whatIsNameInYear(name, year, newYear, gender);
        
        // task 5
        name = "Mich";
        gender = "M";
        //System.out.println("Year of highest rank is " + yearOfHighestRank(name,gender));
        
        //task 6
        name = "Robert";
        gender = "M";
        //System.out.println("Average rank of "+ name + " " +  gender + " is: "           
        //                    + getAverageRank(name, gender));
        
        //task 7
        year = 1990;
        name = "Drew";
        gender = "M";
        System.out.println("Total births ranked higher than " + name + " " +  gender + " : " + 
                            getTotalBirthsRankedHigher(year, name, gender));
    }
}
