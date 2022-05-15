package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;
import model.Detail;
import model.Item;
import model.Promotion;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class ByPromoPanel extends JPanel {
    private Container container;
    private ApplicationController applicationController;
    private ArrayList<Promotion> promotions;
    private ArrayList<Item>items;
    private ArrayList<Detail>details;
    private AllPromotionsInformationModel model;
    private JLabel listEmpty;
    private GridBagConstraints layoutConstraints;
    private JButton back;
    private JTable table;
    private TableColumn column;

    private JScrollPane jScrollPane;

    public ByPromoPanel (int minvalue, int maxValue, Container container){
        this.setLayout(new BorderLayout());
        this.container = container;
        try{
            applicationController = new ApplicationController();
            promotions = applicationController.getPromotions(minvalue, maxValue);

            if (promotions.isEmpty()){
                listEmpty = new JLabel("The list is empty");
                listEmpty.setHorizontalAlignment(SwingConstants.CENTER);

                back = new BackButton(container);

                this.add(listEmpty, BorderLayout.CENTER);
                this.add(back, BorderLayout.AFTER_LAST_LINE);
            } else{
                items = applicationController.getItems(promotions);
                details = applicationController.getDetails(items);
                model = new AllPromotionsInformationModel(promotions,items,details);

                table = new JTable(model);
                table.setPreferredScrollableViewportSize(new Dimension(515,300));
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                jScrollPane = new JScrollPane(table);

                setColumnSize();
                this.add(table.getTableHeader(), BorderLayout.CENTER);

                this.add(jScrollPane, BorderLayout.CENTER);


            }
        }
        catch (DBException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), exception.getErrorTitle(), JOptionPane.ERROR_MESSAGE);
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
