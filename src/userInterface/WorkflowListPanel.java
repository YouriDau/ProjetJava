package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;
import model.DocumentByWorkflowType;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class WorkflowListPanel extends JPanel {

    private JLabel listEmpty;
    private JButton back;
    private Container container;
    private ApplicationController controller;
    private ArrayList<DocumentByWorkflowType> documents;
    private DocumentByWorkflowModel model;
    private JTable table;
    private TableColumn column;
    private JScrollPane scrollPane;
    private GridBagConstraints layoutConstraints;

    public WorkflowListPanel(int workflowNumber, Container container) {
        this.setLayout(new GridBagLayout());
        this.layoutConstraints = new GridBagConstraints();
        this.container = container;

        try {
            controller = new ApplicationController();
            documents = controller.getDocuments(workflowNumber);

            if (documents.isEmpty()) {
                listEmpty = new JLabel("The list is empty");
                listEmpty.setHorizontalAlignment(SwingConstants.CENTER);

                back = new HomeButton(container);

                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 0;
                this.add(listEmpty, layoutConstraints);

                layoutConstraints.gridx = 1;
                this.add(back, layoutConstraints);
            } else {
                model = new DocumentByWorkflowModel(documents);

                table = new JTable(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                table.setPreferredScrollableViewportSize(new Dimension(345,300));
                scrollPane = new JScrollPane(table);

                setColumnsSize();

                layoutConstraints.insets = new Insets(0, 0, 15, 0);
                layoutConstraints.gridwidth = 2;
                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 0;
                this.add(table.getTableHeader(), layoutConstraints);

                layoutConstraints.gridy = 1;
                this.add(scrollPane,layoutConstraints);

                layoutConstraints.gridwidth = 1;
                layoutConstraints.gridy = 2;
                this.add(new HomeButton(container), layoutConstraints);
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
