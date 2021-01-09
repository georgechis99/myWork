package Controllers;

import OpenCV.FaceDetection;
import OpenCV.WebcamFaceDetection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.sql.SQLOutput;

public class MainScreenController {

    private FaceDetection faceDetection;
    private WebcamFaceDetection webcamFaceDetection;
    private BufferedImage image; //webcam
    private Image imageFX;
    private FileChooser fileChooser;
    private File file;
    private Thread webcamThread;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView imageView;

    @FXML
    private Button detectButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ChoiceBox haarChoiceBox;

    @FXML
    private Label progressBarLabel;

    @FXML
    private MenuBar menuBar;

    public void initialize() {

        //to set the choicebox and give functionality to it (so you can change the Haar xml file used in detection)
        String[] xmlFiles = {"haarcascade_frontalface_default.xml",
                "haarcascade_frontalface_alt.xml", "haarcascade_frontalface_alt2.xml", "haarcascade_frontalface_alt_tree.xml"};
        haarChoiceBox.setItems(FXCollections.observableArrayList(xmlFiles));

        //to change the Haar xml file used in detection from the filechooser
        haarChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                String pathPrefix = "C:\\Users\\ACER DEMO\\Desktop\\Faculta\\Java\\GitHub Respository\\myWork\\6.FaceDetectionJavaFX\\haar_cascades\\";
                faceDetection.setCascadeClassifier(pathPrefix + xmlFiles[newValue.intValue()]);
                webcamFaceDetection.setCascadeClassifier(pathPrefix + xmlFiles[newValue.intValue()]);
                progressBarLabel.setVisible(false);
            }
        });

        //initializing the Face Detection component
        faceDetection = new FaceDetection();

        //to configure the filechooser to only accept jpg and png files
        fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.setInitialDirectory(new File("C:\\Users\\ACER DEMO\\Desktop\\Faculta\\Java\\GitHub Respository\\myWork\\6.FaceDetectionJavaFX\\src\\Resources"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        //to make the window draggable (it is undecorated)
        SplashScreenController.makeWindowDraggable(anchorPane);
        SplashScreenController.makeWindowDraggable(menuBar);

        detectButton.setDisable(true);
    }

    //to close the window when pressing the "Close" MenuItem
    public void handleCloseButton() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    public void loadImage() {

        webcamThread.stop();

        progressBarLabel.setVisible(false);
        //the file image that will be loaded into the app to be scanned
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setFitHeight(500);
            imageView.setFitWidth(670);
            imageView.setPreserveRatio(true);
            imageView.setImage(image);
            detectButton.setDisable(false);
        }
    }

    public boolean convertMatToImage(Mat matBGR) {

        int width = matBGR.width(), height = matBGR.height(), channels = matBGR.channels();
        byte[] sourcePixels = new byte[width * height * channels];
        matBGR.get(0, 0, sourcePixels);

        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);

        imageFX = SwingFXUtils.toFXImage(image, null);

        return true;
    }

    public void loadWebcam() {

        detectButton.setDisable(true);
        webcamThread = new Thread() {
            public void run() {
                System.out.println("Webcam Thread tunning");

                webcamFaceDetection = new

                        WebcamFaceDetection();

                Mat webcamImage = new Mat();

                VideoCapture videoCapture = new VideoCapture(0, 0);
                videoCapture.open(0, 0);

                if (videoCapture.isOpened()) {
                    System.out.println("Camera opened");

                    while (true) {

                        videoCapture.read(webcamImage);

                        if (!webcamImage.empty()) {

//                    setSize(webcamImage.width() +  30 , webcamImage.height() + 55);
                            webcamImage = webcamFaceDetection.detect(webcamImage);
                            convertMatToImage(webcamImage);
                            imageView.setImage(imageFX);
                        } else {
                            System.out.println("Problem loading webcam");
                            break;
                        }
                    }
                } else {
                    System.out.println("Cannot open webcam");
                }
            }
        };

        webcamThread.start();
    }

    public void detectImage() {

        Task<Void> task = faceDetection.detectFaces(file, imageView);
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                progressBarLabel.setVisible(true);
            }
        });

        Thread thread = new Thread(task);
        thread.start();
        progressBar.setDisable(false);
        progressBar.progressProperty().bind(task.progressProperty());
    }


}
