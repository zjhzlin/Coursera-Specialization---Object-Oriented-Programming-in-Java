package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int x = 5;
        if (x == 5){
            int i = 6;
            int y = 72;
            System.out.println("i = " + i + " y = " + y);
        }
        System.out.println("x = " + x + " y = " + x);

        String filename = "";
        if(true) {
            filename = filename + "f.name";
        }
        System.out.println("filename is "+filename);

        double currTemp = -9999;
        boolean flag = (currTemp==-9999);
        System.out.println(flag);

        String humid = "N/A";
        flag = (humid!="N/A");
        System.out.println(flag);
        int intValue = Integer.parseInt(humid);
        System.out.println(intValue);


    }
}
