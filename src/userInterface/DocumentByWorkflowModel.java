package userInterface;

import model.Document;
import model.DocumentByWorkflowType;
import model.DocumentType;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DocumentByWorkflowModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<DocumentByWorkflowType> contents;

    public DocumentByWorkflowModel(ArrayList<DocumentByWorkflowType> documents) {
        columnNames = new ArrayList<>();
        columnNames.add("Document");
        columnNames.add("Workflow");
        columnNames.add("Creation date");
        columnNames.add("Credit limit");
        columnNames.add("Document type");
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
        DocumentByWorkflowType document = contents.get(row);


        switch (column) {
            case 0: return document.getDocumentNumber();
            case 1: return document.getWorkflowNumber();
            case 2: return document.getCreationDateStr();
            case 3: {
                if (document.getCreditLimit() != null)
                    return document.getCreditLimit();
                else
                    return null;
            }
            case 4: return document.getDocumentType();
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
            case 3: c = Double.class;
                break;
            case 4: c = String.class;
                break;
            default: c = String.class;
        }
        return c;
    }

    public void setContents(ArrayList<DocumentByWorkflowType> documents) {
        this.contents = documents;
    }

}
