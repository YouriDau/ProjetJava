package userInterface;

import controller.ApplicationController;
import exception.SingletonConnectionException;
import model.PointingBetweenDates;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class PointingListPanel extends JPanel {
    private GridBagConstraints layoutConstraints;
    private Container container;
    private ApplicationController controller;
    private GregorianCalendar firstDate;
    private GregorianCalendar secondDate;
    private ArrayList<PointingBetweenDates> pointings;
    private PointingListModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel listEmpty;

    public PointingListPanel(Container container, JSpinner firstDateSpinner, JSpinner secondDateSpinner) {
        this.setLayout(new GridBagLayout());
        this.layoutConstraints = new GridBagConstraints();
        this.container = container;

        firstDate = new GregorianCalendar();
        firstDate.setTime((Date)firstDateSpinner.getValue());
        secondDate = new GregorianCalendar();
        secondDate.setTime((Date)secondDateSpinner.getValue());

        try {
            controller = new ApplicationController();
            pointings = controller.getPointingBetweenDates(firstDate, secondDate);

            if (pointings.isEmpty()) {
                listEmpty = new JLabel("The list is empty");
                listEmpty.setHorizontalAlignment(SwingConstants.CENTER);

                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 0;
                this.add(listEmpty, layoutConstraints);

                layoutConstraints.gridy = 1;
                this.add(new BackButton(new PointingPanel(container), container), layoutConstraints);
            } else {
                model = new PointingListModel(pointings);

                table = new JTable(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                table.setPreferredScrollableViewportSize(new Dimension(425,300));
                scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setVisible(true);

                setColumnsSize();

                layoutConstraints.insets = new Insets(0, 0, 15, 0);
                layoutConstraints.gridwidth = 2;
                layoutConstraints.anchor = GridBagConstraints.CENTER;
                layoutConstraints.gridy = 2;
                layoutConstraints.gridx = 0;
                this.add(new BackButton(new PointingPanel(container), container), layoutConstraints);

                layoutConstraints.anchor = GridBagConstraints.CENTER;
                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 0;
                this.add(table.getTableHeader(), layoutConstraints);

                layoutConstraints.gridy = 1;
                this.add(scrollPane,layoutConstraints);
            }
        }
        catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setColumnsSize() {
        TableColumn column;
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(95);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(80);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(110);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(80);
    }
}
