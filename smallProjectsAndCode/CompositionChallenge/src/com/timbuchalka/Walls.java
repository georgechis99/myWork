package com.timbuchalka;

import java.awt.*;

public class Walls {

    private int numberOfWalls;
    private String color;
    private Dimensions dimensions;

    public Walls(int numberOfWalls, String color, Dimensions dimensions) {
        this.numberOfWalls = numberOfWalls;
        this.color = color;
        this.dimensions = dimensions;
    }

    public int getNumberOfWalls() {
        return numberOfWalls;
    }

    public String getColor() {
        return color;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }
}
