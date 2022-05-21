package userInterface;

import controller.ApplicationController;
import exception.DBException;
import exception.SingletonConnectionException;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PointingListPanel extends JPanel {
    private GridBagConstraints layoutConstraints;
    private Container container;
    private ApplicationController controller;
    private GregorianCalendar firstDate;
    private GregorianCalendar secondDate;

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
            

            /*if (documents.isEmpty()) {
                listEmpty = new JLabel("The list is empty");
                listEmpty.setHorizontalAlignment(SwingConstants.CENTER);

                layoutConstraints.gridx = 0;
                layoutConstraints.gridy = 0;
                this.add(listEmpty, layoutConstraints);

                layoutConstraints.gridx = 1;
                this.add(new BackButton(new WorkflowPanel(container), container), layoutConstraints);
            }*/
        }
        catch(DBException exception) {
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "Data base exception", JOptionPane.ERROR_MESSAGE);
        }
        catch (SingletonConnectionException exception){
            JOptionPane.showMessageDialog(null, exception.getErrorMessage(), "SingletonConnexion exception", JOptionPane.ERROR_MESSAGE);
        }
    }
}
