package com.timbuchalka;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] array = getArray(5);
        System.out.println("Bubble sort:");
        sortArray(array);
        printArray(array);
        System.out.println("While+for sort:");
        int[] sorted = sortArray2(array);
        printArray(sorted);

    }

    //bubble sort
    public static void sortArray(int[] array) {
        int aux;
        for (int i = 0; i < array.length - 1; i++)
            for (int j = 0; j < array.length - i - 1; j++)
                if (array[j] < array[j + 1]) {
                    aux = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = aux;
                }
    }

    //while+for sort
    public static int[] sortArray2(int[] array) {
        int[] sortedArray = new int[array.length];
//        for (int i = 0; i < array.length; i++)
//            sortedArray[i] = array[i];
        sortedArray = Arrays.copyOf(array,array.length);
        boolean flag = true;
        int temp;

        while (flag) {
            flag = false;
            for (int i = 0; i < sortedArray.length - 1; i++) {
                if (sortedArray[i] < sortedArray[i + 1]) {
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i+1];
                    sortedArray[i+1] = temp;
                    flag = true;
                }
            }
        }

        return sortedArray;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element #" + i + " : " + array[i]);
        }
    }

    public static int[] getArray(int number) {
        System.out.println("Enter " + number + " integer values:\r");
        int[] values = new int[number];

        for (int i = 0; i < values.length; i++) {
            System.out.println("Element #" + i + " : \r");
            values[i] = scanner.nextInt();
        }
        return values;
    }

}
