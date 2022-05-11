package userInterface;

import controller.ApplicationController;
import dataAccess.SingletonConnection;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Document;
import model.WorkflowType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WorkflowPanel extends JPanel {
    private JLabel workflowTypesLabel;
    private JComboBox workflowTypes;
    private GridBagConstraints layoutConstraints;
    private JButton submit;
    private JButton back;
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

            workflowTypes = new JComboBox();
            workflowTypes.setMaximumRowCount(4);
            fillWorkflowTypes(controller.getAllWorkflowTypes());

            submit = new JButton("Submit");
            back = new BackButton(container);

            submit.addActionListener(new SubmitListener());

            layoutConstraints.insets = new Insets(0, 0, 15, 15);
            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 0;
            this.add(workflowTypesLabel, layoutConstraints);

            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 0;
            this.add(workflowTypes, layoutConstraints);

            layoutConstraints.gridx = 0;
            layoutConstraints.gridy = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_END;
            this.add(submit, layoutConstraints);


            layoutConstraints.gridx = 1;
            layoutConstraints.gridy = 1;
            layoutConstraints.anchor = GridBagConstraints.LINE_START;
            this.add(back, layoutConstraints);
        }
        catch (DBException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQLError", JOptionPane.ERROR_MESSAGE);
        }
        catch (SingletonConnectionException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void fillWorkflowTypes(ArrayList<WorkflowType> type) {
        for (WorkflowType info:type) {
            workflowTypes.addItem(info.getWording());
        }
    }

    public class SubmitListener implements ActionListener {
        private Integer workflowNumber;

        @Override
        public void actionPerformed(ActionEvent event) {
            workflowNumber = workflowTypes.getSelectedIndex() + 1;
            container.removeAll();
            container.add(new WorkflowListPanel(workflowNumber, container));
            container.revalidate();
            container.repaint();
        }
    }
}
