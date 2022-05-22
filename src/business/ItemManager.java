package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.ResearchByPromoModel;

import java.util.ArrayList;

public class ItemManager {
    private DataAccess dao;

    public ItemManager() {
        setDao(new DBAccess());
    }

    public void setDao(DataAccess itemDBAccess){
        this.dao = itemDBAccess;
    }

    public ArrayList<ResearchByPromoModel> getResearchByPromo(int littleValue, int bigValue) throws DBException, SingletonConnectionException{
        return dao.getResearchByPromo(littleValue, bigValue);
    }


}
