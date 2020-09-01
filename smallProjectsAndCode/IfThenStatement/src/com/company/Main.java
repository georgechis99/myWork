package com.company;

import org.w3c.dom.ls.LSOutput;

public class Main {

    public static void main(String[] args) {

        boolean isAlien = false;
        if (!isAlien)
            System.out.println("It is not an alien!");

        int topScore = 80;
        if (topScore >= 100) {
            System.out.println("You got the highest score!");
        }

        int secondTopScore = 60;
        if ((topScore > secondTopScore) && (topScore < 100))
            System.out.println("Greater than second top score and less than 100");

        int newValue = 50;
        if (newValue == 50)
            System.out.println("This is an error!");

        boolean isCar = false;
        if (isCar) {
            System.out.println("This is not supposed to happen");
        }

        ///TERNARY operator
        isCar = true;
        boolean wasCar = isCar ? true : false;
        if(wasCar)
            System.out.println("wasCar is true");


        ///TERNARY operator
        int ageOfClient = 18;
        boolean isEighteenOrOver = (ageOfClient >= 18) ? true : false;
        if(isEighteenOrOver)
            System.out.println("Yes, he is 18 or over.");
        else
            System.out.println("No, he is not 18 or over.");

         boolean check = false;
         int result = (check)? 1 : 0;
         if(result == 1)
             System.out.println("First boolean was TRUE");
         else
             System.out.println("First bolean was FALSE");


    }
}
