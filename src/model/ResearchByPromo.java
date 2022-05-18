package model;

public class ResearchByPromo {
    Integer percentage;
    String wordingItem;
    Double lastUnitPriceDetail;

    public ResearchByPromo(Integer percentage, String wordingItem,Double lastUnitPriceDetail){
        setPercentage(percentage);
        setWordingItem(wordingItem);
        setLastUnitPriceDetail(lastUnitPriceDetail);
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public void setWordingItem(String wordingItem) {
        this.wordingItem = wordingItem;
    }

    public void setLastUnitPriceDetail(Double lastUnitPriceDetail) {
        this.lastUnitPriceDetail = lastUnitPriceDetail;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public String getWordingItem() {
        return wordingItem;
    }

    public Double getLastUnitPriceDetail() {
        return lastUnitPriceDetail;
    }
}
