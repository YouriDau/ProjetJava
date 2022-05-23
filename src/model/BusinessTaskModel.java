package model;

import business.BusinessTaskManager;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class BusinessTaskModel {
    private String wordingItem;
    private Integer percentagePromotion;
    private Integer percentageId;
    private GregorianCalendar startDate;
    private  GregorianCalendar endDate;
    private Integer detailQuantity;

    public BusinessTaskModel(String wordingItem, Integer percentagePromotion, Integer percentageId, GregorianCalendar startDate, GregorianCalendar endDate, Integer detailQuantity){
        setWordingItem(wordingItem);
        setPercentagePromotion(percentagePromotion);
        setPercentageId(percentageId);
        setStartDate(startDate);
        setEndDate(endDate);
        setDetailQuantity(detailQuantity);
    }
    public BusinessTaskModel(){
    // utile pour le test unitaire
    }

    public void setWordingItem(String wordingItem) {
        wordingItem = wordingItem;
    }

    public void setPercentagePromotion(Integer percentagePromotion) {
        this.percentagePromotion = percentagePromotion;
    }

    public void setPercentageId(Integer percentageId) {
        this.percentageId = percentageId;
    }

    public void setDetailQuantity(Integer detailQuantity) {
        this.detailQuantity = detailQuantity;
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public String getWordingItem() {
        return wordingItem;
    }

    public Integer getPercentagePromotion() {
        return percentagePromotion;
    }

    public Integer getPercentageId() {
        return percentageId;
    }

    public GregorianCalendar getStartDate() {
        return startDate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public Integer getDetailQuantity() {
        return detailQuantity;
    }
    public String getDateStr(GregorianCalendar date) {
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH)+1;
        int day = date.get(Calendar.DAY_OF_MONTH);

        return year + "-" + month + "-" + day;
    }
}
