package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllDocumentsPanel extends JPanel{
    private ApplicationController controller;
    private ArrayList<Document> allDocuments;
    private DocumentsModel model;
    private JTable table;
    private TableColumn column;
    private JScrollPane scrollPane;
    private GridBagConstraints layoutConstraints;
    private JButton addDocument;
    private JButton modifyDocument;
    private JButton deleteDocument;
    private Container container;

    public AllDocumentsPanel(Container container) {
        this.container = container;
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();

        try {
            controller = new ApplicationController();
            allDocuments = controller.getAllDocuments();
            model = new DocumentsModel(controller.getAllDocuments());

            table = new JTable(model);

            table.setPreferredScrollableViewportSize(new Dimension(520, 300));
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scrollPane = new JScrollPane(table);

            addDocument = new JButton("Add a new document");
            addDocument.addActionListener(new AddDocumentListener());

            modifyDocument = new JButton("Modify");
            deleteDocument = new JButton("Delete");

            deleteDocument.addActionListener(new DeleteDocumentListener());

            setColumnsSize();

            layoutConstraints.gridwidth = 2;
            layoutConstraints.insets = new Insets(3, 0, 15, 0);
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            this.add(addDocument, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            this.add(table.getTableHeader(), layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 2;
            this.add(scrollPane, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 3;
            this.add(modifyDocument, layoutConstraints);

            layoutConstraints.anchor = GridBagConstraints.EAST;
            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 3;
            this.add(deleteDocument, layoutConstraints);
        }
        catch (DBException exception) {
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
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(80);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(5);
        column.setPreferredWidth(100);
    }

    public class AddDocumentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new NewDocumentPanel(container));
            container.revalidate();
            container.repaint();
        }
    }

    public class DeleteDocumentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                controller.deleteDocument(allDocuments.get(table.getSelectedRow()).getNumber());
                container.removeAll();
                container.add(new AllDocumentsPanel(container));
                container.revalidate();
                container.repaint();
            }
            catch (DBException exception) {
                JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQLError", JOptionPane.ERROR_MESSAGE);
            }
            catch (SingletonConnectionException exception) {
                JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
