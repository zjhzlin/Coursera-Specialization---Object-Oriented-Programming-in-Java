package com.company;
import edu.duke.*;
//
//public class HelloWorld {
//    public void runHello () {
//        FileResource res = new FileResource("hello_unicode.txt");
//        for (String line : res.lines()) {
//            System.out.println(line);
//        }
//    }
//}

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello World");
        FileResource res = new FileResource("hello_unicode.txt");
        for (String line : res.lines()) {
            System.out.println(line);
        }
    }
}

