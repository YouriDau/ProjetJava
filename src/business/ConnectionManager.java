package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.CloseConnectionException;
import exception.DBException;
import exception.SingletonConnectionException;

public class ConnectionManager {
    private DataAccess dao;
    public ConnectionManager(){
        setDao(new DBAccess());
    }

    public void setDao(DataAccess dao) {
        this.dao = dao;
    }

    public void closeConnection() throws CloseConnectionException {
        dao.closeConnection();
    }
}
