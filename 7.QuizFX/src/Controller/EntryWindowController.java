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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.http.WebSocket;
import java.util.Objects;
import java.util.Optional;

public class EntryWindowController {

    @FXML
    private GridPane entryWindow;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccountButton;

    public void initialize() {

        loginButton.disableProperty().bind(passwordField.textProperty().isEmpty().or(userField.textProperty().isEmpty()));

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = userField.getText();
                String password = passwordField.getText();

                User user = new User(username,password);

                if(UsersPool.getInstance().queryUsersPool(user,1)){
                    //PLAYER FOUND
                    Alert loggedInAlert = new Alert(Alert.AlertType.INFORMATION);
                    loggedInAlert.setTitle("Logging in...");
                    loggedInAlert.setHeaderText("Welcome back, " + username + "!");
                    loggedInAlert.setContentText("Logging in now...");
                    Optional<ButtonType> result = loggedInAlert.showAndWait();
                    Parent root;
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("View/ProfileWindow.fxml")));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root, 600, 400);
                        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("View/MyStyle.css")).toExternalForm());
                        stage.setTitle("My Profile");
                        stage.setScene(scene);
                        stage.setFullScreen(true);
                        stage.show();
                        // Hide this current window (if this is what you want)
                        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //PLAYER NOT FOUND
                    Alert userNotFoundAlert = new Alert(Alert.AlertType.WARNING);
                    userNotFoundAlert.setTitle("User Not Found");
                    userNotFoundAlert.setHeaderText("The username you entered is not valid!");
                    userNotFoundAlert.setContentText("Please try again!");
                    Optional<ButtonType> result = userNotFoundAlert.showAndWait();
                    if (result.isPresent() && (result.get() == ButtonType.OK)) {
                        userField.clear();
                        passwordField.clear();
                    }
                }
            }
        });

        createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createAccountDialog();
            }
        });
    }

    //loading and assigning the dialog to Create a new Account
    @FXML
    public void createAccountDialog() {
        //this opens up the new dialog to add new To-do items when the File -> New... option is selected
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(entryWindow.getScene().getWindow());
        dialog.setTitle("Create a New Account");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("View/CreateAccountWindow.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        CreateAccountWindowController controller = fxmlLoader.getController();
        //.processResults() method is defined in the DialogController class
//            ToDoItem newItem = controller.processResults();
//            toDoListView.getSelectionModel().select(newItem);
    }
}
