package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        System.out.println("10 000 at 2% interest is " + calculateInterest(10000, 2));

        ///challenge Sum 3 and 5
        int sumOfNumbers = 0, countIs = 0;
        for (int i = 1; i <= 1000; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                sumOfNumbers+=i;
                countIs++;
                System.out.println(i);
                if (countIs > 5) {
                    System.out.println(sumOfNumbers);
                    break;
                }
            }
        }

        for(int i=0; i<5; i++){
            System.out.println("Loop " + i + " hello!");
        }

        for(double j=2; j<=8; j++){
            System.out.println("10 000 at " + j + "% interest is " + String.format("%.2f",calculateInterest(10_000.0,j)));
        }

        System.out.println("-----------------------");

        for(double j=8; j>=2; j-=0.5){
            System.out.println("10 000 at " + j + "% interest is " + String.format("%.2f",calculateInterest(10_000.0,j)));
        }

        System.out.println("-----------------------");
        ///Create a for statement using any range of numbers
        //Determine if the number is a prime number using the isPrime method
        //if it is a prime number print it out AND increment a count of the
        //number of prime numbers found
        //if that count exceeds 3 exit the for loop
        //hint: use the break; statement to exit

        int count=0;
        for(int i=10; i>0; i++){
            if(isPrime(i)) {
                count++;
                System.out.println("Prime number found: " + i);
            }
            if(count>=3)
            {
                System.out.println("Exiting for loop");
                break;
            }
        }

    }

    //checking if a number is prime
    public static boolean isPrime(int n){
        if(n == 1)
            return false;

        for(int i=2; i <= (long) Math.sqrt(n); i++){
            if(n % i == 0)
                return false;
        }
        return true;
    }

    public static double calculateInterest(double amount,double interestRate){
        return (amount* (interestRate/100));
    }
}
