package userInterface;

import javax.swing.*;
import java.awt.*;

public class DocumentWorkflowPanel extends JPanel {
    private JLabel workflowTypesLabel;
    private JComboBox<String> workflowTypes;
    private GridBagConstraints layoutConstraints;

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

        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.insets = new Insets(0, 0, 10, 10);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        this.add(workflowTypesLabel, layoutConstraints);

        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 0;
        this.add(workflowTypes, layoutConstraints);
    }
}
