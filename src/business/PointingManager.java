package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.SingletonConnectionException;
import model.PointingBetweenDates;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PointingManager {
    private DataAccess dao;

    public PointingManager() {
        setDao(new DBAccess());
    }

    public void setDao(DataAccess itemDBAccess){
        this.dao = itemDBAccess;
    }

    public ArrayList<PointingBetweenDates> getPointingBetweenDates(GregorianCalendar firstDate, GregorianCalendar secondDate) throws SingletonConnectionException {
        return dao.getPointingBetweenDates(firstDate, secondDate);
    }
}
