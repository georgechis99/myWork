package com.George.OpenCV;

import com.George.Model.Constants;
import com.George.View.ImagePanel;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

public class FaceDetection {

    private CascadeClassifier cascadeClassifier;

    public FaceDetection() {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        this.cascadeClassifier = new CascadeClassifier(Constants.CASCADE_CLASSIFIER);
    }

    public void detectFaces(File file, ImagePanel imagePanel) {

        //converting the image into matrix (numerical) form so that the Haar Cascade analysis can be completed
        Mat image = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_COLOR);
        MatOfRect faceDetections = new MatOfRect();

        //this stores all the detected faces in the faceDetections Mat
        cascadeClassifier.detectMultiScale(image, faceDetections, 1.2, 3,10,new Size(30,30),new Size(500,500));

        //now we iterate through all the faces and place a rectangle on top of them to show that the detection was successful
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(100, 100, 250), 10);
        }

        BufferedImage bufferedImage = convertMatToImage(image);
        imagePanel.updateImage(bufferedImage);
    }

    //converts numerical representation of the image (matrix form) back into an image
    private BufferedImage convertMatToImage(Mat mat){

        //in case it is a black n white image
        int type = BufferedImage.TYPE_BYTE_GRAY;

        //if not we switch to BGR (or RGB red-green-blue) format
        if(mat.channels() > 1){
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        int bufferSize = mat.channels()*mat.cols()*mat.rows();
        byte[] bytes = new byte[bufferSize];
        mat.get(0,0,bytes);
        BufferedImage image = new BufferedImage(mat.cols(),mat.rows(),type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(bytes,0,targetPixels,0,bytes.length);
        return image;
    }
}
