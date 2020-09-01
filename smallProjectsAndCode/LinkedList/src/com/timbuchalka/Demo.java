package com.timbuchalka;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {

        LinkedList<String> placesToVisit = new LinkedList<String>();

        addInOrder(placesToVisit, "Sydney");
        addInOrder(placesToVisit, "Melbourne");
        addInOrder(placesToVisit, "Brisbane");
        addInOrder(placesToVisit, "Perth");
        addInOrder(placesToVisit, "Canberra");
        addInOrder(placesToVisit, "Adelaide");
        addInOrder(placesToVisit, "Darwin");
        printList(placesToVisit);

        addInOrder(placesToVisit, "Alice Springs");
        addInOrder(placesToVisit, "Darwin");
        printList(placesToVisit);

        visit(placesToVisit);

    }

    private static void printList(LinkedList<String> linkedList) {
        Iterator<String> i = linkedList.iterator();
        while (i.hasNext()) {
            System.out.println("Now visiting " + i.next());
        }
        System.out.println("====================");
    }

    private static boolean addInOrder(LinkedList<String> linkedList, String newCity) {
        ListIterator<String> i = linkedList.listIterator();

        while (i.hasNext()) {
            int comparison = i.next().compareTo(newCity);
            if (comparison == 0) {
                System.out.println(newCity + " is already included as a destination.");
                return false;
            } else if (comparison > 0) {
                i.previous();
                i.add(newCity);
                return true;
            } else if (comparison < 0) {
                //move on to next city
            }
        }

        i.add(newCity);
        return true;
    }

    private static void visit(LinkedList<String> cities) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean goingForward = true;
        ListIterator<String> i = cities.listIterator();

        if (cities.isEmpty()) {
            System.out.println("No cities in the itinerery!");
            return;
        } else {
            System.out.println("Now visiting " + i.next());
            printMenu();
        }

        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Holiday over!");
                    quit = true;
                    break;
                case 1:
                    if(!goingForward){
                        if(i.hasNext()){
                            i.next();
                        }
                        goingForward = true;
                    }
                    if(i.hasNext()) {
                        System.out.println("Now visiting " + i.next());
                    }else{
                        System.out.println("Reached the end of the list.");
                        goingForward = false;
                    }
                    break;
                case 2:
                    if(goingForward){
                        if(i.hasPrevious()){
                            i.previous();
                        }
                        goingForward = false;
                    }
                    if(i.hasPrevious()){
                        System.out.println("Now visiting " + i.previous());
                    }else{
                        System.out.println("We are at the start of the list.");
                        goingForward = true;
                    }
                    break;
                case 3:
                    printMenu();
                    break;
            }
        }
    }

    public static void printMenu(){

        System.out.println("Available actions: \npress ");
        System.out.println("0 - to quit");
        System.out.println("1 - go to next city");
        System.out.println("2 - go to previous city");
        System.out.println("3 - print menu options");
    }
}
