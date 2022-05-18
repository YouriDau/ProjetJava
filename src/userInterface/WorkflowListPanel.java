package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class WorkflowListPanel extends JPanel {

    private JLabel listEmpty;
    private JButton back;
    private Container container;
    private ApplicationController controller;
    private ArrayList<Document> documents;
    private DocumentByWorkflowModel model;
    private JTable table;
    private TableColumn column;
    private JScrollPane scrollPane;

    public WorkflowListPanel(int workflowNumber, Container container) {
        this.setLayout(new BorderLayout());
        this.container = container;

        try {
            controller = new ApplicationController();
            documents = controller.getDocuments(workflowNumber);

            if (documents.isEmpty()) {
                listEmpty = new JLabel("The list is empty");
                listEmpty.setHorizontalAlignment(SwingConstants.CENTER);

                back = new BackButton(container);

                this.add(listEmpty, BorderLayout.CENTER);
                this.add(back, BorderLayout.AFTER_LAST_LINE);
            } else {
                model = new DocumentByWorkflowModel(controller.getDocuments(workflowNumber), controller.getAllDocumentTypes());

                table = new JTable(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                scrollPane = new JScrollPane(table);

                setColumnsSize();

                this.add(table.getTableHeader(), BorderLayout.CENTER);
                this.add(scrollPane, BorderLayout.CENTER);
            }
        }
        catch(DBException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (SingletonConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setColumnsSize() {
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(55);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(80);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(80);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(130);
    }
}
