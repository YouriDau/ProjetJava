package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;
import model.BusinessTaskModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PromotionsByItemPanel extends JPanel {
    private Container container;
    private GridBagConstraints layoutConstraints;
    private ApplicationController controller;
    private ArrayList<BusinessTaskModel> businessTaskModels;
    private JLabel label;
    private JButton back;
    private JTable table;
    private JScrollPane scrollPane;
    PromotionsByItemModel model;

    public PromotionsByItemPanel(Container container, String wordingItem){
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();
        setContainer(container);
        try {
            controller = new ApplicationController();
            businessTaskModels = controller.getBusinessTaskInformation(wordingItem);
            if (businessTaskModels.isEmpty()){
                label = new JLabel("There were no sales during the promotions for this item : "+wordingItem);
                label.setHorizontalAlignment(SwingConstants.CENTER);

                back = new BackButton( new ItemSelectionPanel(container),container);

                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 0;
                this.add(label, layoutConstraints);

                layoutConstraints.gridy = 1;
                layoutConstraints.insets = new Insets(15,0,0,0);
                this.add(back, layoutConstraints);
            } else {
                label = new JLabel(wordingItem+": ");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                model = new PromotionsByItemModel(businessTaskModels);
                back = new BackButton( new ItemSelectionPanel(container),container);
                table = new JTable(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

                table.setPreferredScrollableViewportSize(new Dimension(425,300));
                scrollPane = new JScrollPane(table);

                layoutConstraints.gridwidth = 1;
                layoutConstraints.insets = new Insets(0,0,15,0);
                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 0;
                this.add(label, layoutConstraints);

                layoutConstraints.insets = new Insets(15,0,0,0);
                layoutConstraints.gridy = 3;
                this.add(back, layoutConstraints);

                layoutConstraints.insets = new Insets(0,0,0,0);
                layoutConstraints.gridy = 1;
                this.add(table.getTableHeader(), layoutConstraints);

                layoutConstraints.gridy = 2;
                this.add(scrollPane, layoutConstraints);

            }
        } catch (DBException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        } catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
