package userInterface;

import controller.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginWindow extends JFrame {
    // Comme
    private Container container;
    private PanelLoginNorth northPanel;
    private PanelLoginSouth southPanel;
    private ApplicationController controller;

    public LoginWindow() {
        super("login");
        setBounds(100, 100, 400, 130);

        container = this.getContentPane();
        container.setLayout(new BorderLayout());

        northPanel = new PanelLoginNorth();
        southPanel = new PanelLoginSouth();
        container.add(northPanel, BorderLayout.CENTER);
        container.add(southPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);

        controller = new ApplicationController();
    }

}
