package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        VipCustomer customer1 = new VipCustomer();
        VipCustomer customer2 = new VipCustomer("George",500);
        VipCustomer customer3 = new VipCustomer("Tim",1000,"timbuchalka@email.com");

        System.out.println("First customer:");
        System.out.println(customer1.getName());
        System.out.println(customer1.getEmailAdress());
        System.out.println(customer1.getCreditLimit());

        System.out.println("Second customer:");
        System.out.println(customer2.getName());
        System.out.println(customer2.getEmailAdress());
        System.out.println(customer2.getCreditLimit());

        System.out.println("Third customer:");
        System.out.println(customer3.getName());
        System.out.println(customer3.getEmailAdress());
        System.out.println(customer3.getCreditLimit());
    }
}
