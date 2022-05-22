package userInterface;

import controller.ApplicationController;
import exception.ModifyDocumentException;
import exception.SingletonConnectionException;
import model.Document;
import model.DocumentType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
    private JLabel updateTheStockLabel;
    private ApplicationController controller;
    private JRadioButton yes;
    private JRadioButton no;
    private ButtonGroup buttonsStock;
    private JButton submit;

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
            workflowLabel = new JLabel("Wofkflow number : ");
            updateTheStockLabel = new JLabel("Update the stock? : ");

            number = new JTextField(10);
            number.setText(document.getNumber().toString());
            number.setEnabled(false);

            paymentCondition = new JTextArea(3, 15);
            paymentCondition.setLineWrap(true);
            paymentCondition.setText(document.getPaymentCondition());

            creditLimit = new JTextField(12);
            if (document.getCreditLimit() != null)
                creditLimit.setText(document.getCreditLimit().toString());

            documentTypesComboBox = new JComboBox<>();
            documentTypesComboBox.setMaximumRowCount(4);
            fillDocumentTypes(controller.getAllDocumentTypes());

            workflowsComboBox = new JComboBox<>();
            workflowsComboBox.setMaximumRowCount(4);
            fillWorkflows(controller.getAllWorkflow());
            workflowsComboBox.setSelectedItem(document.getworkflowNumber());

            submit = new JButton("submit");

            yes = new JRadioButton("yes", false);
            no = new JRadioButton("no", true);

            buttonsStock = new ButtonGroup();
            buttonsStock.add(yes);
            buttonsStock.add(no);

            submit.addActionListener(new SubmitListener());

            layoutConstraints.insets = new Insets(0, 0, 15, 15);
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(numberLabel, layoutConstraints);

            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(number, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(paymentConditionLabel, layoutConstraints);

            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(new JScrollPane(paymentCondition), layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 2;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(creditLimitLabel, layoutConstraints);

            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(creditLimit, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 3;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(documentTypeLabel, layoutConstraints);

            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(documentTypesComboBox, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 4;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(workflowLabel, layoutConstraints);

            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(workflowsComboBox, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 5;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(updateTheStockLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(yes, layoutConstraints);

            layoutConstraints.gridx = 2;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(no, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 6;
            layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
            this.add(submit, layoutConstraints);

            layoutConstraints.gridx = 1;
            this.add(new BackButton(new AllDocumentsPanel(container), container), layoutConstraints);
        }
        catch (SingletonConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
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
            i++;
        }
    }

    public class SubmitListener implements ActionListener {
        private Pattern pattern;
        private Double newCreditLimit;
        private String newPaymentCondition;

        public void actionPerformed(ActionEvent event) {
            // Vérifier si le text à - de 100 caractères
            if (paymentCondition.getText().length() > 100) {
                JOptionPane.showMessageDialog(null, "Le nombre de caractères ne peut pas être suppérieur à 100\nNombre de caractères actuel : " + paymentCondition.getText().length(),
                        "Error payment condition", JOptionPane.ERROR_MESSAGE);
            } else {

                // Vérifier si le text est alphanumérique uniquement de "a" à "z", espaces et des chiffres
                if (!pattern.matches("^[A-Za-z\\d\\p{javaWhitespace}]*$", paymentCondition.getText())) {
                    JOptionPane.showMessageDialog(null, "Les conditions de paiements doivent uniquement\ncomprendre des lettres et des chiffres",
                            "Error payment condition", JOptionPane.ERROR_MESSAGE);
                } else {

                    // Vérifier si le nombre respecte bien le format décimal, entier ou null
                    if (!pattern.matches("^[0-9]*\\.?[0-9]*$", creditLimit.getText())) {
                        JOptionPane.showMessageDialog(null, "Credit limit peut être soit vide soit respecter le format suivant XXX OU XXX.XXX",
                                "Error credit limit", JOptionPane.ERROR_MESSAGE);
                        creditLimit.setText("");
                    } else {
                        // Vérifier si créditLimit est rempli
                        if (!creditLimit.getText().equals("")) {
                            newCreditLimit =  Double.parseDouble(creditLimit.getText());
                        } else {
                            newCreditLimit = null;
                        }

                        if (!paymentCondition.getText().equals("")) {
                            newPaymentCondition = paymentCondition.getText();
                        } else {
                            newPaymentCondition = null;
                        }

                        document.setPaymentCondition(newPaymentCondition);
                        document.setCreditLimit(newCreditLimit);
                        document.setType(documentTypes[documentTypesComboBox.getSelectedIndex()].getNumber());
                        document.setworkflowNumber(workflowNumbers[workflowsComboBox.getSelectedIndex()]);
                        document.setUpdateTheStock(yes.isSelected());
                        try {
                            controller.modifyDocument(document);
                        }
                        catch (ModifyDocumentException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage(), "SQLError", JOptionPane.ERROR_MESSAGE);
                        }
                        catch (SingletonConnectionException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
                        }

                        container.removeAll();
                        container.add(new AllDocumentsPanel(container));
                        container.revalidate();
                        container.repaint();
                    }
                }
            }
        }
    }
}
