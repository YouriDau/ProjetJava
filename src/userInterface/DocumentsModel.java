package userInterface;

import model.Document;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DocumentsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Document> contents;

    public DocumentsModel(ArrayList<Document> documents) {
        columnNames = new ArrayList<>();
        columnNames.add("Number");
        columnNames.add("Creation date");
        columnNames.add("Payment condition");
        columnNames.add("Credit limit");
        columnNames.add("Document type");
        columnNames.add("Process number");
        columnNames.add("UpdateTheStock");
        setContents(documents);
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
        Document document = contents.get(row);

        switch (column) {
            case 0: return document.getNumber();
            case 1: return document.getCreationDateStr();
            case 2: {
                    if (document.getPaymentCondition() != null)
                        return document.getPaymentCondition();
                    else
                        return null;
                    }
            case 3: {
                    if (document.getCreditLimit() != null)
                        return document.getCreditLimit();
                    else
                        return null;
                    }
            case 4: return document.getType();
            case 5: return document.getworkflowNumber();
            case 6: return document.getUpdateTheStock();
            default: return null;
        }
    }

    public Class getColumnClass(int column) {
        Class c;
        switch (column) {
            case 0: c = Integer.class;
                break;
            case 1: c = String.class;
                break;
            case 2: c = String.class;
                break;
            case 3: c = Double.class;
                break;
            case 4: c = Integer.class;
                break;
            case 5: c = Integer.class;
                break;
            case 6: c = Boolean.class;
            default: c = String.class;
        }
        return c;
    }

    public void setContents(ArrayList<Document> documents) {
        this.contents = documents;
    }
}
