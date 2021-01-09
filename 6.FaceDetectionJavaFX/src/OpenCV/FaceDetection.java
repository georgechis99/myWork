package OpenCV;

import Models.Constants;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

public class FaceDetection {

    private CascadeClassifier cascadeClassifier;

    //constructor
    public FaceDetection() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.cascadeClassifier = new CascadeClassifier(Constants.FINAL_CASCADE_CLASSIFIER);
    }

    public void setCascadeClassifier(String cascadeClassifier){
        this.cascadeClassifier = new CascadeClassifier(cascadeClassifier);
    }

    //main method of the class , which uses the Haar Casacade analysis to detect faces and places a rectangle on top of
    //   them so the user can see the results
    public Task<Void> detectFaces(File file, ImageView imageView) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateProgress(1,100);
                //converting the image into matrix (numerical) form so that the Haar Cascade analysis can be completed
                Mat imageMat = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_COLOR);
                MatOfRect faceDetections = new MatOfRect();
                updateProgress(25,100);

                //this stores all the detected faces in the faceDetections Mat
                cascadeClassifier.detectMultiScale(imageMat, faceDetections, 1.2, 3,10,new Size(30,30),new Size(500,500));
                updateProgress(50,100);

                //now we iterate through all the faces and place a rectangle on top of them to show that the detection was successful
                for (Rect rect : faceDetections.toArray()) {
                    Imgproc.rectangle(imageMat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                            new Scalar(100, 100, 250), 10);
                }
                updateProgress(75,100);
                //this renders the successfully analysed image on the screen
                BufferedImage bufferedImage = convertMatToImage(imageMat);  //first converting back the image from matrix form
                Image imageFinal = SwingFXUtils.toFXImage(bufferedImage, null); //making use of the SwingFXUtils to have ->
                imageView.setImage(imageFinal);  //setting the final image to the UI
                updateProgress(100,100);// -> an Image rather than a BufferedImage
                return null;
            }
        };
        return task;
    }

    //converts numerical representation of the image (matrix form) back into an image
    private BufferedImage convertMatToImage(Mat mat){

        //in case it is a black n white image
        int type = BufferedImage.TYPE_BYTE_GRAY;

        //if not we switch to BGR (or RGB red-green-blue) format
        if(mat.channels() > 1){
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        //here the converting is done
        int bufferSize = mat.channels()*mat.cols()*mat.rows();
        byte[] bytes = new byte[bufferSize];
        mat.get(0,0,bytes);
        BufferedImage image = new BufferedImage(mat.cols(),mat.rows(),type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(bytes,0,targetPixels,0,bytes.length);
        return image;
    }
}