package userInterface;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ThreadX extends Thread{
    private ArrayList<JLabel> imagesLabel;
    private Image image;
    private BufferedImage bufferedImage;
    private HomePanel homePanel;


    public ThreadX(HomePanel homePanel){
/*        imagesLabel = new ArrayList<>();
        String path = "C:\\Users\\Nouno\\IdeaProjects\\ProjetJava\\src\\ressources\\IconStore\\Magasin_";
        int numberImage = 0;
        this.homePanel = homePanel;
        ImageIcon imageIcon ;
        JLabel imageLabel
        try {
            for (int iImage = 0; iImage > 5; iImage++){
                bufferedImage = ImageIO.read(new File(path+numberImage+".png"));
                image = bufferedImage.getScaledInstance(100,100, Image.SCALE_DEFAULT);
                imageIcon = new ImageIcon(image);
                JLabel label.
            }
        } catch (IOException exception){
            JOptionPane.showMessageDialog(null, "Error",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        */
    }
    @Override
    public void run() {
        super.run();

    }
}
