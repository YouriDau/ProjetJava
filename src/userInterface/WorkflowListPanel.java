package userInterface;

import controller.ApplicationController;
import exception.DBException;
import model.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WorkflowListPanel extends JPanel {

    private JLabel listEmpty;
    private JButton back;
    private Container container;
    private ApplicationController controller;
    private ArrayList<Document> documents;
    private DocumentsModel model;
    private JTable table;
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
                model = new DocumentsModel(controller.getDocuments(workflowNumber));

                table = new JTable(model);
                scrollPane = new JScrollPane(table);

                this.add(scrollPane);
                this.add(table.getTableHeader(), BorderLayout.PAGE_START);
                this.add(table, BorderLayout.CENTER);
            }
        }
        catch(DBException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
