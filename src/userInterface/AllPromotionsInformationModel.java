package userInterface;

import model.ResearchByPromoModel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllPromotionsInformationModel extends AbstractTableModel {
    ArrayList<String> columnNames;
    ArrayList<ResearchByPromoModel> researchByPromoModels;
    public AllPromotionsInformationModel(ArrayList<ResearchByPromoModel> researchByPromoModels){
        columnNames = new ArrayList<>();
        columnNames.add("Percentage");
        columnNames.add("WordingItem");
        columnNames.add("Last unit price");
        setResearchByPromos(researchByPromoModels);
    }

    public void setResearchByPromos(ArrayList<ResearchByPromoModel> researchByPromoModels) {
        this.researchByPromoModels = researchByPromoModels;
    }

    public int getRowCount(){
        return researchByPromoModels.size();
    }
    public int getColumnCount(){
        return columnNames.size();
    }
    public Object getValueAt(int row, int column){
        ResearchByPromoModel researchByPromoModel = researchByPromoModels.get(row);

        switch (column){
            case 0 : return researchByPromoModel.getPercentage();
            case 1 : return researchByPromoModel.getWordingItem();
            case 2 : return researchByPromoModel.getLastUnitPriceDetail();
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
