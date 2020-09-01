package com.company;

public class Main {

    public static void main(String[] args) {

        /*
        char myChar = 'D';
        char myUnicode = '\u00A9';
        System.out.println(myChar);
        System.out.println(myUnicode);

        boolean myTrueBooleanValue = true;
        boolean myFalseBooleanValue = false;

        boolean isCustomerOverTwentyOne = true;
        */

        ////The 8 primitive data types
        //byte
        //-->short
        //int
        //long
        //float
        //-->double
        //char
        //-->boolean

        String myString = "This is a string";
        System.out.println("myString is equal to " + myString);
        myString = myString + ", and this is more.";
        System.out.println("myString is equal to " + myString);

        String numberString  = "10";
        int myInt = 50;
        double myDouble = 125.55d;
        numberString = numberString + myInt + myDouble;
        System.out.println("numberString is equal to " + numberString);


    }
}
