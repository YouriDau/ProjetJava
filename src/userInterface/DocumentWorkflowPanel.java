package userInterface;

import controller.ApplicationController;
import model.WorkflowType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DocumentWorkflowPanel extends JPanel {
    private JLabel workflowTypesLabel;
    private JComboBox<String> workflowTypes;
    private GridBagConstraints layoutConstraints;
    private JButton submit;
    private JButton back;
    private JButton reset;
    private ArrayList<WorkflowType> allWorkflowType;
    private String[] types;
    private ApplicationController controller;

    public DocumentWorkflowPanel() {
        this.setLayout(new GridBagLayout());
        controller = new ApplicationController();
        layoutConstraints = new GridBagConstraints();

        allWorkflowType = controller.getAllWorkflowTypes();
        setTypes(allWorkflowType);

        workflowTypesLabel = new JLabel("Choose a workflow type :");
        workflowTypesLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        workflowTypes = new JComboBox<String>(types);
        workflowTypes.setSelectedItem("Facture");
        workflowTypes.setMaximumRowCount(3);
        workflowTypes.setEnabled(true);

        submit = new JButton("Submit");
        back = new JButton("Back");
        reset = new JButton("Reset");

        submit.addActionListener(new SubmitListener());
        back.addActionListener(new BackListener());

        layoutConstraints.insets = new Insets(0, 0, 15, 15);
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

    public void setTypes(ArrayList<WorkflowType> data) {
        String[] types;

        types = new String[data.size()];
        int i = 0;
        for (WorkflowType info:data) {
            types[i] = info.getWording();
            i++;
        }
        this.types = types;
    }

    public class SubmitListener implements ActionListener {
        private int workflowNumber;

        public void actionPerformed(ActionEvent event) {
           // workflowNumber = allWorkflowType.get;
           // controller.getDocuments(workflowType);
        }
    }

    public class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            DocumentWorkflowPanel.this.removeAll();
            DocumentWorkflowPanel.this.setLayout(new BorderLayout());
            DocumentWorkflowPanel.this.add(new AccueilPanel());
            DocumentWorkflowPanel.this.repaint();
            setVisible(true);
        }
    }
}
