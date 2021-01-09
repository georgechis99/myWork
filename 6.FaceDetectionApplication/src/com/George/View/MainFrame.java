package com.George.View;

import com.George.Model.Constants;
import com.George.OpenCV.FaceDetection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainFrame extends JFrame {

    private static void method() {
        System.out.println("invoked");
    }

    private static final long serialVersionUID = 1L;
    private ImagePanel imagePanel;
    private JFileChooser fileChooser;
    private FaceDetection faceDetection;
    private File file;

    public MainFrame() {
        super(Constants.APPLICATION_NAME);

//        addKeyListener(this);
        this.setJMenuBar(createMenuBar());

        this.imagePanel = new ImagePanel();
        this.fileChooser = new JFileChooser();
        this.faceDetection = new FaceDetection();

        imagePanel.setBackground(Color.DARK_GRAY);
        add(imagePanel, BorderLayout.CENTER);

        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
//        MainFrame.this.imagePanel.loadImage(new File("C:\\Users\\ACER DEMO\\Desktop\\homeScreen.png"));
    }

    public JMenuBar createMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load Image");
        JMenuItem detectMenuItem = new JMenuItem("Detect Faces");
        detectMenuItem.setEnabled(false);
        JMenuItem webcamMenuItem = new JMenuItem("Webcam");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        fileMenu.add(loadMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(detectMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(webcamMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (fileChooser.showDialog(MainFrame.this, "Choose") == JFileChooser.APPROVE_OPTION) {
                    MainFrame.this.file = fileChooser.getSelectedFile();
                    System.out.println(MainFrame.this.file);
                    MainFrame.this.imagePanel.loadImage(MainFrame.this.file);
                    detectMenuItem.setEnabled(true);
                }
            }
        });

        detectMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame.this.faceDetection.detectFaces(MainFrame.this.file, imagePanel);
            }
        });

        webcamMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Thread t1 = new Thread(new WebcamDialog("Webcam"));
                t1.start();
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        JMenu aboutMenu = new JMenu("About");
        JMenuItem gitHub = new JMenuItem("GitHub");
        JMenuItem documentation = new JMenuItem("Documentation");

        aboutMenu.add(gitHub);
        fileMenu.addSeparator();
        aboutMenu.add(documentation);

        gitHub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    URI uri = new URI("https://www.google.com");
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        return menuBar;
    }

    public static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void keyTyped(KeyEvent e) {
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//    }

//    @Override
//    public void keyReleased(KeyEvent e) {
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_ENTER: {
//                if (fileChooser.showDialog(MainFrame.this, "Choose") == JFileChooser.APPROVE_OPTION) {
//                    MainFrame.this.file = fileChooser.getSelectedFile();
//                    System.out.println(MainFrame.this.file);
//                    MainFrame.this.imagePanel.loadImage(MainFrame.this.file);
//                }
//            }
//            case KeyEvent.VK_L: {
//                MainFrame.this.faceDetection.detectFaces(MainFrame.this.file, imagePanel);
//            }
//            default: {
//                System.out.println("Other key pressed");
//            }
//        }
//    }
}
