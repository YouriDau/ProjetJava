package userInterface;

import model.BusinessTaskModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PromotionsByItemModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<BusinessTaskModel> contents;

    public PromotionsByItemModel(ArrayList<BusinessTaskModel> businessTaskModels){
        columnNames = new ArrayList<>();
        columnNames.add("Percentage of promo");
        columnNames.add("Promo ID");
        columnNames.add("Start date");
        columnNames.add("End date");
        columnNames.add("quantity sold");
        setContents(businessTaskModels);
    }

    public void setContents(ArrayList<BusinessTaskModel> contents) {
        this.contents = contents;
    }

    public int getColumnCount() {
        return columnNames.size();
    }
    public int getRowCount() {
        return contents.size();
    }
    public String getColumnName(int column) {
        return columnNames.get(column);
    }
    public Object getValueAt(int row, int column) {
        BusinessTaskModel businessTaskModel = contents.get(row);

        switch (column) {
            case 0: return businessTaskModel.getPercentagePromotion();
            case 1: return businessTaskModel.getPercentageId();
            case 2:

                return businessTaskModel.getDateStr(businessTaskModel.getStartDate());
            case 3: return businessTaskModel.getDateStr(businessTaskModel.getEndDate());
            case 4: return businessTaskModel.getDetailQuantity();
            default: return null;
        }
    }
    public Class getColumnClass(int column) {
        Class c;
        switch (column) {
            case 0: c = Integer.class;
                break;
            case 1: c = Integer.class;
                break;
            case 2: c = String.class;
                break;
            case 3: c = String.class;
                break;
            case 4: c = Integer.class;
                break;
            default: c = String.class;
        }
        return c;
    }

}
