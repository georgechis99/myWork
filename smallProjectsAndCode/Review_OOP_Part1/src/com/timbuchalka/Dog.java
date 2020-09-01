package com.timbuchalka;

public class Dog extends Animal {

    private int eyes;
    private int legs;
    private int tail;
    private int teeth;
    private String coat;

    public Dog(String name, int size, int weight, int eyes, int legs, int tail, int teeth, String coat) {
        super(name, 1, 1, size, weight);
        this.eyes = eyes;
        this.legs = legs;
        this.tail = tail;
        this.teeth = teeth;
        this.coat = coat;
    }

    ///////////////////////////////////////////////////////
    private void chew() {
        System.out.println("Dog.chew()");
    }

    ////////////////////////////////////////////////////////
    public void walk() {
        System.out.println("Dog.walk()");
        super.move(5);
    }

    public void run() {
        System.out.println("Dog.run()");
        move(10);
    }

    ////////////////////////////////////////////////////////
    @Override
    public void eat() {
        System.out.println("Dog.eat()");
        chew();
        super.eat();
    }
    ///////////////////////////////////////////////////////////

    @Override
    public void move(int speed) {
        System.out.println("Dog.move()");
        super.move(speed);
    }
}
