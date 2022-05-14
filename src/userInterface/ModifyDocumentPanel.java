package userInterface;

import javax.swing.*;
import java.awt.*;

public class ModifyDocumentPanel extends JPanel {
    private Container container;
    private JLabel text;

    public ModifyDocumentPanel(Container container) {
        this.container = container;
        this.setLayout(new BorderLayout());

        text = new JLabel("test");

        this.add(text);
    }
}
