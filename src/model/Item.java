package model;

import java.util.GregorianCalendar;

public class Item {
    Integer id;
    String wording;
/*    GregorianCalendar productionDate;
    String packaging;
    double minimumLevel;
    Integer quantityInStock;

    public Item(Integer id, String wording, GregorianCalendar productionDate, String packaging, double minimumLevel,Integer quantityInStock){
        setId(id);
        setWording(wording);
        setProductionDate(productionDate);
        setPackaging(packaging);
        setMinimumLevel(minimumLevel);
        setQuantityInStock(quantityInStock);
    }*/
    public Item(Integer id, String wording){
        setId(id);
        setWording(wording);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getWording() {
        return wording;
    }
    /* public void setProductionDate(GregorianCalendar productionDate) {
        this.productionDate = productionDate;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public void setMinimumLevel(double minimumLevel) {
        this.minimumLevel = minimumLevel;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }*/
}
