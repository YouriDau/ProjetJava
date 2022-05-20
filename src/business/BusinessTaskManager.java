package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.BusinessTaskModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class BusinessTaskManager {
    private DataAccess dao;

    public BusinessTaskManager() throws DBException, SingletonConnectionException {
        setDao(new DBAccess());
    }

    public void setDao(DataAccess businessTaskAccess) {
        this.dao = businessTaskAccess;
    }

    public ArrayList<BusinessTaskModel> getBusinessTaskInformation()throws DBException, SingletonConnectionException{
        return dao.getBusinessTaskInformation();
    }

    public ArrayList<BusinessTaskModel> calculatorBusinessTaskInformation() throws DBException, SingletonConnectionException{
        ArrayList<BusinessTaskModel> businessTaskModelInformations = getBusinessTaskInformation();
        ArrayList<BusinessTaskModel> businessTaskModelResult = new ArrayList<>();
        for (BusinessTaskModel businessTaskModel : businessTaskModelInformations){

        }

        return businessTaskModelResult;
    }

}
