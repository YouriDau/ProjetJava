package userInterface;

import model.Document;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DocumentsModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Document> documents;

    public DocumentsModel(ArrayList<Document> documents) {
        columnNames = new ArrayList<>();
        columnNames.add("Number");
        columnNames.add("Creation date");
        columnNames.add("Payment condition");
        columnNames.add("Credit limit");
        columnNames.add("Document type");
        columnNames.add("Process number");
        setContents(documents);
    }

    public int getColumnCount() {
        return columnNames.size();
    }

    public int getRowCount() {
        return documents.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public Object getValueAt(int row, int column) {
        Document document = documents.get(row);
        int year = document.getCreationDate().get(Calendar.YEAR);
        int month = document.getCreationDate().get(Calendar.MONTH) + 1;
        int day = document.getCreationDate().get(Calendar.DAY_OF_MONTH);

        String date = year + "-" +
                      month + "-" +
                      day;

        switch (column) {
            case 0: return document.getNumber();
            case 1: return date;
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
            default: c = String.class;
        }
        return c;
    }

    public void setContents(ArrayList<Document> documents) {
        this.documents = documents;
    }
}
