package userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemPanel extends JPanel {
    private JLabel firstNumberLabel;
    private JLabel secondNumberLabel;
    private JSpinner firstNumber;
    private JSpinner secondNumber;
    private SpinnerNumberModel firstNumberModel;
    private SpinnerNumberModel secondNumberModel;
    private GridBagConstraints layoutConstraints;
    private JButton submit;
    private JButton reset;
    private Container container;

    public ItemPanel (Container container) {
        this.container = container;

        this.setLayout(new GridBagLayout());
        layoutConstraints = new GridBagConstraints();
        firstNumberLabel = new JLabel("Choose the min number :");
        firstNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        secondNumberLabel = new JLabel("Choose the max number :");
        secondNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);

        firstNumberModel = new SpinnerNumberModel(0, 0, 100, 1);
        firstNumber = new JSpinner(firstNumberModel);
        firstNumber.setPreferredSize(new Dimension(50, 20));
        firstNumber.setEditor(new JSpinner.DefaultEditor(firstNumber));

        secondNumberModel = new SpinnerNumberModel(0, 0, 100, 1);
        secondNumber = new JSpinner(secondNumberModel);
        secondNumber.setPreferredSize(new Dimension(50, 20));
        secondNumber.setEditor(new JSpinner.DefaultEditor(secondNumber));

        submit = new JButton("Submit");
        reset = new JButton("Reset");

        layoutConstraints.insets = new Insets(0, 0, 15, 15);
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        this.add(firstNumberLabel, layoutConstraints);

        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 0;
        this.add(firstNumber, layoutConstraints);

        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        this.add(secondNumberLabel, layoutConstraints);

        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 1;
        this.add(secondNumber, layoutConstraints);

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        submit.addActionListener(new SubmitListener ());
        this.add(submit, layoutConstraints);

        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 2;
        this.add(new BackButton(new HomePanel(), container), layoutConstraints);

        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 2;
        reset.addActionListener(new ResetListener());
        this.add(reset, layoutConstraints);
    }

    private class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            firstNumber.setValue(0);
            secondNumber.setValue(0);
        }
    }

    private class SubmitListener implements ActionListener{
        private Integer minValue;
        private Integer maxValue;
        @Override
        public void actionPerformed(ActionEvent e) {
            minValue = Integer.parseInt(firstNumber.getValue().toString());
            maxValue = Integer.parseInt(secondNumber.getValue().toString());

            if (minValue > maxValue){
                JOptionPane.showMessageDialog(null, "Min value is higher than max value", "Error value", JOptionPane.ERROR_MESSAGE);
                firstNumber.setValue(0);
                secondNumber.setValue(0);
            } else {
                container.removeAll();
                container.add(new ByPromoPanel(minValue, maxValue,container));
                container.revalidate();
                container.repaint();
            }
        }
    }
}
