package com.company;

public class Main {

    public static void main(String[] args) {

	int myIntValue = 5 / 3;
	float myFloatValue = 5f / 3f;
	double myDoubleValue = 5.00 / 3.00;

        System.out.println("MyIntValue = " + myIntValue);
        System.out.println("MyFloatValue = " + myFloatValue);
        System.out.println("MyDoubleValue = " + myDoubleValue);

        double pounds = 200d;
        double kilograms = pounds * 0.45359237d;
        System.out.println(kilograms);

    }
}
