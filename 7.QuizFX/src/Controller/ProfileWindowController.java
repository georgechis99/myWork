package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;

public class ProfileWindowController {

    private static ProfileWindowController instance = null;

    @FXML
    private BorderPane rootPane = new BorderPane();

    @FXML
    private ImageView imageView;

    @FXML
    private Button addImage;


    public static ProfileWindowController getInstance() {
        if (instance == null) {
            instance = new ProfileWindowController();
        }
        return instance;
    }

    public Pane getRootPane() {
        return rootPane;
    }

    public void initialize() {

        final FileChooser fileChooser = new FileChooser();
//        Image defaultProfileImage = new Image(getClass().getResourceAsStream("/Resources/defaultProfileImage.png"));
//        imageView.setImage(defaultProfileImage);

        addImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser chooser = new FileChooser();
                chooser.setTitle("Open File");
                chooser.setInitialDirectory(new File(System.getProperty("user.home")));
                chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif"));
                File file = chooser.showOpenDialog(new Stage());
                if(file != null) {
                    String imagepath = null;
                    try {
                        imagepath = file.toURI().toURL().toString();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("file:" + imagepath);
                    assert imagepath != null;
                    Image image = new Image(imagepath);

                    System.out.println("height:"+image.getHeight()+"\nWidth:"+image.getWidth());
                    imageView.setImage(image);
                    imageView.setFitWidth(200);
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);
                    imageView.setCache(true);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Please Select a File");
                    /*alert.setContentText("You didn't select a file!");*/
                    alert.showAndWait();
                }
            }
        });
    }
}
