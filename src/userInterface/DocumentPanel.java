package userInterface;

import javax.swing.*;
import java.awt.*;

public class DocumentPanel extends JPanel {
    private JLabel text;

    public DocumentPanel() {
        text = new JLabel("Document panel here!");
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setLayout(new BorderLayout());

        this.add(text);
    }
}
