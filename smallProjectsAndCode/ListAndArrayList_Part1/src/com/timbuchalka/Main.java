package com.timbuchalka;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static GroceryList groceryList = new GroceryList();

    public static void main(String[] args) {

        boolean quit = false;
        int choice = 0;
        printInstructions();
        while (!quit) {
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    groceryList.printGroceryList();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    modifyItem();
                    break;
                case 4:
                    removeItem();
                    break;
                case 5:
                    searchForItem();
                    break;
                case 6:
                    quit = true;
                    break;
            }
        }

    }

    public static void printInstructions() {
        System.out.println("Press:");
        System.out.println("0 - To print choice oprions.");
        System.out.println("1 - To print the grocery list.");
        System.out.println("2 - To add an item to the list.");
        System.out.println("3 - To modify an item from the list.");
        System.out.println("4 - To remove an item from the list.");
        System.out.println("5 - To search for an item in the list.");
        System.out.println("6 - To quit the application.");
    }

    public static void addItem() {
        System.out.println("Enter the name of the item you want to add");
        String newItem = scanner.next();
        groceryList.addGroceryItems(newItem);
    }

    public static void modifyItem() {
        System.out.println("Enter the index:");
        int index = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the name of the item:");
        String newItem = scanner.next();
        groceryList.modifyGroceryItem(index - 1, newItem);
    }

    public static void removeItem() {
        System.out.println("Enter the index:");
        int index = scanner.nextInt();
        scanner.nextLine();
        groceryList.removeGroceryItem(index - 1);
    }

    public static void searchForItem(){
        System.out.println("Item to search for:");
        String searchItem = scanner.next();
        if(groceryList.findItem(searchItem) != null)
            System.out.println("Found " + searchItem + " in the grocery list.");
        else
            System.out.println(searchItem + " is not in the grocery list");
    }
}
