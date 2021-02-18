package Controller;

import Model.User;
import Model.UsersPool;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class CreateAccountWindowController {

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button createAccountButton;

    @FXML
    private Label userLabel;

    @FXML
    private Label passwordLabel;

    public void initialize() {

        //to check if the username has AT LEAST 6 CHARACTERS
        BooleanBinding userValid = Bindings.createBooleanBinding(() -> {
            boolean charNumberFlag = false; // checks the number of characters in the username
            String username = userField.getText();
            if (username.length() >= 6) {
                charNumberFlag = true; //true if char number >= 6
            }
            return charNumberFlag;
        }, userField.textProperty());

        //to check if the password has at least ONE CAPITAL, 6 CHARACTERS and ONE DIGIT
        BooleanBinding passwordValid = Bindings.createBooleanBinding(() -> {
            boolean charNumberFlag = false;
            boolean uppercaseFlag = false;
            boolean digitFlag = false;
            char ch; // to iterate through each char
            String password = passwordField.getText();
            if (password.length() >= 6) {
                charNumberFlag = true;
            }
            for (int i = 0; i < password.length(); i++){
                ch = password.charAt(i);
                if(Character.isUpperCase(ch)){
                    uppercaseFlag = true;
                } else if(Character.isDigit(ch)){
                    digitFlag = true;
                }
            }
            return charNumberFlag && uppercaseFlag && digitFlag;
        }, passwordField.textProperty());

        createAccountButton.disableProperty().bind(userValid.not().or(passwordValid.not()));
        userLabel.visibleProperty().bind(userField.textProperty().isNotEmpty().and(userValid.not()));
        passwordLabel.visibleProperty().bind(passwordField.textProperty().isNotEmpty().and(passwordValid.not()));

        createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = userField.getText();
                String password = passwordField.getText();

                User newUser = new User(username,password);
                if(!UsersPool.getInstance().queryUsersPool(newUser)){
                    //ACCOUNT SUCCESSFULLY CREATED
                    UsersPool.getInstance().addUser(newUser);
                    Alert accountCreatedAlert = new Alert(Alert.AlertType.INFORMATION);
                    accountCreatedAlert.setTitle("Account Created");
                    accountCreatedAlert.setHeaderText("Welcome, " + username + "!");
                    accountCreatedAlert.setContentText("You are now ready to prove your knowledge! Proceed?");
                    Optional<ButtonType> result = accountCreatedAlert.showAndWait();
                    Parent root;
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/ProfileWindow.fxml")));
                        Stage stage = new Stage();
                        stage.setTitle("My Profile");
                        stage.setScene(new Scene(root, 450, 450));
                        stage.setFullScreen(true);
                        stage.show();
                        // Hide this current window (if this is what you want)
                        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //ACCOUNT NOT SUCCESSFULLY CREATED
                    Alert userAlreadyExistsAlert = new Alert(Alert.AlertType.WARNING);
                    userAlreadyExistsAlert.setTitle("User Already Exists");
                    userAlreadyExistsAlert.setHeaderText("The username you entered is already taken!");
                    userAlreadyExistsAlert.setContentText("Please try again with another username!");
                    Optional<ButtonType> result = userAlreadyExistsAlert.showAndWait();
                    if (result.isPresent() && (result.get() == ButtonType.OK)) {
                        userField.clear();
                        passwordField.clear();
                    }
                }
            }
        });
    }
}
