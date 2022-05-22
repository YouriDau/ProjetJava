package userInterface;

import controller.ApplicationController;
import exception.SingletonConnectionException;
import model.BusinessTaskModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

public class PromotionsByItemPanel extends JPanel {
    private Container container;
    private GridBagConstraints layoutConstraints;
    private ApplicationController controller;
    private ArrayList<BusinessTaskModel> businessTaskModels;
    private JLabel label;
    private JButton back;
    private JButton add;
    private JTable table;
    private JScrollPane scrollPane;
    private PromotionsByItemModel model;
    private String wordingItem;

    public PromotionsByItemPanel(Container container, String wordingItem){
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();
        this.wordingItem = wordingItem;
        this.container = container;

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
                // Préparation du label
                label = new JLabel(wordingItem+": ");
                // Ajout de style au label
                label.setFont(new Font("Verdana", Font.PLAIN, 24));
                // Ajout d'un soulignage
                Font font = label.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                label.setFont(font.deriveFont(attributes));
                // Centrer le label
                label.setHorizontalAlignment(SwingConstants.CENTER);
                // création du model
                model = new PromotionsByItemModel(businessTaskModels);
                // Préparation des composants
                back = new BackButton( new ItemSelectionPanel(container),container);
                add = new JButton("Add promotion");
                add.addActionListener(new AddListener());
                table = new JTable(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
                table.setPreferredScrollableViewportSize(new Dimension(425,250));
                scrollPane = new JScrollPane(table);
                // Ajouts des composants
                layoutConstraints.gridwidth = 2;
                layoutConstraints.insets = new Insets(0,0,15,0);
                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 0;
                this.add(label, layoutConstraints);

                layoutConstraints.gridwidth = 1;
                layoutConstraints.insets = new Insets(15,0,0,0);
                layoutConstraints.gridy = 3;
                layoutConstraints.gridx = 0;
                this.add(back, layoutConstraints);

                layoutConstraints.gridx = 1;
                layoutConstraints.anchor = GridBagConstraints.LINE_END;
                this.add(add, layoutConstraints);

                layoutConstraints.gridwidth = 2;
                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 1;
                this.add(table.getTableHeader(), layoutConstraints);

                layoutConstraints.gridy = 2;
                this.add(scrollPane, layoutConstraints);

            }
        }
        catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public class AddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            container.removeAll();
            container.add(new NewPromotionPanel(container, wordingItem, businessTaskModels));
            container.revalidate();
            container.repaint();
        }
    }
}
