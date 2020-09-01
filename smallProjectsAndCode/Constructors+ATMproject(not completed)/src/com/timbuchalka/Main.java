package com.timbuchalka;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount();

        Scanner scanner = new Scanner(System.in);

//        Virtually initializing your bank account
        boolean flag2 = false;
        int i;
        String s;
        System.out.println("Please fill in the next information to open an account:");

        System.out.println("   ->select a 4 digit account number: ");
        while(!flag2) {
            i = scanner.nextInt();
            if(i >= 1000 && i <= 9999){
                scanner.nextLine();
                account1.setAccountNumber(i);
                break;
            }
            else{
                System.out.println("You did not enter a 4 digit number!");
                System.out.println("Try again: ");
            }
        }

        System.out.println("   ->enter your first deposited balance: ");
        while(!flag2) {
            i = scanner.nextInt();
            if(i > 0 && i < 1_000_000){
                scanner.nextLine();
                account1.setBalance(i);
                break;
            }
            else{
                System.out.println("Value cannot be processed. Values must range between 0$ and 1 000 000$!");
                System.out.println("Try again: ");
            }
        }
        System.out.println("   ->enter your name: ");
        s = scanner.nextLine();
        account1.setCustomerName(s);

        System.out.println("   ->enter your email: ");
        s = scanner.nextLine();
        account1.setEmail(s);

        System.out.println("   ->enter your phone number: ");
        i = scanner.nextInt();
        scanner.nextLine();
        account1.setPhoneNumber(i);

        System.out.println("Account created succesfully!" );
        System.out.println("****************************");
        System.out.println("For your current balance press 1.");
        System.out.println("To deposit press 2.");
        System.out.println("To withdraw press 3.");
        System.out.println("To exit the ATM press 0.");

        int command = scanner.nextInt();
        scanner.nextLine();
        String commandContinue;

        boolean flag = true;

        while(flag) {
            switch (command) {
                case 0: {
                    flag = false;
                    break;
                }
                case 1: {
                    account1.showCurrentAmmount();
                    System.out.println("****************************");

                    System.out.println("Would you like to execute another operation? (Y/N)");
                    commandContinue = scanner.nextLine();
                    commandContinue = commandContinue.toLowerCase();
                    if(commandContinue.equals("y")) {
                        System.out.println("For your current balance press 1.");
                        System.out.println("To deposit press 2.");
                        System.out.println("To withdraw press 3.");
                        System.out.println("To exit the ATM press 0.");
                        command = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } else if(commandContinue.equals("n")){
                        flag = false;
                        break;
                    } else {
                        System.out.println("Wrong key pressed!");
                        break;
                    }
                }
                case 2: {
                    System.out.println("How much would you like to deposit?");
                    i = scanner.nextInt();
                    scanner.nextLine();
                    account1.depositFunds(i);
                    System.out.println("****************************");

                    System.out.println("Would you like to execute another operation? (Y/N)");
                    commandContinue = scanner.nextLine();
                    commandContinue = commandContinue.toLowerCase();
                    if(commandContinue.equals("y")) {
                        System.out.println("For your current balance press 1.");
                        System.out.println("To deposit press 2.");
                        System.out.println("To withdraw press 3.");
                        System.out.println("To exit the ATM press 0.");
                        command = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    }else if(commandContinue.equals("n")){
                        flag = false;
                        break;
                    } else {
                        System.out.println("Wrong key pressed!");
                        break;
                    }

                }
                case 3: {
                    System.out.println("How much would you like to withdraw?");
                    i = scanner.nextInt();
                    scanner.nextLine();
                    account1.withdrawFunds(i);
                    System.out.println("****************************");

                    System.out.println("Would you like to execute another operation? (Y/N)");
                    commandContinue = scanner.nextLine();
                    commandContinue = commandContinue.toLowerCase();
                    if(commandContinue.equals("y")) {
                        System.out.println("For your current balance press 1.");
                        System.out.println("To deposit press 2.");
                        System.out.println("To withdraw press 3.");
                        System.out.println("To exit the ATM press 0.");
                        command = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    }else if(commandContinue.equals("n")){
                        flag = false;
                        break;
                    } else {
                        System.out.println("Wrong key pressed!");
                        break;
                    }
                }
                default:
                    System.out.println("Wrong key pressed! (not 0,1,2 or 3");
                    break;
            }
        }
    }
}
