package userInterface;

import javax.swing.*;
import java.awt.*;

public class AccueilPanel extends JPanel {
    private JLabel text;

    public AccueilPanel() {
        text = new JLabel("Bienvenue sur notre site");
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(new Font("Verdana", Font.PLAIN, 17));
        this.setLayout(new BorderLayout());

        this.add(text, BorderLayout.CENTER);
    }
}
