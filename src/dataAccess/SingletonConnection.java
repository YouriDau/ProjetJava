package dataAccess;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;

    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_magasin",
                        "root",
                        "root");

            }
            catch (SQLException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        }
        catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
