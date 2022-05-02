package userInterface;

import controller.ApplicationController;
import exception.DBException;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class AllDocumentsPanel extends JPanel{
    public final static int NB_COLUMNS = 6;

    private ApplicationController controller;
    private DocumentsModel model;
    private JTable table;
    private TableColumn column;
    private String[] columnsName;
    private JScrollPane scrollPane;
    private GridBagConstraints layoutConstraints;

    public AllDocumentsPanel() {
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();

        try {
            controller = new ApplicationController();
            model = new DocumentsModel(controller.getAllDocuments());

            table = new JTable(model);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scrollPane = new JScrollPane(table);

            setColumnsSize();
            setColumnsName();

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            this.add(table.getTableHeader(), layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            this.add(table, layoutConstraints);
        }
        catch (DBException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void setColumnsSize() {
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(55);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(80);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(130);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(80);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(5);
        column.setPreferredWidth(100);
    }

    public void setColumnsName() {
        columnsName = new String[NB_COLUMNS];
        for (int i = 0; i < NB_COLUMNS; i++) {
            columnsName[i] = model.getColumnName(i);
        }
    }
}
