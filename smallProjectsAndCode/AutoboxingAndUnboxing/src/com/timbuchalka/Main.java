package com.timbuchalka;

import java.util.ArrayList;

class IntClass {
    private int myValue;

    public IntClass(int myValue) {
        this.myValue = myValue;
    }

    public void setMyValue(int myValue) {
        this.myValue = myValue;
    }

    public int getMyValue() {
        return myValue;
    }
}

public class Main {

    public static void main(String[] args) {
        String[] stringArray = new String[10];
        int[] intArray = new int[10];

        ArrayList<String> strArrayList = new ArrayList<String>();
        strArrayList.add("Tim");

        // ArrayList<int> intArratList = new ArrayList<int>();
        //ERROR
        ArrayList<IntClass> intClassArrayList = new ArrayList<IntClass>();
        intClassArrayList.add(new IntClass(54));

        Integer integer = new Integer(54);
        Double doubleValue = new Double(15.25);
        /////////////////////////////////////////////////////////////////////////
        ArrayList<Integer> intArrayList = new ArrayList<Integer>();
        for (int i = 0; i <= 10; i++) {
            intArrayList.add(i);
        }

        for (int i = 0; i <= 10; i++) {
            System.out.println(i + " ---> " + intArrayList.get(i).intValue());
        }

        Integer myIntValue = 56; //Integer.valueOf(56);
        int myInt = myIntValue; //myIntValue.intValue();

        ArrayList<Double> myDoubleArrayList = new ArrayList<Double>();
        for (double dbl = 0.0; dbl <= 10.0; dbl += 0.5) {
            myDoubleArrayList.add(Double.valueOf(dbl));
        }

        for (int i = 0; i < myDoubleArrayList.size(); i++){
            double value = myDoubleArrayList.get(i).doubleValue();
            System.out.println(i + " ---> " + value);
        }
    }
}
