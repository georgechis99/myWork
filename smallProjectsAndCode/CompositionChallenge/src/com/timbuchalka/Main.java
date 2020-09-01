package com.timbuchalka;

public class Main {

    public static void main(String[] args) {

        Dimensions dimensionOfWalls = new Dimensions(3,5);
        Walls walls = new Walls(4,"white",dimensionOfWalls);

        Dimensions dimensionOfFloor = new Dimensions(5,5);
        Floor floor = new Floor("grey-ish","wood",dimensionOfFloor);

        Oven oven = new Oven("Bosch",225.5);

        Sink sink = new Sink("Miele","metallic");

        Sofa sofa = new Sofa("Ikea","Brown");

        Room room = new Room("Kitchen",walls,floor,oven,sofa,sink);
        room.enterRoom();
        room.useAppliances();
    }
}
