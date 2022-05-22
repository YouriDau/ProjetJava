package userInterface;

import controller.ApplicationController;
import exception.SingletonConnectionException;
import model.ResearchByPromoModel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class ByPromoPanel extends JPanel {
    private ApplicationController applicationController;
    private ArrayList<ResearchByPromoModel> researchByPromoModels;
    private AllPromotionsInformationModel model;
    private JLabel listEmpty;
    private GridBagConstraints layoutConstraints;
    private JTable table;
    private TableColumn column;
    private JScrollPane jScrollPane;

    public ByPromoPanel (int minvalue, int maxValue, Container container){
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();

        try{
            applicationController = new ApplicationController();
            researchByPromoModels = applicationController.getResearchByPromo(minvalue, maxValue);

            if (researchByPromoModels.isEmpty()){
                listEmpty = new JLabel("The list is empty");
                listEmpty.setHorizontalAlignment(SwingConstants.CENTER);

                layoutConstraints.gridwidth = 1;
                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 0;
                this.add(listEmpty, layoutConstraints);

                layoutConstraints.gridwidth = 1;
                layoutConstraints.insets = new Insets(50, 0, 15, 0);
                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 1;
                this.add(new BackButton(new ItemPanel(container), container), layoutConstraints);
            } else{
                model = new AllPromotionsInformationModel(researchByPromoModels);

                table = new JTable(model);
                table.setPreferredScrollableViewportSize(new Dimension(515,300));
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

                jScrollPane = new JScrollPane(table);

                setColumnSize();

                layoutConstraints.gridwidth = 1;
                layoutConstraints.anchor = GridBagConstraints.CENTER;
                layoutConstraints.insets = new Insets(3, 0, 15, 0);
                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 2;
                this.add(new BackButton(new ItemPanel(container), container),layoutConstraints);

                layoutConstraints.gridwidth = 1;
                layoutConstraints.anchor = GridBagConstraints.CENTER;
                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 0;
                this.add(table.getTableHeader(), layoutConstraints);

                layoutConstraints.gridwidth = 1;
                layoutConstraints.anchor = GridBagConstraints.CENTER;
                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 1;
                this.add(jScrollPane, layoutConstraints);


            }
        }
        catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        }
    }
    public void setColumnSize(){
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
    }
}
