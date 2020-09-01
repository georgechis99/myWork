package com.timbuchalka;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter count:");
        int count = scanner.nextInt();
        scanner.nextLine();

        int[] array = readIntegers(count);
        System.out.println("the initial array is:");
        printArray(array);
        reverse(array);
        System.out.println("The reversed array is:");
        printArray(array);

    }

    public static int[] readIntegers(int count) {
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            System.out.println("Emlement #" + i);
            array[i] = scanner.nextInt();
            scanner.nextLine();
        }
        return array;
    }

    private static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    private static void reverse(int[] array) {
        int temp;
        for (int i = 0; i < array.length / 2; i++) {
            temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
}
