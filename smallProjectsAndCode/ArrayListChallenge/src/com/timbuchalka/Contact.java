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

public class Contact {

    private String name;
    private String phoneNumber;

    private Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    String getName() {
        return name;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    static Contact createContact(String name, String phoneNumber){
        return new Contact(name,phoneNumber);
    }


}
