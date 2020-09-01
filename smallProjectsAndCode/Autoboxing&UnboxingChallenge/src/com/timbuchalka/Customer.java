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

import java.util.ArrayList;

public class Customer {

    private String name;
    private ArrayList<Double> transactions;

    public Customer(String name, double initialAmmount) {
        this.name = name;
        this.transactions = new ArrayList<Double>();
        addTransaction(initialAmmount);
    }

    public void addTransaction(double ammount){
        transactions.add(ammount);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getTransactions() {
        return transactions;
    }

//    public void printTransactions() {
//        for (int i = 0; i < transactions.size(); i++){
//            System.out.print(transactions.get(i) + "$; ");
//        }
//    }

}
