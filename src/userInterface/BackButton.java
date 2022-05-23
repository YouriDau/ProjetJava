package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton {
    private JPanel panel;
    private Container container;

    public BackButton(JPanel panel, Container container) {
        super("Back");
        this.panel = panel;
        this.container = container;

        this.addActionListener(new BackListener());
    }

    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(panel);
            container.revalidate();
            container.repaint();
        }
    }
}
