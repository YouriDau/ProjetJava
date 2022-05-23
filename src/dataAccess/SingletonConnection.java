package dataAccess;

import exception.CloseConnectionException;
import exception.SingletonConnectionException;

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

    public static void closeConnection() throws CloseConnectionException {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new CloseConnectionException();
            }
        }
    }

    public static boolean connectionIsNull() {
        return connection == null;
    }
}
