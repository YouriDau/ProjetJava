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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewDocumentPanel extends JPanel {
    private GridBagConstraints layoutConstraints;
    private Container container;
    private ApplicationController controller;
    private JLabel paymentConditionLabel;
    private JLabel creditLimitLabel;
    private JLabel documentTypeLabel;
    private JLabel workflowLabel;
    private JTextArea paymentCondition;
    private JTextField creditLimit;
    private JComboBox documentsComboBox;
    private JComboBox workflowsComboBox;
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

            paymentCondition = new JTextArea(3, 15);
            paymentCondition.setLineWrap(true);

            creditLimit = new JTextField(12);

            documentsComboBox = new JComboBox<>();
            documentsComboBox.setMaximumRowCount(4);
            fillDocumentTypes(controller.getAllDocumentTypes());

            workflowsComboBox = new JComboBox<>();
            workflowsComboBox.setMaximumRowCount(4);
            fillWorkflows(controller.getAllWorkflow());

            submit = new JButton("submit");
            back = new BackButton(container);

            submit.addActionListener(new SubmitListener());

            layoutConstraints.insets = new Insets(0, 0, 15, 15);
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(paymentConditionLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 0;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(new JScrollPane(paymentCondition), layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(creditLimitLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(creditLimit, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 2;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(documentTypeLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 2;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(documentsComboBox, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 3;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(workflowLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 3;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(workflowsComboBox, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 4;
            layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
            this.add(submit, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 4;
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
        for (DocumentType document:documents) {
            documentsComboBox.addItem(document.getWording());
        }
    }

    public void fillWorkflows(ArrayList<Integer> workflowsId) {
        for (Integer id : workflowsId) {
            workflowsComboBox.addItem(id);
        }
    }

    public class SubmitListener implements ActionListener {
        private Pattern pattern;
        private Matcher matcher;
        private Document document;
        private Double newCreditLimit;

        public void actionPerformed(ActionEvent event) {
            // Vérifier si le text à - de 100 caractères
            if (paymentCondition.getText().length() > 100) {
                JOptionPane.showMessageDialog(null, "Le nombre de caractères ne peut pas être suppérieur à 100\nNombre de caractères actuel : " + paymentCondition.getText().length(),
                                            "Error payment condition", JOptionPane.ERROR_MESSAGE);
            } else {
                pattern = Pattern.compile("^[A-Za-z\\d]*$");
                matcher = pattern.matcher(paymentCondition.getText());

                // Vérifier si le text est alphanumérique uniquement de "a" à "z" et des chiffres
                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(null, "Les conditions de paiements doivent uniquement\ncomprendre des lettres et des chiffres",
                            "Error payment condition", JOptionPane.ERROR_MESSAGE);
                }
            }

            pattern = Pattern.compile("^[0-9]*\\.?[0-9]*$");
            matcher = pattern.matcher(creditLimit.getText());

            // Vérifier si le nombre respecte bien le format décimal, entier ou null
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(null, "Credit limit peut être soit vide soit respecter le format suivant XXX OU XXX.XXX",
                        "Error credit limit", JOptionPane.ERROR_MESSAGE);
                creditLimit.setText("");
            } else {
                // Vérifier si crédit limit est rempli
                if (!creditLimit.getText().equals("")) {
                    newCreditLimit =  Double.parseDouble(creditLimit.getText());
                } else { // A voir si else utile
                    newCreditLimit = null;
                }

                document = new Document(null, new GregorianCalendar(), paymentCondition.getText(), newCreditLimit, documentsComboBox.getSelectedIndex()+1, workflowsComboBox.getSelectedIndex()+1);

                try {
                    controller.addDocument(document);
                }
                catch (DBException exception) {
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
