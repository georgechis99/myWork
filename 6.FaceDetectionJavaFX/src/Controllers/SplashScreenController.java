package Controllers;

import Models.User;
import Models.UsersPool;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;


public class SplashScreenController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button exitButton;

    private Parent root;

    private static double xOffset = 0;
    private static double yOffset = 0;

    public void initialize(){

        //to make the window draggable (it is defined with no decorations)
        makeWindowDraggable(anchorPane);

        //to login once you already have an account
        loginButton.disableProperty().bind(passwordField.textProperty().isEmpty().or(usernameField.textProperty().isEmpty()));

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = usernameField.getText();
                String password = passwordField.getText();

                User user = new User(username,password);

                if(UsersPool.getInstance().queryUsersPool(user,1)){
                    //PLAYER FOUND
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/MainScreen.fxml")));
                        Stage stage = (Stage)loginButton.getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
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
                    userNotFoundAlert.initStyle(StageStyle.UNDECORATED);
                    Optional<ButtonType> result = userNotFoundAlert.showAndWait();
                    if (result.isPresent() && (result.get() == ButtonType.OK)) {
                        usernameField.clear();
                        passwordField.clear();
                    }
                }
            }
        });
    }

    //to close the window when pressing the "X" button
    public void handleCloseButton(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    //to open the CreateAccountScreen on "Register Now" link pressed
    public void openCreateAccountScreen(){
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/CreateAccountScreen.fxml")));
            Stage stage = (Stage)loginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
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
