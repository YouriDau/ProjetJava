package userInterface;

import model.Document;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

public class WorkflowListPanel extends JPanel {
    public static final int NB_TITLES = 6;

    private JTable table;
    private String[] columnTitles;
    private JLabel listEmpty;
    private Object[][] data;
    private DefaultTableCellRenderer centerRenderer;

    public WorkflowListPanel(ArrayList<Document> documents) {
        this.centerRenderer = new DefaultTableCellRenderer();
        this.centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.setLayout(new BorderLayout());

        if (documents.isEmpty()) {
            listEmpty = new JLabel("The list is empty");
            listEmpty.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(listEmpty);
        } else {
            columnTitles = new String[NB_TITLES];
            setColumnTitles(Document.getColumnsTitles());
            setData(documents);

            table = new JTable(data, columnTitles);
            table.setEnabled(false);
            centerData();

            this.add(table.getTableHeader(), BorderLayout.PAGE_START);
            this.add(table);
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
