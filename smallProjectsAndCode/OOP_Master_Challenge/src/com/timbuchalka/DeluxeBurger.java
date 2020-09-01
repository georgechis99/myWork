package com.timbuchalka;

public class DeluxeBurger extends Hamburger{

    public DeluxeBurger(String burgerName, String breadRollType, String meatType, double price) {
        super(burgerName, breadRollType, meatType, price);
        System.out.println("Deluxe Burger selected.");
    }
}
