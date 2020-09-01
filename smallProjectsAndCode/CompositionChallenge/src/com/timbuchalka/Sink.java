package com.timbuchalka;

public class Sink {

    private String manufacturer;
    private String color;

    public Sink(String manufacturer, String color) {
        this.manufacturer = manufacturer;
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getColor() {
        return color;
    }

    public void washDishes(){
        System.out.println("Someone is using the sink to wash dishes.");
    }
}
