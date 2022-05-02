package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton {
    private Container container;

    public BackButton(Container container) {
        super("Home");
        this.container = container;

        this.addActionListener(new BackListener());
    }

    public class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new HomePanel());
            container.revalidate();
            container.repaint();
        }
    }
}
