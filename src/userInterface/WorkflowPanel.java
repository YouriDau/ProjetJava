package userInterface;

import controller.ApplicationController;
import exception.SingletonConnectionException;
import model.WorkflowType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WorkflowPanel extends JPanel {
    private JLabel workflowTypesLabel;
    private JComboBox workflowTypesComboBox;
    private WorkflowType[] workflowTypes;
    private GridBagConstraints layoutConstraints;
    private JButton submit;
    private ApplicationController controller;
    private Container container;

    public WorkflowPanel(Container container){
        this.container = container;

        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();
        try {
            controller = new ApplicationController();

            workflowTypesLabel = new JLabel("Choose a workflow type :");
            workflowTypesLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            workflowTypesComboBox = new JComboBox();
            workflowTypesComboBox.setMaximumRowCount(4);
            fillWorkflowTypes(controller.getAllWorkflowTypes());

            submit = new JButton("Submit");

            submit.addActionListener(new SubmitListener());

            layoutConstraints.insets = new Insets(0, 0, 15, 15);
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            this.add(workflowTypesLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 0;
            this.add(workflowTypesComboBox, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(submit, layoutConstraints);


            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(new BackButton(new HomePanel(), container), layoutConstraints);
        }
        catch (SingletonConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void fillWorkflowTypes(ArrayList<WorkflowType> type) {
        workflowTypes = new WorkflowType[type.size()];
        int i = 0;
        for (WorkflowType info:type) {
            workflowTypes[i] = info;
            workflowTypesComboBox.addItem(info.getWording());
            i++;
        }
    }

    private class SubmitListener implements ActionListener {
        private Integer workflowNumber;

        @Override
        public void actionPerformed(ActionEvent event) {
            workflowNumber = workflowTypes[workflowTypesComboBox.getSelectedIndex()].getNumber();
            container.removeAll();
            container.add(new WorkflowListPanel(workflowNumber, container));
            container.revalidate();
            container.repaint();
        }
    }
}
