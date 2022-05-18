package userInterface;

import model.Detail;
import model.Item;
import model.Promotion;
import model.ResearchByPromo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllPromotionsInformationModel extends AbstractTableModel {
    ArrayList<String> columnNames;
    ArrayList<ResearchByPromo> researchByPromos;
    public AllPromotionsInformationModel(ArrayList<ResearchByPromo> researchByPromos){
        columnNames = new ArrayList<>();
        columnNames.add("Percentage");
        columnNames.add("WordingItem");
        columnNames.add("Last unit price");
        setResearchByPromos(researchByPromos);
    }

    public void setResearchByPromos(ArrayList<ResearchByPromo> researchByPromos) {
        this.researchByPromos = researchByPromos;
    }

    public int getRowCount(){
        return researchByPromos.size();
    }
    public int getColumnCount(){
        return columnNames.size();
    }
    public Object getValueAt(int row, int column){
        ResearchByPromo researchByPromo = researchByPromos.get(row);

        switch (column){
            case 0 : return researchByPromo.getPercentage();
            case 1 : return researchByPromo.getWordingItem();
            case 2 : return researchByPromo.getLastUnitPriceDetail();
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
