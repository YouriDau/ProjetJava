package userInterface;

import controller.ApplicationController;

import javax.swing.*;
import java.awt.*;

public class PanelLogin extends JPanel {
    private PanelLoginNorth northPanel;
    private PanelLoginSouth southPanel;

    public PanelLogin() {
        northPanel = new PanelLoginNorth();
        southPanel = new PanelLoginSouth();
        this.setLayout(new BorderLayout());
        this.add(northPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }


}
