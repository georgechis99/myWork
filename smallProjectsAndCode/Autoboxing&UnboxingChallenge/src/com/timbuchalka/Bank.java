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

public class Bank {

    private String name;
    private ArrayList<Branch> branches;

    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<Branch>();
    }

    public boolean addBranch(String branchName) {
        if (findBranch(branchName) == null) {
            this.branches.add(new Branch(branchName));
            return true;
        }

        return false;
    }

    public boolean addCustomer(String branchName, String customerName, double initialAmmount) {
        Branch branch = findBranch(branchName);

        if (branch != null) {
            return branch.newCustomer(customerName, initialAmmount);
        }

        return false;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double ammount) {
        Branch branch = findBranch(branchName);

        if (branch != null) {
            return branch.addCustomerTransaction(customerName, ammount);
        }

        return false;
    }

    private Branch findBranch(String branchName) {
        for (int i = 0; i < this.branches.size(); i++) {
            Branch checkedBranch = this.branches.get(i);

            if (checkedBranch.getName().equals(branchName)) {
                return checkedBranch;
            }
        }

        return null;
    }

    public boolean listCustomers(String branchName, boolean showtransactions) {
        Branch branch = findBranch(branchName);

        if (branch != null) {
            System.out.println("Customer details for branch " + branch.getName() + ": ");

            ArrayList<Customer> branchCustomers = branch.getCustomers();
            for (int i = 0; i < branchCustomers.size(); i++) {
                Customer branchCustomer = branchCustomers.get(i);
                System.out.println("Customer #" + (i + 1) + ": " + branchCustomer.getName());
                if (showtransactions) {
                    System.out.println("Transactions: ");
                    ArrayList<Double> transactions = branchCustomer.getTransactions();
                    for (int j = 0; j < transactions.size(); j++) {
                        System.out.println("[" + (j + 1) + "] Ammount: " + transactions.get(j));
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
