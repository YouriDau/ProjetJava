package userInterface;

import controller.ApplicationController;
import exception.DBException;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class AllDocumentsPanel extends JPanel{
    public final static int NB_COLUMNS = 6;

    private Container container;
    private ApplicationController controller;
    private DocumentsModel model;
    private JTable table;
    private TableColumn column;
    private String[] columnsName;
    private JScrollPane scrollPane;

    public AllDocumentsPanel() {
        this.container = container;
        this.setLayout(new BorderLayout());

        try {
            controller = new ApplicationController();
            model = new DocumentsModel(controller.getAllDocuments());

            table = new JTable(model);
            //table.setEnabled(false);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scrollPane = new JScrollPane(table);

            setColumnsSize();
            setColumnsName();

            this.add(scrollPane);
            this.add(table.getTableHeader(), BorderLayout.PAGE_START);
            this.add(table, BorderLayout.CENTER);
        }
        catch (DBException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void setColumnsSize() {
        for (int i = 0; i < NB_COLUMNS; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(50);
        }
    }

    public void setColumnsName() {
        columnsName = new String[NB_COLUMNS];
        for (int i = 0; i < NB_COLUMNS; i++) {
            columnsName[i] = model.getColumnName(i);
        }
    }
}
