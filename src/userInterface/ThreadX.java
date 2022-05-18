package userInterface;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ThreadX extends Thread{
    private HomePanel homePanel;
    private String imageName;
    private String path;
    String imageExtension;
    private int imageNumber;
    private GridBagConstraints layoutConstraints;

    public ThreadX(HomePanel homePanel){
        path = "./src/ressources/IconStore/";
        imageName = "Magasin_";
        imageNumber = 0;
        imageExtension = ".png";
        this.homePanel = homePanel;
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
    }
    @Override
    public void run() {
        JLabel jLabel = new JLabel();
        super.run();
        homePanel.add(jLabel, layoutConstraints);
        while (true) {
            try {
                jLabel.setIcon(new ImageIcon(path+imageName+imageNumber+imageExtension));
                Thread.sleep(300);
                homePanel.setVisible(true);
                if (imageNumber < 4) {
                    imageNumber++;
                }
                else{
                    imageNumber = 0;
                }

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Thread Error",
                        "Error Thread", JOptionPane.ERROR_MESSAGE);
            }

        }

    }
}
