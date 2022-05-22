package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ItemSelectionPanel extends JPanel {
    private Container container;
    private GridBagConstraints layoutconstraints;
    private JLabel label;
    private JComboBox itemSelection;
    private ArrayList<String> allWordingItems;
    private ApplicationController applicationController;

    private JButton submit;
    private  JButton back;
    public ItemSelectionPanel(Container container){
        setContainer(container);
        this.setLayout( new GridBagLayout());
        layoutconstraints = new GridBagConstraints();
        label = new JLabel("Choose an item :");
        try {
            applicationController = new ApplicationController();

            // preparation du label
            label = new JLabel("Choose an item :");
            label.setHorizontalAlignment(SwingConstants.RIGHT);

            // preparation du JComboBox
            itemSelection = new JComboBox<>();
            allWordingItems = applicationController.getAllItemsWording();
            itemSelection.setMaximumRowCount(allWordingItems.size());
            for (String wording : allWordingItems){
                itemSelection.addItem(wording);
            }
            // préparation des boutons
            submit = new JButton("Submit");
            back = new BackButton(new HomePanel() ,container);
            submit.addActionListener(new  SubmitListener());

            // Ajouts au panel

            layoutconstraints.gridwidth = 2;
            layoutconstraints.gridx = 0;
            layoutconstraints.gridy = 0;
            this.add(label, layoutconstraints);

            layoutconstraints.insets = new Insets(20,0,0,0);
            layoutconstraints.gridwidth = 2;
            layoutconstraints.gridx = 0;
            layoutconstraints.gridy = 1;
            this.add(itemSelection, layoutconstraints);

            layoutconstraints.insets = new Insets(20,0,0,5);
            layoutconstraints.gridwidth = 1;
            layoutconstraints.gridy = 2;
            this.add(submit, layoutconstraints);

            layoutconstraints.insets = new Insets(20,5,0,0);
            layoutconstraints.gridwidth = 1;
            layoutconstraints.gridx = 1;
            this.add(back, layoutconstraints);
        }
        catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
        }


    }

    public void setContainer(Container container) {
        this.container = container;
    }
    public class SubmitListener implements ActionListener{
        private String wordingItem;
        @Override
        public void actionPerformed(ActionEvent e) {
            wordingItem = itemSelection.getSelectedItem().toString();
            container.removeAll();
            container.add(new PromotionsByItemPanel(container, wordingItem));
            container.revalidate();
            container.repaint();
        }
    }

}
