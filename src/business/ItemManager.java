package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Detail;

import java.util.ArrayList;

public class ItemManager {
    private DataAccess dao;

    public ItemManager() throws DBException, SingletonConnectionException{
        dao = new DBAccess();
    }

    public ArrayList<Detail> getDetails(){
        return null;
    }


}
