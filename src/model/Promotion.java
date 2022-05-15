package model;

import java.util.GregorianCalendar;

public class Promotion {
    Integer id;
    Integer percentage;
    GregorianCalendar startDate;
    GregorianCalendar endDate;
    Integer itemId;

    public Promotion(Integer id, Integer percentage, GregorianCalendar startDate, GregorianCalendar endDate, Integer itemId){
        setId(id);
        setPercentage(percentage);
        setStartDate(startDate);
        setEndDate(endDate);
        setItemId(itemId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public void setStartDate(GregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getPercentage() {
        return percentage;
    }
}
