package WebLogProgram;


/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Lynn Zhang
 * @version 2021-04-14 19:30 - 19:42 3.1 task
 *                     19:47 - 20:30 3.2 task unique IP 
 *                     22:29 - 23:10 3.3 task
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // read the file and put the log into records
         FileResource fr = new FileResource();
         for (String line: fr.lines()) {
            // create a LogEntry
            LogEntry log = WebLogParser.parseEntry(line);
            // store to records
            records.add(log);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
        // return number of unique IP addresses
        ArrayList<String> uniqueIp = new ArrayList<String>();
        for (LogEntry le: records) {
            String currIp = le.getIpAddress();
            int idx = uniqueIp.indexOf(currIp);
            if (idx == -1) {    //new ip, not in the list
                uniqueIp.add(currIp);
            }
        }
        return uniqueIp.size();
     }
     
     public void printAllHigherThanNum(int num) {
        // status code greater than num, print the records out.
        System.out.println("Status code greater than: " + num);
        for (LogEntry le: records) {
            int currStatus = le.getStatusCode();
            if (currStatus > num) {
                System.out.println(le);
            }         
        }       
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         //someday format: MMM DD, ex: Dec 04
         ArrayList<String> uniIP = new ArrayList<String>();
         for (LogEntry le: records) {
            Date currDate = le.getAccessTime();
            String date = currDate.toString();
            //System.out.println("date is "+ date);
            int indexDate = date.indexOf(someday);
            if (indexDate != -1) {
                String currIp = le.getIpAddress();
                int idx = uniIP.indexOf(currIp);
                if (idx == -1) {    //new ip, not in the list
                    uniIP.add(currIp);
                    //System.out.println("unique ip "+ currIp);
                }   
            }
         }
         return uniIP;        
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIp = new ArrayList<String>();
         for (LogEntry le: records) {
            int currStatus = le.getStatusCode();
            //System.out.println("Current status: " + currStatus);
            if (currStatus >= low && currStatus <= high) {
                // if it is a unique IP that haven't seen before
                //System.out.println("Yeah");
                String currIp = le.getIpAddress();
                int idx = uniqueIp.indexOf(currIp);
                if (idx == -1) {    //new ip, not in the list
                    uniqueIp.add(currIp);
                    //System.out.println("unique ip "+ currIp);
                }  
            }         
         }  
         return uniqueIp.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
        // maps an IP address to the number of times that IP address appears in records
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
        for (LogEntry le: records) {
            String currIp = le.getIpAddress();
            if(countMap.containsKey(currIp)) {
                int count = countMap.get(currIp);
                countMap.put(currIp, count+1);
            }
            else {
                countMap.put(currIp, 1);
            }                     
        }
        return countMap;       
     }
     
     public int mostNumberVisitsByIP (HashMap<String, Integer> map) {
        // return the max number of visits to this website by a single IP
        int max = 0;
        for(String ip: map.keySet()) {
            int count = map.get(ip);
            if (count > max) {
                max = count;
            }
        }
        return max;
     }
     
     public ArrayList<String> iPsMostVisits (HashMap<String, Integer> map) {
        // ips that have the max number of visits
        ArrayList<String> ips = new ArrayList<String>();
        int max = mostNumberVisitsByIP(map);
        for(String ip: map.keySet()) {
            int count = map.get(ip);
            if (count == max) {
                ips.add(ip);
            }
        }
        return ips;       
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> ipMap = new HashMap<String, ArrayList<String>>();
        for (LogEntry le: records) {
            String currIp = le.getIpAddress();
            Date currDate = le.getAccessTime();
            String date = currDate.toString().substring(4,10);
            //System.out.println("date is "+ date);
            if (ipMap.containsKey(date)) {   //if date is in the map
                ArrayList<String> ipList = ipMap.get(date);
                ipList.add(currIp);
                ipMap.put(date, ipList);
            }
            else {          //date is not in the map yet, add new date
                ArrayList<String> ipList = new ArrayList<String>();
                ipList.add(currIp);
                ipMap.put(date, ipList);
            }
         
        }
        return ipMap; 
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipMap) {
        int max = 0;
        String dayMost = "";
        for (String date: ipMap.keySet()) {
            ArrayList<String> ipList = ipMap.get(date);
            int size = ipList.size();
            if (size > max) {
                max = size;
                dayMost = date;
            }
        }
        return dayMost;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipMap, String date) {
        ArrayList<String> iPsList = new ArrayList<String>();
        ArrayList<String> ipList = ipMap.get(date);
        // find the IP with most visits
        HashMap<String, Integer> countMap = new HashMap<String, Integer>();
        for (String ip: ipList) {
            if(countMap.containsKey(ip)) {
                int count = countMap.get(ip);
                countMap.put(ip, count+1);
            }
            else {
                countMap.put(ip, 1);
            }                     
        }
        int max = mostNumberVisitsByIP (countMap);
        //System.out.println("max number: " + max); 
        for (String ip: countMap.keySet()){
            int count = countMap.get(ip);
            if (count == max){
                //System.out.println("Yeah ip" + ip );
                iPsList.add(ip);
            }           
        }
        return iPsList;
    }
        

}
