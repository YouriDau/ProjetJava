package userInterface;

import javax.swing.*;
import java.awt.*;

public class PanelAccueil extends JPanel {
    private JLabel label1;

    public PanelAccueil() {
        label1 = new JLabel("Bienvenue sur notre site");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setLayout(new BorderLayout());
        this.add(label1);
    }
}
