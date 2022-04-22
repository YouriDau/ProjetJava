package userInterface;

import javax.swing.*;
import java.awt.*;

public class DocumentWorkflowPanel extends JPanel {
    private JLabel workflowTypesLabel;
    private JComboBox<String> workflowTypes;
    private GridBagConstraints layoutConstraints;
    private JButton submit;
    private JButton back;
    private JButton reset;

    public DocumentWorkflowPanel() {
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();

        workflowTypesLabel = new JLabel("Choose a workflow type :");
        workflowTypesLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        String[] types = {"Facture", "Bon de livraison", "Bon de commande", "Reçu"};
        workflowTypes = new JComboBox<>(types);
        workflowTypes.setSelectedItem("Facture");
        workflowTypes.setMaximumRowCount(3);
        workflowTypes.setEnabled(true);

        submit = new JButton("Submit");
        back = new JButton("Back");
        reset = new JButton("Reset");

        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.insets = new Insets(0, 0, 10, 10);
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        this.add(workflowTypesLabel, layoutConstraints);

        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 0;
        this.add(workflowTypes, layoutConstraints);

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        this.add(submit, layoutConstraints);

        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 1;
        this.add(back, layoutConstraints);

        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 1;
        this.add(reset, layoutConstraints);
    }
}
