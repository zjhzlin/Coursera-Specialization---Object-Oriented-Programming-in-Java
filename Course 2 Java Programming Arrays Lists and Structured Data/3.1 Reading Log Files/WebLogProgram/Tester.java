package WebLogProgram;


/**
 * Write a description of class Tester here.
 * 
 * @author Lynn Zhang
 * @version 2021-04-14 19:30-19:42
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        String file = "short-test_log.txt";
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(file);
        la.printAll();
    }
    
    public void testUniqueIP() {
        //String file = "weblog-short_log.txt";
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("");
        //la.printAll();
        int numUnique = la.countUniqueIPs();
        System.out.println("countUniqueIPs: " + numUnique);
        
        //la.printAllHigherThanNum(400);
        
        String someday = "Sep 24";
        ArrayList<String> uniqueIp = la.uniqueIPVisitsOnDay(someday);
        System.out.println("someday: " + someday + " how many unique IP? " + uniqueIp.size());
        for (String s: uniqueIp) {
            System.out.println(s);
        }
        
        int low = 400;
        int high = 499;
        int count = la.countUniqueIPsInRange(low, high);
        System.out.println("Count Unique IP in range:" + low + " and " + high + ":");
        System.out.println(count);
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("");
        la.printAll();
        HashMap<String, Integer> countMap = la.countVisitsPerIP();
        for (String ip: countMap.keySet()) {
            System.out.println(ip+": "+countMap.get(ip));
        }
        
        int mostNum = la.mostNumberVisitsByIP (countMap);
        System.out.println("mostNumberVisitsByIP: " + mostNum);
        
        ArrayList<String> iPs = la.iPsMostVisits(countMap);
        System.out.println("iPsMostVisits: ");
        for (String ip: iPs) {
            System.out.println(ip+": "+countMap.get(ip));
        }
        
        HashMap<String, ArrayList<String>> ipMap = la.iPsForDays();
        for (String date: ipMap.keySet()) {            
            ArrayList<String> ipList = ipMap.get(date);
            System.out.println(date+": "+ipList.size());
            //for (String ip: ipList) {
            //    System.out.println(ip);
            //}
        }
        
        String day = la.dayWithMostIPVisits(ipMap);
        System.out.println("dayWithMostIPVisits: "+day);
        
        String date = "Sep 29";
        ArrayList<String> iPsList = la.iPsWithMostVisitsOnDay(ipMap, date);
        System.out.println("iPsWithMostVisitsOnDay: "+date);
        for (String ip: iPsList) {
            System.out.println(ip);
        }
       
    }
}
