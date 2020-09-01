package com.timbuchalka;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Player tim = new Player("Tim",10,15);

        System.out.println(tim.toString());
        saveObject(tim);

        tim.setHitPoints(8);
        System.out.println(tim.toString());
        tim.setWeapon("WEAPOOON");
        saveObject(tim);
        loadObject(tim);
        System.out.println(tim);

        System.out.println("==================================");

        ISavable wereWolf = new Monster("Werewolf" , 20,40);
        System.out.println("Strength = " + ((Monster)wereWolf).getStrength());
        System.out.println(wereWolf);
        saveObject(wereWolf);

        loadObject(wereWolf);

    }

    public static ArrayList<String> readValues() {
        ArrayList<String> values = new ArrayList<String>();

        System.out.println("Choose\n" +
                "1 to enter a string\n" +
                "0 to quit");

        boolean quit = false;
        int index = 0;
        while (!quit) {
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 0:
                    quit = true;
                    break;
                case 1:
                    System.out.println("Enter a string : ");
                    String input = scanner.nextLine();
                    values.add(index,input);
                    index ++;
                    break;
            }
        }
        return values;
    }

    public static void saveObject(ISavable objectToSave) {
        for (int i = 0; i < objectToSave.write().size(); i++) {
            System.out.println("Saving " + objectToSave.write().get(i) + " to storage device.");
        }
    }

    public static void loadObject(ISavable objectToLoad) {
        ArrayList<String> values = readValues();
        objectToLoad.read(values);
    }
}
