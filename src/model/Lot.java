package model;

import java.util.GregorianCalendar;

public class Lot {
    Integer reference;
    GregorianCalendar arrivageDate;
    GregorianCalendar expirationDate;

    public Lot(Integer reference, GregorianCalendar arrivageDate, GregorianCalendar expirationDate){
        setReference(reference);
        setArrivageDate(arrivageDate);
        setExpirationDate(expirationDate);
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    public void setArrivageDate(GregorianCalendar arrivageDate) {
        this.arrivageDate = arrivageDate;
    }

    public void setExpirationDate(GregorianCalendar expirationDate) {
        this.expirationDate = expirationDate;
    }
}
