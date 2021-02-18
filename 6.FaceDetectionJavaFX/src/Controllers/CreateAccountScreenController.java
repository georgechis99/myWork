package Controllers;

import Models.User;
import Models.UsersPool;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class CreateAccountScreenController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button readyButton;

    @FXML
    private Label userLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label phoneNumberLabel;

    private static double xOffset = 0;
    private static double yOffset = 0;

    public void initialize() {

        makeWindowDraggable(usernameField);
        //to check if the username has AT LEAST 6 CHARACTERS
        BooleanBinding userValid = Bindings.createBooleanBinding(() -> {
            boolean charNumberFlag = false; // checks the number of characters in the username
            String username = usernameField.getText();
            if (username.length() >= 6) {
                charNumberFlag = true; //true if char number >= 6
            }
            return charNumberFlag;
        }, usernameField.textProperty());

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
            for (int i = 0; i < password.length(); i++) {
                ch = password.charAt(i);
                if (Character.isUpperCase(ch)) {
                    uppercaseFlag = true;
                } else if (Character.isDigit(ch)) {
                    digitFlag = true;
                }
            }
            return charNumberFlag && uppercaseFlag && digitFlag;
        }, passwordField.textProperty());

        //to check if the phonenumber has the right format
        BooleanBinding phoneNumberValid = Bindings.createBooleanBinding(() -> {
            boolean charNumberFlag = false;
            boolean formatFlag = true;
            String phoneNumber = phoneNumberField.getText();
            if (phoneNumber.length() == 12) {
                charNumberFlag = true;
                if (phoneNumber.length() > 0) {
                    char firstChar = phoneNumber.charAt(0);
                    if (!((Character) firstChar).equals('+')) {
                        formatFlag = false;
                    }
                    for (int i = 1; i < phoneNumber.length(); i++) {
                        if (!Character.isDigit(phoneNumber.charAt(i))) {
                            formatFlag = false;
                        }
                    }
                }
            }
            return formatFlag && charNumberFlag;
        }, phoneNumberField.textProperty());

        readyButton.disableProperty().bind(userValid.not().or(passwordValid.not()).or(phoneNumberValid.not()));
        userLabel.visibleProperty().bind(userValid.not());
        passwordLabel.visibleProperty().bind(passwordValid.not());
        phoneNumberLabel.visibleProperty().bind(phoneNumberValid.not());

        readyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String phoneNumber = phoneNumberField.getText();

                User newUser = new User(username, password, phoneNumber);
                if (!UsersPool.getInstance().queryUsersPool(newUser)) {
                    //ACCOUNT SUCCESSFULLY CREATED
                    UsersPool.getInstance().addUser(newUser);
                    Parent root;
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/MainScreen.fxml")));
                        Stage stage = (Stage) readyButton.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
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
                        usernameField.clear();
                        passwordField.clear();
                    }
                }
            }
        });
    }

    public void backToSplashScreen(){
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/SplashScreen.fxml")));
            Stage stage = (Stage) readyButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeWindowDraggable(Region pane){
        //to make the window draggable (it is defined with no decorations)
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) pane.getScene().getWindow();
                xOffset = stage.getX() - event.getScreenX();
                yOffset = stage.getY() - event.getScreenY();
            }
        });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = (Stage) pane.getScene().getWindow();
                stage.setX(event.getScreenX() + xOffset);
                stage.setY(event.getScreenY() + yOffset);
            }
        });
    }
}
