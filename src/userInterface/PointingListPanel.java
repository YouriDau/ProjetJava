package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;
import model.PointingBetweenDates;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
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

        System.out.println(firstDate.get(Calendar.YEAR) + " " + firstDate.get(Calendar.DAY_OF_MONTH));

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
                scrollPane = new JScrollPane(table);

                //setColumnsSize();

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
        catch(DBException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "Data base exception", JOptionPane.ERROR_MESSAGE);
        }
        catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SingletonConnexion exception", JOptionPane.ERROR_MESSAGE);
        }
    }
}
