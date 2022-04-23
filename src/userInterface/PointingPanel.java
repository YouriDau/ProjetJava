package userInterface;

import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

public class PointingPanel extends JPanel {
    private JLabel firstDateLabel;
    private JLabel secondDateLabel;
    private JSpinner firstDate;
    private JSpinner secondDate;
    private SpinnerDateModel dateModel;
    private SpinnerDateModel dateModel2;
    private JSpinner.DateEditor firstEditor;
    private JSpinner.DateEditor secondEditor;
    private GregorianCalendar today;
    private GridBagConstraints layoutConstraints;
    private JButton submit;
    private JButton back;
    private JButton reset;

    public PointingPanel() {
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();

        firstDateLabel = new JLabel("First date : ");
        firstDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        secondDateLabel = new JLabel("Second date : ");
        secondDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        today = new GregorianCalendar();
        dateModel = new SpinnerDateModel();
        dateModel2 = new SpinnerDateModel();
        firstDate = new JSpinner(dateModel);
        secondDate = new JSpinner(dateModel2);
        firstEditor = new JSpinner.DateEditor(firstDate, "dd-MM-yyyy");
        secondEditor = new JSpinner.DateEditor(secondDate, "dd-MM-yyyy");

        firstDate.setEditor(firstEditor);
        secondDate.setEditor(secondEditor);

        firstDate.setValue(today.getTime());
        secondDate.setValue(today.getTime());

        submit = new JButton("Submit");
        back = new JButton("Back");
        reset = new JButton("Reset");

        layoutConstraints.fill = GridBagConstraints.BOTH;
        layoutConstraints.insets = new Insets(0,0, 15, 15);
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        this.add(firstDateLabel, layoutConstraints);

        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 0;
        this.add(firstDate, layoutConstraints);

        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        this.add(secondDateLabel, layoutConstraints);

        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 1;
        this.add(secondDate, layoutConstraints);

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        this.add(submit, layoutConstraints);

        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 2;
        this.add(back, layoutConstraints);

        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 2;
        this.add(reset, layoutConstraints);
    }
}
