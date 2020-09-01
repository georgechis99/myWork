package com.timbuchalka;

public class HealthyBurger extends Hamburger {

    public HealthyBurger(String burgerName, String breadRollType, String meatType, double price) {
        super(burgerName, breadRollType, meatType, price);
        System.out.println("Healthy Burger selected.");
    }

    public void addAvocado(){
        increasePrice(3);
    }

    public void addRucola(){
        increasePrice(3);
    }
}
