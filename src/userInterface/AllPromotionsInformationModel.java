package userInterface;

import model.Detail;
import model.Item;
import model.Promotion;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllPromotionsInformationModel extends AbstractTableModel {
    ArrayList<String> columnNames;
    ArrayList<Promotion> promotions;
    ArrayList<Item> items;
    ArrayList <Detail> details;
    public AllPromotionsInformationModel(ArrayList<Promotion> promotions, ArrayList<Item> items, ArrayList<Detail> details){
        columnNames = new ArrayList<>();
        columnNames.add("Percentage");
        columnNames.add("WordingItem");
        columnNames.add("Last unit price");
        setPromotions(promotions);
        setItems(items);
        setDetails(details);
    }

    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }
    public int getRowCount(){
        return promotions.size();
    }
    public int getColumnCount(){
        return columnNames.size();
    }
    public Object getValueAt(int row, int column){
        Promotion promotion = promotions.get(row);
        Item item = items.get(row);
        Detail detail = details.get(row);

        switch (column){
            case 0 : return promotion.getPercentage();
            case 1 : return item.getWording();
            case 2 : return detail.getUnit_price();
            default: return null;
        }
    }
    @Override
    public Class getColumnClass(int column){
        Class c;
        switch (column){
            case 0 : c = Double.class;
               break;
            case 1 : c = String.class;
                break;
            case 2 : c = Double.class;
                break;
            default: c = String.class;
        }
        return c;
    }
    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }
}
