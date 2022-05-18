package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;
import model.DocumentType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class NewDocumentPanel extends JPanel {
    private GridBagConstraints layoutConstraints;
    private Container container;
    private ApplicationController controller;

    private JLabel paymentConditionLabel;
    private JLabel creditLimitLabel;
    private JLabel documentTypeLabel;
    private JLabel workflowLabel;
    private JLabel updateTheStockLabel;

    private JTextArea paymentCondition;
    private JTextField creditLimit;
    private JComboBox documentTypesComboBox;
    private JComboBox workflowsComboBox;

    private DocumentType[] documentTypes;
    private Integer[] workflowNumbers;

    private JRadioButton yes;
    private JRadioButton no;
    private ButtonGroup buttonsStock;

    private JButton submit;
    private JButton back;

    public NewDocumentPanel(Container container) {
        this.container = container;
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();

        try {
            controller = new ApplicationController();

            paymentConditionLabel = new JLabel("Payment's conditions : ");
            creditLimitLabel = new JLabel("Credit limit : ");
            documentTypeLabel = new JLabel("Document's types : ");
            workflowLabel = new JLabel("Workflow id : ");
            updateTheStockLabel = new JLabel("Update the stock? :");

            paymentCondition = new JTextArea(3, 15);
            paymentCondition.setLineWrap(true);

            creditLimit = new JTextField(12);

            documentTypesComboBox = new JComboBox<>();
            documentTypesComboBox.setMaximumRowCount(4);
            //fillDocumentTypes(controller.getAllDocumentTypes());

            workflowsComboBox = new JComboBox<>();
            workflowsComboBox.setMaximumRowCount(4);
            fillWorkflows(controller.getAllWorkflow());

            submit = new JButton("submit");
            back = new HomeButton(container);

            yes = new JRadioButton("yes", false);
            no = new JRadioButton("no", true);

            buttonsStock = new ButtonGroup();
            buttonsStock.add(yes);
            buttonsStock.add(no);

            //creditLimit.addActionListener(new CreditLimitListener());
            submit.addActionListener(new SubmitListener());

            layoutConstraints.insets = new Insets(0, 0, 15, 15);
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(paymentConditionLabel, layoutConstraints);

            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(new JScrollPane(paymentCondition), layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(creditLimitLabel, layoutConstraints);

            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(creditLimit, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 2;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(documentTypeLabel, layoutConstraints);

            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(documentTypesComboBox, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 3;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(workflowLabel, layoutConstraints);

            layoutConstraints.gridwidth = 2;
            layoutConstraints.gridx = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(workflowsComboBox, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 4;
            this.add(updateTheStockLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            this.add(yes, layoutConstraints);

            layoutConstraints.gridx = 2;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(no, layoutConstraints);

            layoutConstraints.gridwidth = 1;
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 5;
            layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
            this.add(submit, layoutConstraints);

            layoutConstraints.gridx = 1;
            this.add(back, layoutConstraints);
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
            i++;
        }
    }

    // ACTIF SEULEMENT QUAND APPUIE SUR "ENTER" DANS CREDIT LIMIT
    /*public class CreditLimitListener implements ActionListener {
        private Pattern pattern;
        private Matcher matcher;

        @Override
        public void actionPerformed(ActionEvent e) {
            pattern = Pattern.compile("^[0-9]*\\.?[0-9]*$");
            matcher = pattern.matcher(creditLimit.getText());

            // Vérifier si le nombre respecte bien le format décimal, entier ou null
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(null, "Credit limit peut être soit vide soit respecter le format suivant XXX OU XXX.XXX",
                        "Error credit limit", JOptionPane.ERROR_MESSAGE);
                submit.setEnabled(false);
            } else {
                submit.setEnabled(true);
            }
        }
    }
     */

    public class SubmitListener implements ActionListener {
        private Document document;
        private Pattern pattern;
        private String newPaymentCondition;
        private Double newCreditLimit;

        @Override
        public void actionPerformed(ActionEvent event) {
            if (paymentCondition.getText().length() > 100) {
                JOptionPane.showMessageDialog(null, "Le nombre de caractères ne peut pas être suppérieur à 100\nNombre de caractères actuel : " + paymentCondition.getText().length(),
                        "Error payment condition", JOptionPane.ERROR_MESSAGE);
                paymentCondition.setBackground(new Color(255, 155, 155, 255));
            } else {

                // Vérifier si le text est alphanumérique uniquement de "a" à "z" et des chiffres
                if (!pattern.matches("^[A-Za-z'.\\d\\n\\p{javaWhitespace}]*$", paymentCondition.getText())) {
                    JOptionPane.showMessageDialog(null, "Les conditions de paiements doivent uniquement\ncomprendre des lettres et des chiffres",
                            "Error payment condition", JOptionPane.ERROR_MESSAGE);
                    paymentCondition.setBackground(new Color(255, 155, 155, 255));

                } else {
                    paymentCondition.setBackground(new Color(255, 255, 255, 255));

                    if (!pattern.matches("^[0-9]*\\.?[0-9]*$", creditLimit.getText())) {
                        JOptionPane.showMessageDialog(null, "Credit limit peut être soit vide soit respecter le format suivant XXX OU XXX.XXX",
                                "Error credit limit", JOptionPane.ERROR_MESSAGE);
                        creditLimit.setBackground(new Color(255, 155, 155, 255));
                    } else {
                        creditLimit.setBackground(new Color(255, 255, 255, 255));
                        if (paymentCondition.getText().equals("")) {
                            newPaymentCondition = null;
                        } else {
                            newPaymentCondition = paymentCondition.getText();
                        }
                        if (creditLimit.getText().equals("")) {
                            newCreditLimit = null;
                        } else {
                            newCreditLimit = Double.parseDouble(creditLimit.getText());
                        }

                        try {
                            document = new Document(null, new GregorianCalendar(), newPaymentCondition, newCreditLimit,
                                    documentTypes[documentTypesComboBox.getSelectedIndex()].getNumber(),
                                    workflowNumbers[workflowsComboBox.getSelectedIndex()], yes.isSelected());

                            controller.addDocument(document);
                        }
                        catch(DBException exception) {
                            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQLError", JOptionPane.ERROR_MESSAGE);
                        }
                        catch (SingletonConnectionException exception) {
                            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
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
