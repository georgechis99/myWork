package com.timbuchalka;

public class George {

    private String first_name;
    private int brain;
    private String eyes;
    private double height;
    private double weight;
    private double average_grade;

    public George(String first_name, int brain, String eyes, double height, double weight){

    }

    public George(String first_name, int brain, String eyes, double height, double weight, double average_grade) {
        this.first_name = first_name;
        this.brain = brain;
        this.eyes = eyes;
        this.height = height;
        this.weight = weight;
        this.average_grade = average_grade;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getBrain() {
        return brain;
    }

    public void setBrain(int brain) {
        this.brain = brain;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAverage_grade() {
        return average_grade;
    }

    public void setAverage_grade(double average_grade) {
        this.average_grade = average_grade;
    }
}
