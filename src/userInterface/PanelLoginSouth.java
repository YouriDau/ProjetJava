package userInterface;

import controller.ApplicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLoginSouth extends JPanel {
    private JButton login;
    private ApplicationController controller;

    public PanelLoginSouth() {
        this.controller = new ApplicationController();

        this.setLayout(new BorderLayout());
        login = new JButton("login");
        this.add(login);
    }

    public class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

        }
    }
}
