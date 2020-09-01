package com.timbuchalka;


import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = 1;
        int sum = 0;
        while(count <= 10){
            int number;
            System.out.println("Enter number #" + count + ": ");
            boolean isNextInt = scanner.hasNextInt();
            if(isNextInt){
                number = scanner.nextInt();
                count++;
                sum += number;
            }
            else {
                System.out.println("Invalid Number");
            }
            scanner.nextLine();
        }
        scanner.close();
        System.out.println("The sum of the 10 numbers entered is: " + sum);
    }
}
