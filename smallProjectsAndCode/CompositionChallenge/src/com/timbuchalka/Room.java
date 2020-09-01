package com.timbuchalka;

import java.util.Scanner;

public class Room {

    private String name;
    private Walls walls;
    private Floor floor;
    private Oven oven;
    private Sofa sofa;
    private Sink sink;

    public Room(String name, Walls walls, Floor floor, Oven oven, Sofa sofa, Sink sink) {
        this.name = name;
        this.walls = walls;
        this.floor = floor;
        this.oven = oven;
        this.sofa = sofa;
        this.sink = sink;
    }

    public String getName() {
        return name;
    }

    public Walls getWalls() {
        return walls;
    }

    public Floor getFloor() {
        return floor;
    }

    public Sofa getSofa() {
        return sofa;
    }

    public void enterRoom(){
        System.out.println("Someone just enetered the room.");
    }

    public void useAppliances(){
        System.out.println("Someone is about to use the oven.");
        System.out.println("Set a timer: ");
        Scanner scanner = new Scanner(System.in);
        int minutes = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Set a temperature: ");
        double temperature = scanner.nextDouble();
        scanner.nextLine();

        oven.startOven(minutes,temperature);
        System.out.println("Some food was cooked , now you should wash the dishes.");
        sink.washDishes();
    }
}
