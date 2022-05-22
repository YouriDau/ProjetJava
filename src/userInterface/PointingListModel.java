package userInterface;

import model.PointingBetweenDates;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PointingListModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<PointingBetweenDates> contents;

    public PointingListModel(ArrayList<PointingBetweenDates> pointings) {
        columnNames = new ArrayList<>();
        columnNames.add("Last name");
        columnNames.add("First name");
        columnNames.add("Person type");
        columnNames.add("Date");
        columnNames.add("Pointing type");
        setContents(pointings);
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
        PointingBetweenDates pointing = contents.get(row);

        switch (column) {
            case 0: return pointing.getLastName();
            case 1:  {
                if (pointing.getFirstName() != null)
                    return pointing.getFirstName();
                else
                    return null;
                }
            case 2:
                return pointing.getPersonType();
            case 3: return pointing.getPointingDateHourStr();
            case 4: return pointing.getPointingType();
            default: return null;
        }
    }

    public Class getColumnClass(int column) {
        Class c;
        switch (column) {
            case 0, 1, 2, 3, 4: c = String.class;
                break;
            default: c = String.class;
        }
        return c;
    }

    public void setContents(ArrayList<PointingBetweenDates> pointings) {
        this.contents = pointings;
    }
}
