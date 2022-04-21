package userInterface;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DocumentListPanel extends JPanel{
    private JLabel text;

    public DocumentListPanel() {
        this.setLayout(new BorderLayout());
        text = new JLabel("Document list panel here!");
        text.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(text, BorderLayout.CENTER);
    }
}
