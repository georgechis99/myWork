package sample.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class ToDoData {

    //instance of the class itself
    private static ToDoData instance = new ToDoData();
    //the name of the file in which the data will be stored (saveed into / loaded from)
    private static String filename = "ToDoListItems.txt";
    private static String filename2 = "RecentlyDeleted.txt";

    //this list of To-do items
    private ObservableList<ToDoItem> toDoItems;
    private ObservableList<ToDoItem> recentlyDeleted;

    //this formats the date into a specified format (dd-MM-yyyy in this case)
    private DateTimeFormatter formatter;

    //this is specific to singleton classes
    //the class is only instantiated once "through itself"
    //meanning it has a private instance of itself
    public static ToDoData getInstance() {
        return instance;
    }

    //public method to add To-do items to the List
    public void addToDoItem(ToDoItem item) {
        toDoItems.add(item);
    }

    //method to add recently deleted items to the accroding list
    public void addRecentlyDeleted(ToDoItem item) {
        recentlyDeleted.add(item);
    }

    //int the constructor we only set the formatter to the desired format
    private ToDoData() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    //getter for the list
    public ObservableList<ToDoItem> getToDoItems() {
        return toDoItems;
    }

    //getter for the recentlyDeleted
    public ObservableList<ToDoItem> getRecentlyDeleted() {
        return recentlyDeleted;
    }

    //method to load items into the UI from the file (already existing data)
    public void loadToDoItems() throws IOException {

        //this is necessary in the Controller.java
        toDoItems = FXCollections.observableArrayList();
        //Path type variable that stores the path (location) of the file with "filename" name
        Path path = Paths.get(filename);
        //here we instantiate a BufferedReader to be able to read lines from the txt file
        BufferedReader br = Files.newBufferedReader(path);

        //this will be where we store each read line
        String input;

        try {
            while ((input = br.readLine()) != null) {
                //here we store all the lines in a String array (separating them with .split() method
                //splitting them where we meet a tab ("\t")
                String[] itemPieces = input.split("\t");

                //here we store each line with the blueprint of the ToDoItem class (shortDescription, details, dateString)
                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                //here we format the date from String to a LocalDate variable using our already defined formatter
                //using the LocalDate.parse() method which takes the parameters : .parse(String , DateFormatter)
                LocalDate date = LocalDate.parse(dateString, formatter);

                //here we create the new ToDoItem object and add it to the List of items
                ToDoItem toDoItem = new ToDoItem(shortDescription, details, date);
                toDoItems.add(toDoItem);
            }
        } finally {
            if (br != null) {
                //closing the reader
                br.close();
            }
        }
    }

    public void loadRecentlyDeleted() throws IOException {
        recentlyDeleted = FXCollections.observableArrayList();
        Path path = Paths.get(filename2);
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        try {
            while ((input = br.readLine()) != null) {

                String[] itemPieces = input.split("\t");

                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];

                LocalDate date = LocalDate.parse(dateString, formatter);

                ToDoItem toDoItem = new ToDoItem(shortDescription, details, date);
                recentlyDeleted.add(toDoItem);
            }
        } finally {
            if (br != null) {
                //closing the reader
                br.close();
            }
        }
    }

    //method to store the existing data (wether it already existed in the UI when we opened it , or it was added subsequently)
    //in our file
    public void storeToDoItems() throws IOException {

        //Path type variable that stores the path (location) of the file with "filename" name
        Path path = Paths.get(filename);
        //here we instantiate a BuffredWriter variable to be able to write stuff to file
        BufferedWriter bw = Files.newBufferedWriter(path);

        try {

            //we choose to use an Iterator to iterate through the items of the List
            Iterator<ToDoItem> iter = toDoItems.iterator();
            while (iter.hasNext()) {
                ToDoItem item = iter.next();
                //here we write in the desired format items to the file
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    public void storeRecentlyDeleted() throws IOException{

        Path path = Paths.get(filename2);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try{

            Iterator<ToDoItem> iter = recentlyDeleted.iterator();
            while(iter.hasNext()){
                ToDoItem item = iter.next();
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDeadline().format(formatter)));
                bw.newLine();
            }
        }finally {
            if (bw != null) {
                bw.close();
            }
        }
    }

    public void deleteToDoItem(ToDoItem item) {
        toDoItems.remove(item);
        recentlyDeleted.add(item);
    }

}
