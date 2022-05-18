package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Detail;
import model.Item;
import model.Promotion;
import model.ResearchByPromo;

import java.util.ArrayList;

public class ItemManager {
    private DataAccess dao;

    public ItemManager() throws DBException, SingletonConnectionException{
        setDao(new DBAccess());
    }

    public void setDao(DataAccess itemDBAccess){
        this.dao = itemDBAccess;
    }
    public ArrayList<ResearchByPromo> getResearchByPromo(int littleValue, int bigValue) throws DBException, SingletonConnectionException{
        return dao.getResearchByPromo(littleValue, bigValue);
    }


}
