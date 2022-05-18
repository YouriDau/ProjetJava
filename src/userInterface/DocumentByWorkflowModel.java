package userInterface;

import model.Document;
import model.DocumentType;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DocumentByWorkflowModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Document> documents;
    private Document[] documentList;
    private DocumentType[] documentTypesList;

    public DocumentByWorkflowModel(ArrayList<Document> documents, ArrayList<DocumentType> documentTypes) {
        columnNames = new ArrayList<>();
        columnNames.add("Number");
        columnNames.add("Creation date");
        columnNames.add("Credit limit");
        columnNames.add("Document type");
        setContents(documents, documentTypes);
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
        DocumentType documentType = documentTypesList[row];

        switch (column) {
            case 0: return document.getNumber();
            case 1: return document.getCreationDateStr();
            case 2: {
                if (document.getCreditLimit() != null)
                    return document.getCreditLimit();
                else
                    return null;
            }
            case 3: return documentType.getWording();
            case 4: return document.getworkflowNumber();
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
            case 2: c = Double.class;
                break;
            case 3: c = String.class;
                break;
            case 4: c = Integer.class;
                break;
            default: c = String.class;
        }
        return c;
    }

    public void setContents(ArrayList<Document> documents, ArrayList<DocumentType> documentTypes) {
        this.documents = documents;
        this.documentList = new Document[documents.size()];
        this.documentTypesList = new DocumentType[documentTypes.size()];

        int i = 0;
        for (Document document : documents) {
            this.documentList[i] = document;
            i++;
        }

        i = 0;
        for (DocumentType documentType : documentTypes) {
            this.documentTypesList[i] = documentType;
            i++;
        }
    }

}
