package userInterface;

import controller.ApplicationController;
import exception.DBException;
import model.Document;
import model.WorkflowType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WorkflowPanel extends JPanel {
    private JLabel workflowTypesLabel;
    private JComboBox<String> workflowTypes;
    private GridBagConstraints layoutConstraints;
    private JButton submit;
    private JButton back;
    private JButton reset;
    private ArrayList<WorkflowType> allWorkflowType;
    private String[] types;
    private ApplicationController controller;
    private Container container;

    public WorkflowPanel(Container container){
        this.container = container;

        this.setLayout(new GridBagLayout());
        try {
            controller = new ApplicationController();
            allWorkflowType = controller.getAllWorkflowTypes();
            layoutConstraints = new GridBagConstraints();
            setTypes(allWorkflowType);

            workflowTypesLabel = new JLabel("Choose a workflow type :");
            workflowTypesLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            workflowTypes = new JComboBox<String>(types);
            workflowTypes.setSelectedItem("Facture");
            workflowTypes.setMaximumRowCount(3);
            workflowTypes.setEnabled(true);

            submit = new JButton("Submit");
            back = new BackButton(container);

            submit.addActionListener(new SubmitListener());

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


            layoutConstraints.gridx = 2;
            layoutConstraints.gridy = 1;
            this.add(back, layoutConstraints);
        }
        catch (DBException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQLError", JOptionPane.ERROR_MESSAGE);
        }

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
        private Integer workflowNumber;

        public void actionPerformed(ActionEvent event) {
            workflowNumber = workflowTypes.getSelectedIndex() + 1;
            container.removeAll();
            container.add(new WorkflowListPanel(workflowNumber, container));
            container.revalidate();
            container.repaint();
        }
    }
}
