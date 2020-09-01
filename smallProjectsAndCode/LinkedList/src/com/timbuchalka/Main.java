package com.timbuchalka;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer("Tim", 54.96);
        Customer anotherCustomer;
        anotherCustomer = customer;
        anotherCustomer.setBalance(12.199); // this actually also updates the value of
        //the first object because the second object is in fact a reference
        //to the first object so they both point to the same
        //adress in memory

        System.out.println("Balance for customer " + customer.getName()
                           + " is " + customer.getBalance());

        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(1);
        intList.add(3);
        intList.add(4);

        for(int i=0;i<intList.size();i++){
            System.out.println(i + ": " + intList.get(i));
        }

        System.out.println();
        intList.add(1, 2);

        for(int i=0;i<intList.size();i++){
            System.out.println(i + ": " + intList.get(i));
        }

        System.out.println();
        intList.remove(1);

        for(int i=0;i<intList.size();i++){
            System.out.println(i + ": " + intList.get(i));
        }


    }
}
