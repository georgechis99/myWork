//	    // You job is to create a simple banking application.
//        // There should be a Bank class
//        // It should have an arraylist of Branches
//        // Each Branch should have an arraylist of Customers
//        // The Customer class should have an arraylist of Doubles (transactions)
//        // Customer:
//        // Name, and the ArrayList of doubles.
//        // Branch:
//        // Need to be able to add a new customer and initial transaction amount.
//        // Also needs to add additional transactions for that customer/branch
//        // Bank:
//        // Add a new branch
//        // Add a customer to that branch with initial transaction
//        // Add a transaction for an existing customer for that branch
//        // Show a list of customers for a particular branch and optionally a list
//        // of their transactions
//        // Demonstration autoboxing and unboxing in your code
//        // Hint: Transactions
//        // Add data validation.
//        // e.g. check if exists, or does not exist, etc.
//        // Think about where you are adding the code to perform certain actions

package com.timbuchalka;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Bank bank = new Bank("BancaTransilvania");

        bank.addBranch("Cluj-Napoca");
        bank.addCustomer("Cluj-Napoca","George",50.25);
        bank.addCustomer("Cluj-Napoca","Mike",100);
        bank.addCustomer("Cluj-Napoca","Tim",220.12);

        bank.addBranch("Bucuresti");
        bank.addCustomer("Bucuresti","Bob",70.25);

        bank.addCustomerTransaction("Cluj-Napoca","George",44.22);
        bank.addCustomerTransaction("Cluj-Napoca","Mike",2500);
        bank.addCustomerTransaction("Cluj-Napoca","Tim",0.15);

        bank.listCustomers("Cluj-Napoca",true);
        bank.listCustomers("Bucuresti",true);

        bank.addBranch("Sibiu");

        if(!bank.addCustomer("Sibiu","Brian",6.23)){
            System.out.println("Error , branch Sibiu does not exist!");
        }

        if(!bank.addBranch("Cluj-Napoca")){
            System.out.println("Error , branch Cluj-Napoca already exists.");
        }
    }
}
