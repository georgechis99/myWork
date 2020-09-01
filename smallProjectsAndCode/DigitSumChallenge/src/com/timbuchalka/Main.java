package com.timbuchalka;

public class Main {

    public static void main(String[] args) {

        int validValue = 125;
        int invalidValue = 8;

        System.out.println("Returned value for " + validValue + " is " + sumDigits(validValue));
        System.out.println("Returned value for " + invalidValue + " is " + sumDigits(invalidValue));
    }

    private static int sumDigits(int number){
        if(number < 10)
            return -1;
        int sumOfDigits = 0;
        while(number > 0){
            int leastSignificantDigit;
            leastSignificantDigit = number % 10;
            sumOfDigits += leastSignificantDigit;
            number /= 10;
        }
        return sumOfDigits;
    }

}
