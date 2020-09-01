package com.timbuchalka;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        //VALUE TYPES
        int myIntValue = 10;                 //primitive type -> value type
        int anotherIntValue = myIntValue;

        System.out.println("myIntValue = " + myIntValue);            //10
        System.out.println("anotherIntValue = " + anotherIntValue);  //10

        anotherIntValue++;

        System.out.println("myIntValue = " + myIntValue);            //10
        System.out.println("anotherIntValue = " + anotherIntValue);  //11

        myIntValue = 5;

        System.out.println("myIntValue = " + myIntValue);            //5
        System.out.println("anotherIntValue = " + anotherIntValue);  //11

        //REFERENCE TYPES
        int[] myIntArray = new int[5];    //reference pointing to an object in memory
        int[] anotherArray = myIntArray;  //pointing to tha same object as above

        //these 2 arrays hold the same adress -> pointing to the same array in memory
        //any change to either of the references modifies the value to which they point

        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));

        anotherArray[0] = 1; //makes changes for both references!!!

        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));

        modify(myIntArray); // modifies both references (actually it modifies the actual
        // value they point to in the memory)

        System.out.println("myIntArray = " + Arrays.toString(myIntArray));
        System.out.println("anotherArray = " + Arrays.toString(anotherArray));
    }

    private static void modify(int[] array) {
        array[0] = 2;
    }
}
