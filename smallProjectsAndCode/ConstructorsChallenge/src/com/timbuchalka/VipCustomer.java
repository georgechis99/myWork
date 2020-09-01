package com.timbuchalka;

public class VipCustomer {
    private String name;
    private double creditLimit;
    private String emailAdress;

    public VipCustomer(){
        this("Default name",0.00,"Default email");
        System.out.println("Empty constructor called with default values");
    }

    public VipCustomer(String name,double creditLimit){
        this(name,creditLimit,"Default email adress");
        System.out.println("Constructor with 2 parameters called");
    }

    public VipCustomer(String name, double creditLimit, String emailAdress) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.emailAdress = emailAdress;
        System.out.println("Constructor with 3 parameters called");
    }

    public String getName() {
        return name;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAdress() {
        return emailAdress;
    }
}
