package com.timbuchalka;

public class Oven {

    private String manufacturer;
    private double electricalPower;

    public Oven(String manufacturer, double electricalPower) {
        this.manufacturer = manufacturer;
        this.electricalPower = electricalPower;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getElectricalPower() {
        return electricalPower;
    }

    public void startOven(int timeInMinutes,double temperatureInCelsius){
        System.out.println("Oven powered on. Set for " + timeInMinutes + " minutes , at " + temperatureInCelsius + "C (Celsius)");
    }
}
