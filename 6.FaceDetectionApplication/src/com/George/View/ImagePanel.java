package com.George.View;

import com.George.Model.Constants;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImagePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel imageLabel;
    private ImageIcon transformedImageIcon;

    public ImagePanel(){

        this.imageLabel = new JLabel();

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(Constants.IMAGE_LABEL_BORDER, Constants.IMAGE_LABEL_BORDER,
                Constants.IMAGE_LABEL_BORDER, Constants.IMAGE_LABEL_BORDER));

        add(imageLabel,BorderLayout.CENTER);
    }

    public void updateImage(final Image image){
        imageLabel.setIcon(new ImageIcon(scaleImage(image)));
    }

    public Image scaleImage(Image image){
        return image.getScaledInstance(600,450,Image.SCALE_SMOOTH);
    }

    public void loadImage(File file){

        this.transformedImageIcon = new ImageIcon(file.getAbsolutePath());
        Image image = transformedImageIcon.getImage();

        updateImage(image);
    }

}
