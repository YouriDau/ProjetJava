package userInterface;

import javax.print.attribute.standard.JobPriority;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public class PointingPanel extends JPanel {
    private JLabel firstDateLabel;
    private JLabel secondDateLabel;
    private JSpinner firstDate;
    private JSpinner secondDate;
    private JSpinner.DateEditor firstEditor;
    private JSpinner.DateEditor secondEditor;
    private GregorianCalendar today;
    private GridBagConstraints layoutConstraints;
    private Container container;
    private JButton submit;
    private JButton reset;

    public PointingPanel(Container container) {
        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();
        this.container = container;

        firstDateLabel = new JLabel("First date : ");
        firstDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        secondDateLabel = new JLabel("Second date : ");
        secondDateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        today = new GregorianCalendar();
        firstDate = new JSpinner(new SpinnerDateModel());
        secondDate = new JSpinner(new SpinnerDateModel());
        firstEditor = new JSpinner.DateEditor(firstDate, "dd-MM-yyyy");
        secondEditor = new JSpinner.DateEditor(secondDate, "dd-MM-yyyy");

        firstDate.setEditor(firstEditor);
        secondDate.setEditor(secondEditor);

        firstDate.setValue(today.getTime());
        secondDate.setValue(today.getTime());

        submit = new JButton("Submit");
        reset = new JButton("Reset");

        submit.addActionListener(new SubmitListener());

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
        this.add(new BackButton(new HomePanel(), container), layoutConstraints);

        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 2;
        reset.addActionListener( new ResetListener());
        this.add(reset, layoutConstraints);
    }

    public boolean firstDateInferiorToSecond(JSpinner.DateEditor firstEditor,JSpinner.DateEditor secondEditor) {
        Date date1 = new Date();
        Date date2 = new Date();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            date1 = simpleDateFormat.parse(firstEditor.getTextField().getText());
            date2 = simpleDateFormat.parse(secondEditor.getTextField().getText());
        }catch (ParseException exception){
            JOptionPane.showMessageDialog(null, "Error while parse date", exception.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        }
        return date1.before(date2) || date1.equals(date2);

    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            firstDate.setValue(today.getTime());
            secondDate.setValue(today.getTime());
        }
    }

    private class SubmitListener implements ActionListener{
        private Pattern pattern;

        @Override
        public void actionPerformed(ActionEvent e) {
            // v??rifier si la PREMIERE date respecte le format dd-mm-yyyy et si l'ann??e n'est pas inf??rieure ?? 1000 ni sup??rieur ?? 2999
            if (!pattern.matches("^[0-9]{2}-[0-9]{2}-[1-2][0-9]{3}$", firstEditor.getTextField().getText())) {
                JOptionPane.showMessageDialog(null, "The first date must respect the format dd-mm-yyyy\nAnd it must be a credible date",
                        "Error date format", JOptionPane.ERROR_MESSAGE);
            } else {
                // M??me v??rif qu'au dessus pour la DEUXIEME date
                if (!pattern.matches("^[0-9]{2}-[0-9]{2}-[1-2]{1}[0-9]{3}$", secondEditor.getTextField().getText())) {
                    JOptionPane.showMessageDialog(null, "The second date must respect the format dd-mm-yyyy\nAnd it must be a credible date",
                            "Error date format", JOptionPane.ERROR_MESSAGE);
                } else {
                    // V??rification que la 1ere date est inf??rieur a la deuxi??me
                    if (firstDateInferiorToSecond(firstEditor, secondEditor)){
                        container.removeAll();
                        container.add(new PointingListPanel(container, firstDate, secondDate));
                        container.revalidate();
                        container.repaint();
                    }else {
                        JOptionPane.showMessageDialog(null, "The first date must be inferior to the second date",
                                "Error date", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
}
