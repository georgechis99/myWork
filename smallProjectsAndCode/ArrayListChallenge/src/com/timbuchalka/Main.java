// // Create a program that implements a simple mobile phone with the following capabilities.
//        // Able to store, modify, remove and query contact names.
//        // You will want to create a separate class for Contacts (name and phone number).
//        // Create a master class (MobilePhone) that holds the ArrayList of Contacts
//        // The MobilePhone class has the functionality listed above.
//        // Add a menu of options that are available.
//        // Options:  Quit, print list of contacts, add new contact, update existing contact, remove contact
//        // and search/find contact.
//        // When adding or updating be sure to check if the contact already exists (use name)
//        // Be sure not to expose the inner workings of the Arraylist to MobilePhone
//        // e.g. no ints, no .get(i) etc
//        // MobilePhone should do everything with Contact objects only.

package com.timbuchalka;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("0756891494");

    public static void main(String[] args) {

        boolean quit = false;
        startPhone();
        printActions();
        while(!quit){
            System.out.println("Enter action: (press 6 to show available actions)");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action){
                case 0:
                    System.out.println("Shutting down...");
                    quit = true;
                    break;
                case 1:
                    printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printActions();
                    break;
            }
        }
    }

    private static void addNewContact(){
        System.out.println("Enter new contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        Contact newContact = Contact.createContact(name,phone);
        if(mobilePhone.addNewContact(newContact)){
            System.out.println("New contact added: " + name + ", phone: " + phone);
        }else{
            System.out.println("Cannot add " + name + ". Already on file.");
        }
    }

    private static void updateContact(){
        System.out.println("Enter old contact name: ");
        String oldName = scanner.nextLine();
        Contact oldContact = mobilePhone.queryContact(oldName);
        if(oldContact == null){
            System.out.println("Contact not found.");
            return;
        }

        System.out.println("Enter new contact name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter phone: ");
        String phone = scanner.nextLine();

        Contact newContact = Contact.createContact(newName,phone);
        if(mobilePhone.updateContact(oldContact,newContact)){
            System.out.println("Successfully updated record.");
        }else{
            System.out.println("Failed updated record.");
        }
    }

    private static void removeContact() {
        System.out.println("Enter existing contact name: ");
        String oldName = scanner.nextLine();
        Contact oldContact = mobilePhone.queryContact(oldName);
        if (oldContact == null) {
            System.out.println("Contact not found.");
            return;
        }

        if(mobilePhone.removeContact(oldContact))
            System.out.println("Contact deleted successfully.");
        else
            System.out.println("Error deleting contact.");
    }

    private static void queryContact() {
        System.out.println("Enter existing contact name: ");
        String oldName = scanner.nextLine();
        Contact oldContact = mobilePhone.queryContact(oldName);
        if (oldContact == null) {
            System.out.println("Contact not found.");
            return;
        }

        System.out.println("Name : " + oldContact.getName() + " phone number is " + oldContact.getPhoneNumber());
    }


    private static void printContacts(){
        mobilePhone.printContacts();
    }

    private static void startPhone(){
        System.out.println("Starting phone...");
    }

    private static void printActions(){
        System.out.println("Available actions: press: ");
        System.out.println("0 - To shtudown. \n" +
                           "1 - To print contacts. \n" +
                           "2 - To add a new contact. \n" +
                           "3 - To update an existing contact. \n" +
                           "4 - To remove an existing contact. \n" +
                           "5 - Query if an existing contact exists. \n" +
                           "6 - To print a list of available actions.");
        System.out.println("Choose your action: ");
    }
}
