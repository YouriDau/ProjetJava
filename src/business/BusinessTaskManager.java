package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.BusinessTask;

import java.util.ArrayList;

public class BusinessTaskManager {
    private DataAccess dao;

    public BusinessTaskManager() throws DBException, SingletonConnectionException {
        setDao(new DBAccess());
    }

    public void setDao(DataAccess businessTaskAccess) {
        this.dao = businessTaskAccess;
    }

    public ArrayList<BusinessTask> getBusinessTaskInformation(String wordingItemReceive)throws DBException, SingletonConnectionException{
        return dao.getBusinessTaskInformation(wordingItemReceive);
    }
}
