package com.George.View;

import com.George.OpenCV.WebcamDetection;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;

public class WebcamDialog extends JFrame implements Runnable{

    private static final long serialVersionUID = 1L;
    private WebcamDetection detector;
    private CameraPanel cameraPanel;

    public WebcamDialog( String name ){

        super(name);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        detector = new WebcamDetection();
        cameraPanel = new CameraPanel();

        setContentPane(cameraPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,500);
        setVisible(true);
    }

    @Override
    public void run() {
        System.out.println("Webcam thread started");
        this.displayScreen();
    }

    public void displayScreen(){

        Mat webcamImage = new Mat();

        VideoCapture videoCapture = new VideoCapture(0,0);
        videoCapture.open(0,0);

//        camera_ = cv::VideoCapture(1);
//        camera_.set(CV_CAP_PROP_CONVERT_RGB , false);
//        videoCapture.open(0);

        if(videoCapture.isOpened()){
            System.out.println("Camera opened");

            while (true){

                videoCapture.read(webcamImage);

                if(!webcamImage.empty()){

                    setSize(webcamImage.width() +  30 , webcamImage.height() + 55);
                    webcamImage = detector.detect(webcamImage);
                    cameraPanel.convertMatToImage(webcamImage);
                    cameraPanel.repaint();
                } else {
                    System.out.println("Problem loading webcam");
                    break;
                }
            }
        }else{
            System.out.println("Cannot open webcam");
        }
    }
}
