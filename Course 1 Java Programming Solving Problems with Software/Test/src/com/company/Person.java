package com.company;

public class Person {
    private int age;

    public Person(int initialAge){
        if(initialAge < 0) {
            age = 0;
            System.out.println("Age is not valid, setting age to 0.");
        }
        else {
            age = initialAge;
        }
    }


    public void yearPasses(){
        age += 1;
    }

    public void amIOld(){
        if(age < 13) {
            System.out.println("You are young.");
        }
        else if (age >= 13 && age < 18) {
            System.out.println("You are a teenager.");
        }
        else {
            System.out.println("You are old.");
        }

    }
}

