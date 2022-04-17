package userInterface;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private Container container;
    private PanelAccueil accueilPage;

    public MainWindow() {
        super("Magasin");
        setBounds(100, 100, 500, 500);

        container = this.getContentPane();
        container.setLayout(new BorderLayout());

        accueilPage = new PanelAccueil();
        container.add(accueilPage);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
}
