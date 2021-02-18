package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UsersPool {

    private static UsersPool instance = null;
    private List<User> users = new ArrayList<>();

    public static UsersPool getInstance() {
        if (instance == null) {
            instance = new UsersPool();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void writeToFile() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt"))) {
            for (User user : users) {
                bw.write(user.getUsername());
                bw.newLine();
                bw.write(user.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Exception at writing data to file");
        }

    }

    public void readFromFile() {

        try(BufferedReader br = new BufferedReader(new FileReader("users.txt"))){
            String username;
            String password;
            while((username = br.readLine()) != null){
                password = br.readLine();
                User user = new User(username,password);
                UsersPool.getInstance().addUser(user);
            }
        }catch (IOException e){
            System.out.println("Exception at reading data from file");
        }
    }

    //method that returns true if the user already exists and false otherwise
    public boolean queryUsersPool(User theUser){
        boolean userAlreadyExistsFlag = false;
        for(User user : users){
            if(theUser.getUsername().equals(user.getUsername())){
                userAlreadyExistsFlag = true;
            }
        }
        return userAlreadyExistsFlag;
    }

    //overloaded method for login
    public boolean queryUsersPool(User theUser,int overload){
        boolean userExistent = false;
        for(User user : users){
            if(theUser.getUsername().equals(user.getUsername()) && theUser.getPassword().equals(user.getPassword())){
                userExistent = true;
            }
        }
        return userExistent;
    }
}
