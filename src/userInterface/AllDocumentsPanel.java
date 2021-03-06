package userInterface;

import controller.ApplicationController;
import exception.DeleteDocumentException;
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
    private JScrollPane scrollPane;
    private GridBagConstraints layoutConstraints;
    private JButton addDocument;
    private JButton deleteDocument;
    private JButton modifyDocument;
    private Container container;

    public AllDocumentsPanel(Container container) {
        this.container = container;
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();

        try {
            controller = new ApplicationController();
            allDocuments = controller.getAllDocuments();
            model = new DocumentsModel(allDocuments);

            table = new JTable(model);

            table.setPreferredScrollableViewportSize(new Dimension(515, 304));
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            addDocument = new JButton("Add a new document");
            deleteDocument = new JButton("Delete");
            modifyDocument = new JButton("Modify");

            addDocument.addActionListener(new AddDocumentListener());
            deleteDocument.addActionListener(new DeleteDocumentListener());
            modifyDocument.addActionListener(new ModifyDocumentListener());

            setColumnsSize();

            layoutConstraints.insets = new Insets(3, 0, 15, 0);
            layoutConstraints.gridwidth = 3;
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            this.add(addDocument, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 3;
            this.add(modifyDocument, layoutConstraints);

            layoutConstraints.gridx = 1;
            this.add(deleteDocument, layoutConstraints);

            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            layoutConstraints.gridx = 2;
            this.add(new BackButton(new HomePanel(), container), layoutConstraints);

            layoutConstraints.gridwidth = 3;
            layoutConstraints.anchor = GridBagConstraints.CENTER;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            this.add(table.getTableHeader(), layoutConstraints);

            layoutConstraints.gridy = 2;
            this.add(scrollPane, layoutConstraints);
        }
        catch (SingletonConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setColumnsSize() {
        TableColumn column;
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(55);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(80);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(115);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(80);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(5);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(6);
        column.setPreferredWidth(100);
    }

    private class AddDocumentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            container.removeAll();
            container.add(new NewDocumentPanel(container));
            container.revalidate();
            container.repaint();
        }
    }

    private class DeleteDocumentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Please, select a row to delete", "No row selected", JOptionPane.WARNING_MESSAGE);
                } else {
                    controller.deleteDocument(allDocuments.get(table.getSelectedRow()).getNumber());
                    container.removeAll();
                    container.add(new AllDocumentsPanel(container));
                    container.revalidate();
                    container.repaint();
                }

            }
            catch (DeleteDocumentException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
            }
            catch (SingletonConnectionException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ModifyDocumentListener implements ActionListener {
        private Document document;
        @Override
        public void actionPerformed(ActionEvent event) {
            if (table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please, select a row to modify", "No row selected", JOptionPane.WARNING_MESSAGE);
            } else {
                document = allDocuments.get(table.getSelectedRow());
                container.removeAll();
                container.add(new ModifyDocumentPanel(container, document));
                container.revalidate();
                container.repaint();
            }
        }
    }
}
