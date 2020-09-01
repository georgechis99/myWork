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

public class Branch {

    private String name;
    private ArrayList<Customer> customers;

    public Branch(String name) {
        this.name = name;
        this.customers = new ArrayList<Customer>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean newCustomer(String customerName, double initialAmmount){
        if(findCustomer(customerName) == null){
            this.customers.add(new Customer(customerName,initialAmmount));
            return true;
        }

        return false;
    }

    public boolean addCustomerTransaction(String customerName, double ammount){
        Customer existingCustomer = findCustomer(customerName);

        if(existingCustomer != null){
            existingCustomer.addTransaction(ammount);
            return true;
        }

        return false;
    }

    private Customer findCustomer(String customerName){
        for(int i=0;i<customers.size();i++){
            Customer checkedCustomer = this.customers.get(i);
            if(checkedCustomer.getName().equals(customerName)){
                return checkedCustomer;
            }
        }

        return null;
    }

//    public void printCustomers() {
//        for (int i = 0; i < customers.size(); i++){
//            System.out.print(customers.get(i) + " with initial transactions ");
//            customers.get(i).printTransactions();
//        }
//    }

}
