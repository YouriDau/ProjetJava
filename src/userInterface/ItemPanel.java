package userInterface;

import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel {
    private JLabel text;

    public ItemPanel () {
        text = new JLabel("Item panel here!");
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setLayout(new BorderLayout());

        this.add(text);
    }
}
