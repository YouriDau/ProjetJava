package model;

import java.util.GregorianCalendar;

public class Detail {

    private Integer code;
    private Double unit_price;
    private Integer quantity;
    private Double vatRate;
    private Integer backOrder;
    private Integer documentId;
    private Integer itemId;
    private Integer lotId;

    public Detail(Integer code,Double unit_price,Integer quantity,Double vatRate,Integer backOrder,Integer documentId, Integer itemId,Integer lotId){
        setCode(code);
        setUnit_price(unit_price);
        setQuantity(quantity);
        setVatRate(vatRate);
        setBackOrder(backOrder);
        setDocumentId(documentId);
        setItemId(itemId);
        setLotId(lotId);
    }
    // Setters
    public void setCode(Integer code) {
        this.code = code;
    }
    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setVatRate(Double vatRate) {
        this.vatRate = vatRate;
    }
    public void setBackOrder(Integer backOrder) {
        this.backOrder = backOrder;
    }
    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    public void setLotId(Integer lotId) {
        this.lotId = lotId;
    }
}
