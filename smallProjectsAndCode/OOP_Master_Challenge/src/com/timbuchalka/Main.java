package com.timbuchalka;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        double grandTotal = 0;

        while (flag) {
            System.out.println("Welcome to Bill's Burgers!");
            System.out.println("Which type of burger would you like?" + "\n" +
                    "Basic hamburger(10$) --> press '1'" + "\n" +
                    "Healthy Hamburger(12$) --> press '2'" + "\n" + "" +
                    "Deluxe Hamburger(18$)(comes with extra chips and drinks) --> press '3'");

            int pressedKey = scanner.nextInt();
            scanner.nextLine();
            switch (pressedKey) {
                case 1: {
                    Hamburger hamburger = new Hamburger("Basic hamburger", "White bread", "Pork", 10);
                    System.out.println("Would you like any additional items?");
                    System.out.println("Lettuce?(2$) (y/n)");
                    String key = scanner.next();
                    if (key.contentEquals("y"))
                        hamburger.addLettuce();
                    System.out.println("Tomato?(2$) (y/n)");
                    key = scanner.next();
                    if (key.contentEquals("y"))
                        hamburger.addTomato();
                    System.out.println("Carrot?(2$) (y/n)");
                    key = scanner.next();
                    if (key.contentEquals("y"))
                        hamburger.addCarrot();

                    hamburger.showPrice();
                    grandTotal += hamburger.getPrice();
                    break;
                }

                case 2: {
                    HealthyBurger healthyBurger = new HealthyBurger("Healthy Burger", "Brown rye bread roll", "Beef", 12);
                    System.out.println("Would you like any additional items?");
                    System.out.println("Lettuce?(2$) (y/n)");
                    String key = scanner.next();
                    if (key.contentEquals("y"))
                        healthyBurger.addLettuce();
                    System.out.println("Tomato?(2$) (y/n)");
                    key = scanner.next();
                    if (key.contentEquals("y"))
                        healthyBurger.addTomato();
                    System.out.println("Carrot?(2$) (y/n)");
                    key = scanner.next();
                    if (key.contentEquals("y"))
                        healthyBurger.addCarrot();
                    System.out.println("Avocado?(3$) (y/n)");
                    key = scanner.next();
                    if (key.contentEquals("y"))
                        healthyBurger.addAvocado();
                    System.out.println("Rucola?(3$) (y/n)");
                    key = scanner.next();
                    if (key.contentEquals("y"))
                        healthyBurger.addAvocado();

                    healthyBurger.showPrice();
                    grandTotal += healthyBurger.getPrice();
                    break;
                }

                case 3: {
                    DeluxeBurger deluxeBurger = new DeluxeBurger("Deluxe Burger", "White bread", "Pork and Beef", 18);
                    System.out.println("No extra additions are allowed for this selection.");

                    deluxeBurger.showPrice();
                    grandTotal += deluxeBurger.getPrice();
                    break;
                }
            }
            System.out.println("Would you like anything else? (y/n)");
            String key = scanner.next();
            if (key.contentEquals("y"))
                continue;
            else
            {
                showGrandTotal(grandTotal);
                flag = false;
            }
        }
    }

    public static void showGrandTotal(double total){
        System.out.println("Your grand total is now " + total + "$ . Thank you and enjoy!");
    }
}
