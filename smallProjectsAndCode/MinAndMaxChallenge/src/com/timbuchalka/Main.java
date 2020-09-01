package com.timbuchalka;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

//        boolean first = true;

        while(true){
            System.out.println("Enter number: ");

            int number;
            boolean isNumberValid = scanner.hasNextInt();
            if(isNumberValid){
                number = scanner.nextInt();

//                if(first){
//                     first = false;
//                     min = number;
//                     max = number;
//                }

                if(number < min)
                    min = number;
                if(number > max)
                    max = number;
            }
            else
            {
                System.out.println("The minimum entered number is: " + min);
                System.out.println("The maximum entered number is: " + max);
                break;
            }
            scanner.nextLine();
        }
        scanner.close();
    }
}
