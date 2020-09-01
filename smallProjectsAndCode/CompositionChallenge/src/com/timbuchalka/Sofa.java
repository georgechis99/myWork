package com.timbuchalka;

public class Sofa {

    private String manufacturer;
    private String color;

    public Sofa(String manufacturer, String color) {
        this.manufacturer = manufacturer;
        this.color = color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getColor() {
        return color;
    }

    public void sitOnSofa(){
        System.out.println("Someone is sitting on the sofa.");
    }

    public void openSofa(){
        System.out.println("Sofa was just extended. Now you can lay on it like on a bed.");
    }
}
