package com.timbuchalka;

public class Hamburger {

    private String burgerName;
    private String breadRollType;
    private String meatType;
    private double price = 10;

    public Hamburger(String burgerName, String breadRollType, String meatType, double price) {
        this.burgerName = burgerName;
        this.breadRollType = breadRollType;
        this.meatType = meatType;
        this.price = price;
        System.out.println("Basic Hamburger selected.");
    }

    public double getPrice(){
        return price;
    }

    public void increasePrice(int value){
        price += value;
    }

    public void showPrice() {
        System.out.println("Your total burger price is " + price + "$. Enjoy your meal!");
    }

    public void addLettuce() {
        price += 2;
    }

    public void addTomato() {
        price += 2;
    }

    public void addCarrot() {
        price += 2;
    }
}
