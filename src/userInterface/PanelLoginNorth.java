package userInterface;

import javax.swing.*;
import java.awt.*;

public class PanelLoginNorth extends JPanel {
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JTextField login;
    private JPasswordField password;

    public PanelLoginNorth() {
        this.setLayout(new GridLayout(2, 2, 10, 10));

        loginLabel = new JLabel("Login : ");
        loginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        login = new JTextField( 10);
        this.add(loginLabel);
        this.add(login);

        passwordLabel = new JLabel("Password : ");
        password = new JPasswordField(20);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.add(passwordLabel);
        this.add(password);
    }

    public String getLogin() {
        return login.getText();
    }

    public String getPassword() {
        return new String(password.getPassword());
    }
}
