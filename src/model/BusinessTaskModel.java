package model;

public class BusinessTaskModel {
    private String wordingItem;
    private Integer percentagePromotion;
    private Integer percentageId;
    private Integer detailQuantity;

    public BusinessTaskModel(String wordingItem,Integer percentagePromotion,Integer percentageId,Integer detailQuantity){
        setWordingItem(wordingItem);
        setPercentagePromotion(percentagePromotion);
        setPercentageId(percentageId);
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

    public Integer getPercentageId() {
        return percentageId;
    }
}
