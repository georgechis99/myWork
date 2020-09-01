package com.timbuchalka;

public class BankAccount {
    private int accountNumber;
    private int balance;
    private String customerName;
    private String email;
    private int phoneNumber;

    public BankAccount(){
        this(0000,0,"Default name","Default email",1234567890);
    }

    public BankAccount(int accountNumber,int balance,String customerName,String email,int phoneNumber){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public BankAccount(String customerName, String email, int phoneNumber) {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //    SETTERS

    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }

//    GETTERS

    public int getAccountNumber(){
        return accountNumber;
    }

    public int getBalance(){
        return balance;
    }

    public String getCustomerName(){
        return customerName;
    }

    public String getEmail(){
        return email;
    }

    public int getPhoneNumber(){
        return phoneNumber;
    }

//    Method to deposit funds

    public void depositFunds(int ammount){
        System.out.println("To your initial balance of " + balance + "$");
        balance += ammount;
        System.out.println(" there have been deposited " + ammount + "$");
    }

//    Method to withdraw funds

    public void withdrawFunds(int ammount){
        if(ammount > balance)
            System.out.println("Insufficient funds...");
        else{
            System.out.println("From your initial balance of " + balance + "$");
            balance -= ammount;
            System.out.println(" there have been withdrawn " + ammount + "$");
        }
    }

//    Method to show current ammount of money

    public void showCurrentAmmount(){
        System.out.println("Your current balance is " + balance + "$");
    }

}
