package userInterface;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private JLabel text;

    public HomePanel() {
        text = new JLabel("Welcome on our app");
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(new Font("Verdana", Font.PLAIN, 17));
        this.setLayout(new BorderLayout());

        this.add(text, BorderLayout.CENTER);
    }
}
