package Controllers;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WebViewController {

    @FXML
    private AnchorPane anchorPaneWeb;

    @FXML
    private WebView webView;

    @FXML
    private Button backToAppButton;

    private Parent root;

    public void initialize() {

        if(MainScreenController.goToURL == 1) {
            String myGitHubURL = "https://github.com/georgechis99/myWork/tree/master/6.FaceDetectionJavaFX";
            webView.getEngine().load(myGitHubURL);
        } else if (MainScreenController.goToURL == 2) {
            String findOutMoreURL = "https://docs.opencv.org/3.4/d5/d10/tutorial_js_root.html";
            webView.getEngine().load(findOutMoreURL);
        }
    }

    public void backToApp() {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Views/MainScreen.fxml")));
            Stage stage = (Stage)webView.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
