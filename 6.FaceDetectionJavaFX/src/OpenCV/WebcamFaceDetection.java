package OpenCV;

import Models.Constants;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class WebcamFaceDetection {

    private CascadeClassifier cascadeClassifier;
    private MatOfRect detectedFaces;
    private Mat coloredImage;
    private Mat greyImage;

    public WebcamFaceDetection(){

        this.detectedFaces = new MatOfRect();
        this.coloredImage = new Mat();
        this.greyImage = new Mat();
        this.cascadeClassifier = new CascadeClassifier(Constants.FINAL_CASCADE_CLASSIFIER);
    }

    public void setCascadeClassifier(String cascadeClassifier){
        this.cascadeClassifier = new CascadeClassifier(cascadeClassifier);
    }

    public Mat detect(Mat inputFrame){

        inputFrame.copyTo(coloredImage);
        inputFrame.copyTo(greyImage);

        //converts input image from one color space to another
        Imgproc.cvtColor(coloredImage,greyImage,Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(greyImage,greyImage);

        cascadeClassifier.detectMultiScale(greyImage,detectedFaces);

        showFacesOnScreen();

        return coloredImage;
    }

    private void showFacesOnScreen(){
        for(Rect rect : detectedFaces.toArray()){
            Imgproc.rectangle(coloredImage,new Point(rect.x,rect.y),
                    new Point(rect.x + rect.width,rect.y + rect.height), new Scalar(100,100,250),10);
        }
    }
}
