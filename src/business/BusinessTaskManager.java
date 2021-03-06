package business;

import controller.DataAccess;
import dataAccess.DBAccess;
import exception.AddPromotionException;
import exception.SingletonConnectionException;
import model.BusinessTaskModel;

import java.util.ArrayList;

public class BusinessTaskManager {
    private DataAccess dao;

    public BusinessTaskManager() {
        setDao(new DBAccess());
    }

    public void setDao(DataAccess businessTaskAccess) {
        this.dao = businessTaskAccess;
    }

    public ArrayList<BusinessTaskModel> getBusinessTaskInformation(String wordingItemReceive)throws SingletonConnectionException{
        return dao.getBusinessTaskInformation(wordingItemReceive);
    }
    public ArrayList<String> getAllItemsWording() throws SingletonConnectionException{
        return dao.getAllItemsWording();
    }
    public void addPromotion(int percentage, String startDate, String endDate, String itemWording) throws AddPromotionException, SingletonConnectionException{
        dao.addPromotion(percentage, startDate, endDate, itemWording);
    }
}
