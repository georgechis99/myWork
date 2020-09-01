package com.timbuchalka;

public class Car {

    private int doors;
    private int wheels;
    private String model;
    private String engine;
    private String colour;

    public void setModel(String model) {
        String validModel = model.toLowerCase();
        if (validModel.equals("carrera") || validModel.equals("duster"))
            this.model = model;
        else {
            this.model = "Unknown";
        }
    }
    public String getModel(){
        return this.model;
    }
}
