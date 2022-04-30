package userInterface;

import controller.ApplicationController;
import exception.DBException;
import model.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DocumentListPanel extends JPanel{
    private static final int NB_TITLES = 6;

    private JTable table;
    private String[] columnTitles;
    private Object[][] data;
    private ApplicationController controller;
    private DefaultTableCellRenderer centerRenderer;

    public DocumentListPanel() {
        try {
            controller = new ApplicationController();
            columnTitles = new String[NB_TITLES];
            this.centerRenderer = new DefaultTableCellRenderer();
            this.centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            this.setLayout(new BorderLayout());

            setColumnTitles(Document.getColumnsTitles());
            setData(controller.getAllDocuments());

            table = new JTable(data, columnTitles);
            table.setEnabled(false);

            this.add(table.getTableHeader(), BorderLayout.PAGE_START);
            this.add(table, BorderLayout.CENTER);
        }
        catch (DBException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void setColumnTitles(String[] columnTitles) {
        for (int i = 0; i < NB_TITLES; i++) {
            this.columnTitles[i] = columnTitles[i];
        }
    }

    public void setData(ArrayList<Document> documents) {
        int iDocument = 0;
        int nbDocuments = documents.size();
        Document document;
        this.data = new Object[nbDocuments][NB_TITLES];

        while (iDocument < nbDocuments) {
            document = documents.get(iDocument);

            this.data[iDocument][0] = document.getNumber();
            this.data[iDocument][1] = document.getCreationDate().get(Calendar.YEAR);
            if (document.getPaymentCondition() != null) {
                this.data[iDocument][2] = document.getPaymentCondition();
            }
            if (document.getCreditLimit() != null) {
                this.data[iDocument][3] = document.getCreditLimit();
            }

            this.data[iDocument][4] = document.getType();
            this.data[iDocument][5] = document.getworkflowNumber();

            iDocument++;
        }
    }

    public void centerData() {
        for (int i = 0; i < NB_TITLES; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}
