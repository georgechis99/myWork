package com.timbuchalka;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("How many integers do you want to read?");
        int count = scanner.nextInt();
        scanner.nextLine();
        int[] array = readIntegers(count);
        System.out.println("The minimum value in the array is " + findMin(array));
    }

    public static int[] readIntegers(int count) {
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            System.out.println("Element #" + i + ":");
            array[i] = scanner.nextInt();
            scanner.nextLine();
        }
        return array;
    }

    public static int findMin(int[] array) {
        int minimumValue = array[0];
        for (int i = 1; i < array.length; i++){
            if(array[i] < minimumValue){
                minimumValue = array[i];
            }
        }
        return minimumValue;
    }

}
