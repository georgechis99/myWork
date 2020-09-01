package com.timbuchalka;

import org.w3c.dom.ls.LSOutput;

class Car {

    //FIELDS

    private String name;
    private boolean engine;
    private int cylinders;
    private int wheels;
    private int doors;
    private boolean isElectric;
    private double maxSpeed;

    //CONSTRUCTOR

    public Car(String name, int cylinders, int doors, boolean isElectric, double maxSpeed) {
        this.name = name;
        this.engine = true; //default
        this.cylinders = cylinders;
        this.wheels = 4; // default
        this.doors = doors;
        this.isElectric = isElectric;
        this.maxSpeed = maxSpeed;
    }

    //GETTERS

    public String getName() {
        return name;
    }

    public boolean isEngine() {
        return engine;
    }

    public int getCylinders() {
        return cylinders;
    }

    public int getWheels() {
        return wheels;
    }

    public int getDoors() {
        return doors;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    //METHODS

    public void startEngine() {
        System.out.println("Default car engine started.");
    }

    public void accelerate() {
        System.out.println("Default car is accelerating.");
    }

    public void brake() {
        System.out.println("Default car is breaking.");
    }
}

class DaciaLogan extends Car {
    public DaciaLogan() {
        super("Dacia Logan", 4, 4, false, 180.0);
    }

    @Override
    public void startEngine() {
        System.out.println("Dacia Logan's engine started.");
    }

    @Override
    public void accelerate() {
        System.out.println("Dacia Logan is accelerating.");
    }

    @Override
    public void brake() {
        System.out.println("Dacia Logan is breaking.");
    }
}

class Porsche911 extends Car {
    public Porsche911() {
        super("Porsche 911", 8, 4, false, 260.5);
    }

    @Override
    public void startEngine() {
        System.out.println("Porsche 911's engine started.");
    }

    @Override
    public void accelerate() {
        System.out.println("Porsche 911 is accelerating.");
    }

    @Override
    public void brake() {
        System.out.println("Porsche 911 is braking.");
    }
}

class DefaultCar extends Car {
    public DefaultCar() {
        super("Default", 0, 0, false, 0);
    }
}

public class Main {

    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++){
            Car car = randomCar();
            System.out.println("Car #" + i + " : ");
            car.startEngine();
            car.accelerate();
            car.brake();
        }

    }

    public static Car randomCar() {
        int randomNumber = (int) (Math.random() * 3) + 1;
        switch (randomNumber) {
            case 1:
                return new DaciaLogan();
            case 2:
                return new Porsche911();
            case 3:
                return new DefaultCar();
            default:
                return null;
        }
    }
}
