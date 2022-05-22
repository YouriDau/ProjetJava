package dataAccess;

import exception.SingletonConnectionException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connection;

    public static Connection getInstance() throws SingletonConnectionException {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store",
                        "root",
                        "root");
            }
            catch (SQLException exception) {
                throw new SingletonConnectionException(exception.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() throws SingletonConnectionException {
        try {
            connection.close();
        }
        catch (SQLException exception) {
            throw new SingletonConnectionException(exception.getMessage());
        }
    }

    public static boolean connectionIsNull() {
        return connection == null;
    }
}
