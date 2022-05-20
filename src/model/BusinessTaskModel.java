package model;

import java.util.GregorianCalendar;

public class BusinessTaskModel {
    private String wordingItem;
    private Integer percentagePromotion;
    private Integer percentageId;
    private GregorianCalendar startDate;
    private  GregorianCalendar endDate;
    private Integer detailQuantity;

    public BusinessTaskModel(String wordingItem,Integer percentagePromotion,Integer percentageId, GregorianCalendar startDate, GregorianCalendar endDate, Integer detailQuantity){
        setWordingItem(wordingItem);
        setPercentagePromotion(percentagePromotion);
        setPercentageId(percentageId);
        setStartDate(startDate);
        setEndDate(endDate);
        setDetailQuantity(detailQuantity);
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

    public Integer getPercentageId() {
        return percentageId;
    }
}
