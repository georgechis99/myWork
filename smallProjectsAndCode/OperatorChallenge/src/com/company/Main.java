package com.company;

public class Main {

    public static void main(String[] args) {

        double firstDouble = 20.00d;
        double secondDouble = 80.00d;
        double resultDouble = (firstDouble + secondDouble) * 100.00d;
        System.out.println("resultDouble = " + resultDouble);

        double remainderDouble = resultDouble % 40.00d;
        System.out.println("remainderDouble = " + remainderDouble);

        boolean isRemainderZero = (remainderDouble == 0) ? true : false;

        System.out.println("isRemainderZero = " + isRemainderZero);

        if(!isRemainderZero)
            System.out.println("Got some remainder");
    }
}
