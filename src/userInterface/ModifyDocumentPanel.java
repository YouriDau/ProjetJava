package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;
import model.DocumentType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModifyDocumentPanel extends JPanel {
    private Container container;
    private GridBagConstraints layoutConstraints;
    private Document document;
    private JTextField number;
    private JTextArea paymentCondition;
    private JTextField creditLimit;
    private JComboBox documentTypesComboBox;
    private JComboBox workflowsComboBox;
    private DocumentType[] documentTypes;
    private Integer[] workflowNumbers;
    private JLabel numberLabel;
    private JLabel paymentConditionLabel;
    private JLabel creditLimitLabel;
    private JLabel documentTypeLabel;
    private JLabel workflowLabel;
    private ApplicationController controller;

    public ModifyDocumentPanel(Container container, Document document) {
        this.container = container;
        this.document = document;
        this.setLayout(new GridBagLayout());
        this.layoutConstraints = new GridBagConstraints();

        try {
            this.controller = new ApplicationController();

            numberLabel = new JLabel("Number : ");
            paymentConditionLabel = new JLabel("Payment's condition : ");
            creditLimitLabel = new JLabel("Credit limit : ");
            documentTypeLabel = new JLabel("Document type : ");
            workflowLabel = new JLabel("Wofkflow type : ");

            number = new JTextField(10);
            number.setText(document.getNumber().toString());
            number.setEnabled(false);

            paymentCondition = new JTextArea(3, 15);
            paymentCondition.setLineWrap(true);
            paymentCondition.setText(document.getPaymentCondition());

            creditLimit = new JTextField(12);
            creditLimit.setText(document.getCreditLimit().toString());

            documentTypesComboBox = new JComboBox<>();
            documentTypesComboBox.setMaximumRowCount(4);
            fillDocumentTypes(controller.getAllDocumentTypes());

            workflowsComboBox = new JComboBox<>();
            workflowsComboBox.setMaximumRowCount(4);
            fillWorkflows(controller.getAllWorkflow());

            layoutConstraints.insets = new Insets(0, 0, 15, 15);
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(numberLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 0;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(number, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(paymentConditionLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(paymentCondition, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 2;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(creditLimitLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 2;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(creditLimit, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 3;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(documentTypeLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 3;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(documentTypesComboBox, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 4;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(workflowLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 4;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(workflowsComboBox, layoutConstraints);
        }
        catch(DBException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQLError", JOptionPane.ERROR_MESSAGE);
        }
        catch (SingletonConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
        }

    }

    public void fillDocumentTypes(ArrayList<DocumentType> documents) {
        this.documentTypes = new DocumentType[documents.size()];
        int i = 0;
        for (DocumentType document:documents) {
            this.documentTypes[i] = document;
            documentTypesComboBox.addItem(document.getWording());
            i++;
        }
    }

    public void fillWorkflows(ArrayList<Integer> workflowsId) {
        workflowNumbers = new Integer[workflowsId.size()];
        int i = 0;
        for (Integer id : workflowsId) {
            workflowNumbers[i] = id;
            workflowsComboBox.addItem(id);
        }
    }
}
