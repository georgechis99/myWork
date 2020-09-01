package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        int switchValue = 3;

        switch(switchValue){
            case 1:
                System.out.println("Value was 1");
                break;
            case 2:
                System.out.println("Value was 2");
                break;

            case 3: case 4: case 5:
                System.out.println("Value was 3 or 4 or 5");
                System.out.println("Actually it was a " + switchValue);
                break;

            default:
                System.out.println("Value was not 1 or 2");
                break;
        }


        //challenge : write a similar statement
        // but using a char instead of an int

        char charValue = 'A';

        switch(charValue){
            case 'A':
                System.out.println("The value was an A");
                break;
            case 'B':
                System.out.println("The value was a B");
                break;
            case 'C':
                System.out.println("The value was a C");
                break;
            case 'D':
                System.out.println("The value was a D");
                break;
            default:
                System.out.println("The value was neither A,B,C or D");
                System.out.println("Actually it was a " + charValue);
                break;
        }

        String month = "JaNuArY";

        //switch used with toLowerCase
        //to avoid writing mistakes
        switch(month.toLowerCase()){
            case "january":
                System.out.println("Jan");
                break;
            case "june":
                System.out.println("Jun");
                break;
            default:
                System.out.println("Not sure");
        }
    }
}
