package userInterface;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HomePanel extends JPanel {
    private JLabel text;
    private ThreadX threadX;
    private GridBagConstraints layoutConstraints;

    public HomePanel() {
        text = new JLabel("Welcome on our app");
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(new Font("Verdana", Font.PLAIN, 17));
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.insets = new Insets(0, 0, 30, 0);
        this.add(text, layoutConstraints);
        threadX = new ThreadX(this);
        threadX.start();


    }

}



