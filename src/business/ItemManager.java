package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Detail;
import model.Item;
import model.Promotion;

import java.util.ArrayList;

public class ItemManager {
    private DataAccess dao;

    public ItemManager() throws DBException, SingletonConnectionException{
        setDao(new DBAccess());
    }

    public void setDao(DataAccess itemDBAccess){
        this.dao = itemDBAccess;
    }
    public ArrayList<Detail> getDetails(ArrayList<Item> items) throws DBException, SingletonConnectionException{
        return dao.getDetails(items);
    }

    public ArrayList<Promotion> getPromotions(int littleValue, int bigValue) throws DBException, SingletonConnectionException{
        return dao.getPromotions(littleValue, bigValue);
    }

    public ArrayList<Item> getItems(ArrayList<Promotion> promotions) throws DBException, SingletonConnectionException{
        return dao.getItems(promotions);
    }


}
